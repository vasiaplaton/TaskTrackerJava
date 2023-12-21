package platon.ru.vsu.cs.adb_lib.sql;

import platon.ru.vsu.cs.adb_lib.annotations.*;
import platon.ru.vsu.cs.adb_lib.sql.conns.SQLConnector;
import platon.ru.vsu.cs.adb_lib.logger.Log;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public abstract class SQLRepository<T> implements SQLRepositoryI<T> {
    protected final Class<T> clazz;
    protected final EntityWork<T> entityWork;

    protected final QueryBuilder queryBuilder;
    protected final SQLConnector connector;

    protected final String tableName;
    public SQLRepository(Class<T> clazz, SQLConnector connector) {
        this.connector = connector;

        this.clazz = clazz;
        entityWork = new EntityWork<>(clazz);
        if(!clazz.isAnnotationPresent(Entity.class)) {
            throw new IllegalArgumentException();
        }
        tableName = clazz.getAnnotation(Entity.class).tableName();
        List<String> columnNamesList = Arrays.stream(entityWork.getFields()).map(e -> e.columnName).toList();
        queryBuilder = new QueryBuilder(columnNamesList.toArray(new String[0]), tableName, entityWork.getPrimaryKey().columnName);
    }

    public T getInstance(){
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            Log.getI().log(e.toString(), 0);
            throw new RuntimeException(e);
        }
    }

    protected T getInstanceByResultSet(ResultSet resultSet) throws SQLException {
        T newObject = getInstance();
        try {

            ColumnField pk = entityWork.getPrimaryKey();
            pk.field.setAccessible(true);
            pk.field.set(newObject, resultSet.getObject(pk.columnName));

            for (ColumnField columnField : entityWork.getFields()) {
                columnField.field.setAccessible(true);
                Log.getI().log("Got " + resultSet.getObject(columnField.columnName) + " for class: " + clazz.getName(), 3);

                Object value = resultSet.getObject(columnField.columnName);
                if(!Objects.equals(columnField.customParseMethod, "")) {
                    value = entityWork.getClazz().getDeclaredMethod(columnField.customParseMethod, Object.class).invoke(null, value);
                }
                columnField.field.set(newObject, value);
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            Log.getI().log(e.toString(), 0);
            throw new RuntimeException(e);
        }

        // Log.getI().log("Got " + Arrays.toString(columnValues) + " for class: " + clazz.getName(), 3);

        return newObject;
    }

    @Override
    public List<T> getAll() {
        return getAll(new Filter[0]);
    }

    @Override
    public List<T> getAll(Filter[] filters) {
        List<T> result = new ArrayList<>();

        String query = queryBuilder.select(filters);
        Log.getI().log(query, 3);

        try {
            ResultSet resultSet = connector.makeQuery(query);
            while (resultSet.next()) {
                T newObject = getInstanceByResultSet(resultSet);
                result.add(newObject);
            }
        } catch (SQLException e) {
            Log.getI().log(e.toString(), 0);
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public T getById(int id) {
        List<T> got = getAll(new Filter[]{new Filter(entityWork.getPrimaryKey().columnName, id)});
        if (got.size() > 1) {
            throw new RuntimeException();
        }
        if(got.size() == 0){
            return null;
        }
        return got.get(0);
    }

    protected String[] getColumnValues(T object) throws IllegalAccessException {
        String[] columnValues = new String[entityWork.getFields().length];
        ColumnField[] fields = entityWork.getFields();
        for (int i = 0; i < fields.length; i++) {
            ColumnField cf = fields[i];
            cf.field.setAccessible(true);
            columnValues[i] = cf.field.get(object).toString();
        }
        return columnValues;
    }

    @Override
    public T add(T object) {
        String query;
        try {
            ColumnField pk = entityWork.getPrimaryKey();
            pk.field.setAccessible(true);
            Integer id = (Integer) pk.field.get(object);
            String[] columnValues = getColumnValues(object);
            query = queryBuilder.insert(id, columnValues);


            PreparedStatement p = connector.makeUpdate(query);
            ResultSet resultSet = p.getGeneratedKeys();

            if(!resultSet.next()) {
                throw new RuntimeException();
            }

            entityWork.getPrimaryKey().field.setAccessible(true);
            entityWork.getPrimaryKey().field.set(object, resultSet.getObject(entityWork.getPrimaryKey().columnName));

            if(resultSet.next()){
                throw new RuntimeException();
            }

            p.close();
            return object;

        } catch (IllegalAccessException | SQLException e) {
            Log.getI().log(e.toString(), 0);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String query = queryBuilder.delete(id);
        try {
            PreparedStatement p = connector.makeUpdate(query);
            int affectedRows = p.getUpdateCount();
            if(affectedRows != 1){
                throw new IllegalArgumentException();
            }
            p.close();
        } catch (SQLException e) {
            Log.getI().log(e.toString(), 0);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int id, T newer) {
        try {
            if(entityWork.getPrimaryKey().field.get(newer) != null){
                throw new IllegalArgumentException();
            }

            String query = queryBuilder.update(getColumnValues(newer), id);
            PreparedStatement p = connector.makeUpdate(query);
            int affectedRows = p.getUpdateCount();
            if(affectedRows != 1){
                throw new IllegalArgumentException();
            }
            p.close();

        } catch (SQLException | IllegalAccessException e) {
            Log.getI().log(e.toString(), 0);
            throw new RuntimeException(e);
        }
    }

    public void creteTable(){
        try {
            PreparedStatement p = connector.makeUpdate(generateCreateCode());
            p.close();
        } catch (SQLException e) {
            Log.getI().log(e.toString(), 0);
            throw new RuntimeException(e);
        }
    }

    public String generateCreateCode(){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ").append(tableName).append("(").append("\n");
        sb.append(entityWork.getPrimaryKey().columnName).append(" SERIAL PRIMARY KEY, \n");
        for (ColumnField cf : entityWork.getFields()) {
            sb.append(cf.columnName).append(" ");

            if(cf.field.getType().isAssignableFrom(Integer.class)){
                sb.append("INTEGER");
            }

            if(cf.field.getType().isAssignableFrom(Boolean.class)){
                sb.append("INTEGER");
            }

            if(cf.field.getType().isAssignableFrom(String.class)){
                sb.append("TEXT");
            }


            if(!cf.nullable){
                sb.append(" NON NULL");
            }
            sb.append(", \n");
        }
        sb.append("\n");

        for (ColumnField cf : entityWork.getFields()) {
            if(!cf.field.isAnnotationPresent(ForeignKey.class)) continue;
            ForeignKey fk = cf.field.getAnnotation(ForeignKey.class);
            EntityWork<?> entityWorkFk = new EntityWork<>(fk.table());

            sb.append("CONSTRAINT ").append(entityWork.getTableName()).append("_").append(entityWorkFk.getTableName()).append("\n");
            sb.append("\tFOREIGN KEY (").append(cf.columnName).append(")\n");


            sb.append("\tREFERENCES ").append(entityWorkFk.getTableName()).append("(").
                    append(entityWorkFk.getPrimaryKey().columnName).append(")\n");

            sb.append("\tON DELETE CASCADE, \n\n");
        }
        sb.append(")\n");

        return sb.toString();
    }
}

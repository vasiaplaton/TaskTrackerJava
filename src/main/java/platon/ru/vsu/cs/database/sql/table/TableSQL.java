package platon.ru.vsu.cs.database.sql.table;

import platon.ru.vsu.cs.database.Table;
import platon.ru.vsu.cs.database.models.BaseModel;
import platon.ru.vsu.cs.database.sql.conns.SQLConnector;
import platon.ru.vsu.cs.logger.Log;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableSQL<T extends BaseModel> implements Table<T> {
    protected SQLConnector connector;
    protected String tablename;
    protected final Class<T> clazz;
    protected String[] columnNames;

    protected final QueryBuilder queryBuilder;
    public TableSQL(SQLConnector connector, String tableName, Class<T> clazz){
        this.connector = connector;
        this.tablename = tableName;
        this.clazz = clazz;
        columnNames = getInstance().getColumnNames();

        queryBuilder = new QueryBuilder(columnNames, tableName);
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
        newObject.setId(resultSet.getInt("id"));
        String[] columnValues = new String[columnNames.length];
        for (int i = 0; i < columnNames.length; i++) {
            String c = columnNames[i];
            columnValues[i] = resultSet.getString(c);
        }

        Log.getI().log("Got " + Arrays.toString(columnValues) + " for class: " + clazz.getName(), 3);

        newObject.setColumnValues(columnValues);
        return newObject;
    }

    @Override
    public List<T> getAll(Filter[] filters) {
        List<T> result = new ArrayList<>();

        String query = queryBuilder.select(filters);

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
    public List<T> getAll() {
        return getAll(new Filter[0]);
    }

    @Override
    public T getById(int id) {
        List<T> got = getAll(new Filter[]{new Filter("id", id)});
        if (got.size() > 1) {
            throw new RuntimeException();
        }
        if(got.size() == 0){
            return null;
        }
        return got.get(0);
    }


    @Override
    public T add(T object) {
        String query = queryBuilder.insert(object.getId(), object.getColumnValues());

        try {
            PreparedStatement p = connector.makeUpdate(query);
            ResultSet resultSet = p.getGeneratedKeys();

            if(!resultSet.next()) {
                throw new RuntimeException();
            }
            object.setId(resultSet.getInt("id"));

            if(resultSet.next()){
                throw new RuntimeException();
            }

            p.close();
            return object;

        } catch (SQLException e) {
            Log.getI().log(e.toString(), 0);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {
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
        String query = queryBuilder.update(newer.getColumnValues(), id);

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
}

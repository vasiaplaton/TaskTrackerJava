package platon.ru.vsu.cs.adb_lib.annotations;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EntityWork<T> {
    protected ColumnField primaryKey;
    protected final ColumnField[] fields;

    protected final Class<T> clazz;

    public String getTableName() {
        return tableName;
    }

    protected final String tableName;


    public EntityWork(Class<T> clazz) {
        this.clazz = clazz;
        if(!clazz.isAnnotationPresent(Entity.class)){
            throw new IllegalArgumentException();
        }

        tableName = clazz.getAnnotation(Entity.class).tableName();

        primaryKey = null;
        List<ColumnField> fields = new ArrayList<>();

        for(Field field  : clazz.getDeclaredFields())
        {
            if (!field.isAnnotationPresent(Column.class)) continue;
            Column column = field.getAnnotation(Column.class);
            String customParseMethod = column.methodCustomParse();
            String customToStringMethod = column.methodCustomToString();
            if(field.isAnnotationPresent(PrimaryKey.class)) {
                if(primaryKey != null) {
                    throw new IllegalArgumentException();
                }
                primaryKey = new ColumnField(field, column.columnName(), false, customParseMethod, customToStringMethod);
            } else {
                fields.add(new ColumnField(field, column.columnName(), column.nullable(), customParseMethod, customToStringMethod));
            }

        }

        this.fields = fields.toArray(new ColumnField[0]);
    }

    public ColumnField getPrimaryKey() {
        return primaryKey;
    }

    public ColumnField[] getFields() {
        return fields;
    }

    public Class<T> getClazz() {
        return clazz;
    }
}

package platon.ru.vsu.cs.alib.annotations;

import java.lang.reflect.Field;

public class ColumnField {
    public final Field field;
    public final String columnName;

    public final boolean nullable;

    public final boolean isPrimaryKey;

    public ColumnField(Field field, String columnName, boolean nullable, boolean isPrimaryKey) {
        this.field = field;
        this.columnName = columnName;
        this.nullable = nullable;
        this.isPrimaryKey = isPrimaryKey;
    }


}

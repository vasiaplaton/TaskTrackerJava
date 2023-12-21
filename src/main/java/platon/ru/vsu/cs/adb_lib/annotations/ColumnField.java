package platon.ru.vsu.cs.adb_lib.annotations;

import java.lang.reflect.Field;

public class ColumnField {
    public final Field field;
    public final String columnName;

    public final boolean nullable;


    public final String customParseMethod;

    public final String customToStringMethod;

    public ColumnField(Field field, String columnName, boolean nullable, String customParseMethod, String customToStringMethod) {
        this.field = field;
        this.columnName = columnName;
        this.nullable = nullable;
        this.customParseMethod = customParseMethod;
        this.customToStringMethod = customToStringMethod;
    }


}

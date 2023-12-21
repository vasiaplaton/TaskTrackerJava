package platon.ru.vsu.cs.adb_lib.sql;

public class Filter {
    // TODO add query mechanism
    public final String columnName;

    public final String columnValue;

    public Filter(String columnName, Object columnValue){
        this.columnName = columnName;
        this.columnValue = String.valueOf(columnValue);
    }
}

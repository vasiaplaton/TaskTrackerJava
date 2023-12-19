package platon.ru.vsu.cs.database.sql.table;

public class Filter {
    // TODO add query mechanizm
    public final String columnName;

    public final String columnValue;

    public Filter(String columnName, String columnValue) {
        this.columnName = columnName;
        this.columnValue = columnValue;
    }

    public Filter(String columnName, Object columnValue){
        this.columnName = columnName;
        this.columnValue = String.valueOf(columnValue);
    }
}

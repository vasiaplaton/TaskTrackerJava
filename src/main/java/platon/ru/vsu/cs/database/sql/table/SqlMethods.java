package platon.ru.vsu.cs.database.sql.table;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface SqlMethods {
    @JsonIgnore
    String[] getColumnNames();

    @JsonIgnore
    String[] getColumnValues();

    void setColumnValues(String[] columnValues);
}

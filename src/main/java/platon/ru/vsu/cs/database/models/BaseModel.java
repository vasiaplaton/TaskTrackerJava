package platon.ru.vsu.cs.database.models;

import platon.ru.vsu.cs.database.sql.table.SqlMethods;

public abstract class BaseModel implements SqlMethods {
    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

}

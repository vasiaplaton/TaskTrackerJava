package platon.ru.vsu.cs.database;

import platon.ru.vsu.cs.database.models.BaseModel;
import platon.ru.vsu.cs.database.sql.table.Filter;

import java.util.List;

public interface Table<T extends BaseModel> {
    List<T> getAll();

    List<T> getAll(Filter[] filters);

    T getById(int id);

    T add(T object);

    void remove(int id);
    void update(int id, T newer);
}

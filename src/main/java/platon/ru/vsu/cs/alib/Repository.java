package platon.ru.vsu.cs.alib;

import platon.ru.vsu.cs.database.sql.table.Filter;

import java.util.List;

public interface Repository<T> {
    List<T> getAll();

    List<T> getAll(Filter[] filters);

    T getById(int id);

    T add(T object);

    void delete(int id);
    void update(int id, T newer);
}

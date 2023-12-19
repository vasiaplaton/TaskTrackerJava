package platon.ru.vsu.cs.adb_lib.repository;

import platon.ru.vsu.cs.adb_lib.sql.Filter;

import java.util.List;

public interface Repository<T> {
    List<T> getAll();

    List<T> getAll(Filter[] filters);

    T getById(int id);

    T add(T object);

    void delete(int id);
    void update(int id, T newer);
}

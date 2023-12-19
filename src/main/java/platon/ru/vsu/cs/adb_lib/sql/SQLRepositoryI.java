package platon.ru.vsu.cs.adb_lib.sql;

import platon.ru.vsu.cs.adb_lib.repository.Repository;

public interface SQLRepositoryI<T> extends Repository<T> {
    String generateCreateCode();
    void creteTable();
}

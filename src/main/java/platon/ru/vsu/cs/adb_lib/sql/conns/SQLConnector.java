package platon.ru.vsu.cs.adb_lib.sql.conns;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface SQLConnector {
    ResultSet makeQuery(String query) throws SQLException;

    PreparedStatement makeUpdate(String query) throws SQLException;
}

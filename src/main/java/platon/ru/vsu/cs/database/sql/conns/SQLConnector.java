package platon.ru.vsu.cs.database.sql.conns;

import javax.management.Query;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface SQLConnector {
    ResultSet makeQuery(String query) throws SQLException;

    PreparedStatement makeUpdate(String query) throws SQLException;
}

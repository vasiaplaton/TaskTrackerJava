package platon.ru.vsu.cs.adb_lib.sql.conns;

import platon.ru.vsu.cs.adb_lib.logger.Log;

import java.sql.*;

public class PostgreSQLConn implements SQLConnector {
    protected final String url;
    protected final Connection connection;


    private PostgreSQLConn() {
        url = "jdbc:postgresql://localhost:5432/tasks?user=admin&password=admin";
        Log.getI().log("url: " + url, 1);
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            Log.getI().log(e.toString(), 0);
            throw new RuntimeException(e);
        }
    }

    private static PostgreSQLConn INSTANCE;

    public static PostgreSQLConn getInstance(){
        if(INSTANCE == null){
            INSTANCE = new PostgreSQLConn();
        }
        return INSTANCE;
    }

    @Override
    public ResultSet makeQuery(String query) throws SQLException {
        Log.getI().log(query, 2);

        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    @Override
    public PreparedStatement makeUpdate(String query) throws SQLException {
        Log.getI().log(query, 2);

        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.execute();
        return statement;
    }
}

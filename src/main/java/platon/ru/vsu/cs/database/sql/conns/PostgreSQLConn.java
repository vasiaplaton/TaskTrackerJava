package platon.ru.vsu.cs.database.sql.conns;

import platon.ru.vsu.cs.logger.Log;

import java.sql.*;

public class PostgreSQLConn implements SQLConnector {
    protected final String url;
    protected final Connection connection;


    private PostgreSQLConn() throws SQLException {
        url = "jdbc:postgresql://localhost:5432/tasks?user=admin&password=admin";
        Log.getI().log("url: " + url, 1);
        connection = DriverManager.getConnection(url);
    }

    private static PostgreSQLConn INSTANCE;

    public static PostgreSQLConn getInstance() throws SQLException {
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

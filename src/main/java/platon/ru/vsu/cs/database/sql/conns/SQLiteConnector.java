package platon.ru.vsu.cs.database.sql.conns;

import platon.ru.vsu.cs.logger.Log;

import java.sql.*;

public class SQLiteConnector implements SQLConnector {
    protected final String url;
    protected final Connection connection;


    private SQLiteConnector() throws SQLException {
        String path = "base.db";
        url = "jdbc:sqlite:" + path;

        connection = DriverManager.getConnection(url);
    }

    private static SQLiteConnector INSTANCE;

    public static SQLiteConnector getInstance() throws SQLException {
        if(INSTANCE == null){
            INSTANCE = new SQLiteConnector();
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

        String[] returnId = { "BATCHID" };
        PreparedStatement statement = connection.prepareStatement(query, returnId);
        statement.execute();
        return statement;
    }
}

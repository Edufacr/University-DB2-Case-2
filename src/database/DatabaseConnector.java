package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnector implements ICredentials {
    private Connection connection = null;

    public DatabaseConnector () {
        connect();
    }

    private void connect() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet query(String pQuery) {
        ResultSet result = null;

        try {
            Statement statement = this.connection.createStatement();
            result = statement.executeQuery(pQuery);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }
}
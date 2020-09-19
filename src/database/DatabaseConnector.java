package database;

import java.sql.*;

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

    public Connection getConnection() {
        return connection;
    }
}
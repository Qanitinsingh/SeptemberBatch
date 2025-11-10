package database.testing;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class SqlConnector {
    protected static Connection connection;

    public static Connection createConnection() throws Exception {

        if (connection == null || connection.isClosed()) {

            Properties prop = new Properties();
            FileInputStream ip = new FileInputStream("src/test/resources/sqlconfig.properties");
            prop.load(ip);

            String dbURL = prop.getProperty("db.url");
            String dbUser = prop.getProperty("db.username");
            String dbPass = prop.getProperty("db.password");

            connection = DriverManager.getConnection(dbURL, dbUser, dbPass);
        }

        return connection;
    }


    public static void closeConnection() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}

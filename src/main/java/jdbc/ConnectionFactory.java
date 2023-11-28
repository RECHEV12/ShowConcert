package jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.DriverManager;


public class ConnectionFactory {
    private String url;
    private String id;
    private String pw;
    private int maxConn;

    public int getMaxConn() {
        return maxConn;
    }

    private ConnectionFactory() {
        Properties prop = new Properties();
        InputStream input = ConnectionFactory.class.getResourceAsStream("/config/context-db.properties");


        try {
            prop.load(input);

            this.url = prop.getProperty("url");
            this.id = prop.getProperty("id");
            this.pw = prop.getProperty("pw");
            this.maxConn = Integer.parseInt(prop.getProperty("maxConn"));

            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("성공");
        } catch (IOException e) {
            System.exit(0);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    private static ConnectionFactory instance = new ConnectionFactory();

    public static ConnectionFactory getInstance() {
        return instance;
    }

    public Connection getConnection()  {
        try {
            return DriverManager.getConnection(url, id, pw);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

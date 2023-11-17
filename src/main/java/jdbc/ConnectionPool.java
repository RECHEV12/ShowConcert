package jdbc;

import java.sql.Connection;
import java.util.Vector;

public class ConnectionPool {
    private ConnectionPool() {
        initPool();
    }

    private static ConnectionPool instance = new ConnectionPool();

    public static ConnectionPool getInstance() {
        return instance;
    }

    Vector<Connection> pool = new Vector<>();

    private void initPool() {
        ConnectionFactory cf = ConnectionFactory.getInstance();

        int maxConn = cf.getMaxConn();

        for (int i = 0; i < maxConn; i++) {
            pool.add(cf.getConnection());
        }

    }

    public synchronized Connection getConnection() {

        if (pool.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        Connection conn = pool.get(0);
        pool.remove(0);
        return conn;
    }

    public synchronized void realeaseConnection(Connection conn){
        pool.add(conn);
        notify();
    }


}

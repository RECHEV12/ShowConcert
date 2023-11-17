package concert.service;

import concert.dao.ConcertDAO;
import concert.dto.ConcertDTO;
import jdbc.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class ConcertService {
    private ConcertService(){

    }
    private static ConcertService instance = new ConcertService();
    public static ConcertService getInstance(){
        return instance;
    }
    private ConnectionPool cp = ConnectionPool.getInstance();
    private ConcertDAO dao = ConcertDAO.getInstance();


    public void insertConcert(ConcertDTO concert){
        Connection conn = cp.getConnection();

        try {
            dao.insertConcert(conn, concert);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            cp.realeaseConnection(conn);
        }
    }
}

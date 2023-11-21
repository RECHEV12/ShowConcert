package concert.service;

import concert.dao.ConcertDAO;
import concert.dto.ConcertDTO;
import jdbc.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConcertService {
    private ConcertService() {

    }

    private static ConcertService instance = new ConcertService();

    public static ConcertService getInstance() {
        return instance;
    }

    private ConnectionPool cp = ConnectionPool.getInstance();
    private ConcertDAO dao = ConcertDAO.getInstance();


    public void insertConcert(ConcertDTO concert) {
        Connection conn = cp.getConnection();

        try {
            dao.insertConcert(conn, concert);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            cp.realeaseConnection(conn);
        }
    }

    public ArrayList<ConcertDTO> selectDay(String startDay, String endDay) {
        Connection conn = cp.getConnection();
        ArrayList<ConcertDTO> result;

        try {
            result = dao.selectDay(conn, startDay, endDay);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            cp.realeaseConnection(conn);
        }

        return result;
    }

    public ArrayList<ConcertDTO> allDay(){
        Connection conn = cp.getConnection();
        ArrayList<ConcertDTO> result;

        try {
            result = dao.selectAllDay(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            cp.realeaseConnection(conn);
        }

        return result;
    }
}

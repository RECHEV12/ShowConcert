package concert.dao;

import concert.dto.ConcertDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConcertDAO {
    private ConcertDAO() {
    }

    private static ConcertDAO instance = new ConcertDAO();

    public static ConcertDAO getInstance() {
        return instance;
    }

    /**
     * 데이터 입력 쿼리문
     *
     * @param conn
     * @param concert
     */
    public void insertConcert(Connection conn, ConcertDTO concert) throws SQLException {
        StringBuffer query = new StringBuffer();

        query.append("INSERT INTO        ");
        query.append("  concerts(        ");
        query.append("      co_period,   ");
        query.append("      co_tel,      ");
        query.append("      co_evPeriod, ");
        query.append("      co_title,    ");
        query.append("      co_url,      ");
        query.append("       co_imgUrl,  ");
        query.append("      co_charge,   ");
        query.append("      co_type,     ");
        query.append("      co_eventSite ");
        query.append("       )           ");
        query.append("VALUES(            ");
        query.append("      ?,           ");
        query.append("      ?,           ");
        query.append("      ?,           ");
        query.append("      ?,           ");
        query.append("      ?,           ");
        query.append("      'https://www.mcst.go.kr/attachFiles/cultureInfoCourt/monthServ/1699947256023.jpg'");
        query.append("      ?,           ");
        query.append("      ?,           ");
        query.append("      ?,           ");
        query.append("      ?,           ");
        query.append("      )");

        PreparedStatement ps = conn.prepareStatement(query.toString());

        int idx = 1;

        ps.setString(idx++, concert.getCo_period());
        ps.setString(idx++, concert.getCo_tel());
        ps.setLong(idx++, concert.getCo_evPeriod());
        ps.setString(idx++, concert.getCo_title());
        ps.setString(idx++, concert.getCo_url());
        ps.setLong(idx++, concert.getCo_charge());
        ps.setString(idx++, concert.getCo_type());
        ps.setString(idx++, concert.getCo_eventSite());

        ps.executeUpdate();

        ps.close();

    }

    /**
     * 날짜 처음 끝 있을 때
     * @param startDay 시작날짜
     * @param endDay 종료 날짜
     * @return 콘서트 객체 배열
     */
    public ArrayList<ConcertDTO> selectDay(Connection conn, String startDay, String endDay) throws SQLException {
        StringBuffer query = new StringBuffer();

        query.append("  SELECT               ");
        query.append("      co_period,       ");
        query.append("      co_tel,          ");
        query.append("      co_evPeriod,     ");
        query.append("      co_title,        ");
        query.append("      co_url,          ");
        query.append("       co_imgUrl,      ");
        query.append("      co_charge,       ");
        query.append("      co_type,         ");
        query.append("      co_eventSite     ");
        query.append("  FROM                 ");
        query.append("      CONCERTS         ");
        query.append("  WHERE 1=1            ");
        query.append("  AND CO_PERIOD LIKE ? ");
        query.append("  OR                   ");
        query.append("      CO_PERIOD LIKE ? ");


        PreparedStatement ps = conn.prepareStatement(query.toString());

        int idx = 1;

        ps.setString(idx++, startDay + "%");
        ps.setString(idx++, "%" + endDay);

        ResultSet rs = ps.executeQuery();

        ArrayList<ConcertDTO> selectedDate = new ArrayList<>();

        while (rs.next()) {
            String period = rs.getString("co_period");
            String tel = rs.getString("co_tel");
            long evPeriod = rs.getInt("co_evPeriod");
            String title = rs.getString("co_title");
            String url = rs.getString("co_url");
            String imgUrl = rs.getString("co_imgUrl");
            long charge = rs.getInt("co_charge");
            String type = rs.getString("co_type");
            String eventSite = rs.getString("co_eventSite");
            System.out.println(period);
            System.out.println(title);


            ConcertDTO concert = new ConcertDTO(period, tel, evPeriod, title, url, imgUrl, charge, type, eventSite);
            selectedDate.add(concert);
        }

        rs.close();
        ps.close();

        return selectedDate;

    }

    /**
     * 시작일만 있을 경우 사용
     * @return 콘서트 객체 배열
     */
    public ArrayList<ConcertDTO> selectAllDay(Connection conn) throws SQLException {
        StringBuffer query = new StringBuffer();

        query.append("  SELECT               ");
        query.append("      co_period,       ");
        query.append("      co_tel,          ");
        query.append("      co_evPeriod,     ");
        query.append("      co_title,        ");
        query.append("      co_url,          ");
        query.append("       co_imgUrl,      ");
        query.append("      co_charge,       ");
        query.append("      co_type,         ");
        query.append("      co_eventSite     ");
        query.append("  FROM                 ");
        query.append("      CONCERTS         ");



        PreparedStatement ps = conn.prepareStatement(query.toString());

        ResultSet rs = ps.executeQuery();

        ArrayList<ConcertDTO> selectedDate = new ArrayList<>();

        while (rs.next()) {
            String period = rs.getString("co_period");
            String tel = rs.getString("co_tel");
            long evPeriod = rs.getInt("co_evPeriod");
            String title = rs.getString("co_title");
            String url = rs.getString("co_url");
            String imgUrl = rs.getString("co_imgUrl");
            long charge = rs.getInt("co_charge");
            String type = rs.getString("co_type");
            String eventSite = rs.getString("co_eventSite");



            ConcertDTO concert = new ConcertDTO(period, tel, evPeriod, title, url, imgUrl, charge, type, eventSite);
            selectedDate.add(concert);
        }

        rs.close();
        ps.close();

        return selectedDate;

    }
}

package concert.dao;

import concert.dto.ConcertDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConcertDAO {
    private ConcertDAO() {
    }

    private static ConcertDAO instance = new ConcertDAO();

    public static ConcertDAO getInstance() {
        return instance;
    }

    /**
     * 데이터 입력 쿼리문
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
        query.append("       co_imgUrl   ");
        query.append("       )           ");
        query.append("VALUES(            ");
        query.append("      ?,           ");
        query.append("      ?,           ");
        query.append("      ?,           ");
        query.append("      ?,           ");
        query.append("      ?,           ");
        query.append("      'https://www.mcst.go.kr/attachFiles/cultureInfoCourt/monthServ/1699947256023.jpg')");

        PreparedStatement ps = conn.prepareStatement(query.toString());

        int idx = 1;

        ps.setString(idx++,concert.getCo_period());
        ps.setString(idx++,concert.getCo_tel());
        ps.setLong(idx++,concert.getCo_evPeriod());
        ps.setString(idx++,concert.getCo_title());
        ps.setString(idx++,concert.getCo_url() );

        ps.executeUpdate();

        ps.close();

    }

}

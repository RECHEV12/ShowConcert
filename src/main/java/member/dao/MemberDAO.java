package member.dao;


import member.dto.MemberDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// dao = data access object
// 쿼리문을 작성해놓는 클래스
// member 테이블과 연관된 쿼리문만을 작성해놓음
public class MemberDAO {

    private MemberDAO() {

    }

    private static MemberDAO instance = new MemberDAO();

    public static MemberDAO getInstance() {
        return instance;
    }

    //DAO의 메소드에서는 파라미터에 Connection 객체가 들어올 예정
    // 회원 목록 조회(SELECT)
    public ArrayList<MemberDTO> getMemberList(Connection conn) throws SQLException {
        // 쿼리문 작성

        StringBuffer query = new StringBuffer();

        query.append("SELECT                 ");
        query.append("  mem_id,              ");
        query.append("  mem_pw,              ");
        query.append("  mem_name,            ");
        query.append("  mem_score AS score   ");
        query.append("  FROM                 ");
        query.append("  members              ");

        // Connection  객체로부터 PreparedStatment 객체 생성
        PreparedStatement ps = conn.prepareStatement(query.toString());

        // 쿼리문 실행
        ResultSet rs = ps.executeQuery();

        //MemberDTO  객체들을 담을 수 있는 ArrayList 생성
        ArrayList<MemberDTO> result = new ArrayList<>();


        // 결과 처리
        while (rs.next()) {
            String memId = rs.getString("mem_id");
            String memPw = rs.getString("mem_pw");
            String memName = rs.getString("mem_name");
            int memScore = rs.getInt("score");

            MemberDTO member = new MemberDTO(memId, memPw, memName, memScore);
            result.add(member);

        }

        rs.close();
        ps.close();


        return result;

    }


    // 비밀번호 찾기
    public String getMyPw(Connection conn, String id, String name) throws SQLException {
        // 쿼리문 작성

        StringBuffer query = new StringBuffer();

        query.append("SELECT                 ");
        query.append("  mem_pw               ");
        query.append("FROM                   ");
        query.append("  members              ");
        query.append("  WHERE  1=1           ");
        query.append("  AND mem_id = ?       ");
        query.append("  AND mem_name = ?     ");
        // Connection  객체로부터 PreparedStatment 객체 생성
        PreparedStatement ps = conn.prepareStatement(query.toString());

        int idx = 1;
        ps.setString(idx++, id);
        ps.setString(idx++, name);

        // 쿼리문 실행
        ResultSet rs = ps.executeQuery();



        String memPw = "";
        // 결과 처리

        while (rs.next()) {
            memPw = rs.getString("mem_pw");
        }

        rs.close();
        ps.close();


        return memPw;

    }


    // 회원 가입 메소드(INSERT)
    public int signUp(Connection conn, MemberDTO member) throws SQLException {
        StringBuffer query = new StringBuffer();

        query.append("INSERT INTO            ");
        query.append("  members(             ");
        query.append("  mem_id,              ");
        query.append("  mem_pw,              ");
        query.append("  mem_name,            ");
        query.append("  mem_score)           ");
        query.append("  VALUES(              ");
        query.append("   ?,                  ");
        query.append("    ?,                 ");
        query.append("    ?,                 ");
        query.append("    ?                  ");
        query.append("    )                  ");

        PreparedStatement ps = conn.prepareStatement(query.toString());

        int idx = 1;
        ps.setString(idx++, member.getMemId());
        ps.setString(idx++, member.getMemPw());
        ps.setString(idx++, member.getMemName());
        ps.setInt(idx++, member.getMemScore());

        int result = ps.executeUpdate();

        ps.close();

        return result;

    }

    //  로그인 (SELECT ,WHERE)
    public MemberDTO login(Connection conn, MemberDTO member) throws SQLException {
        StringBuffer query = new StringBuffer();

        query.append("SELECT                 ");
        query.append("  mem_id,              ");
        query.append("  mem_pw,              ");
        query.append("  mem_name,            ");
        query.append("  mem_score AS score   ");
        query.append("  FROM                 ");
        query.append("  members              ");
        query.append("  WHERE  1=1           ");
        query.append("  AND mem_id = ?       ");
        query.append("  AND mem_pw = ?       ");

        PreparedStatement ps = conn.prepareStatement(query.toString());
        int idx = 1;
        ps.setString(idx++, member.getMemId());
        ps.setString(idx++, member.getMemPw());

        ResultSet rs = ps.executeQuery();

        // 빈껍데기 MemberDTO 객체 생성
        MemberDTO result = new MemberDTO();

        if (rs.next()) {
            // 로그인 성공
            result.setMemId(rs.getString("mem_id"));
            result.setMemPw(rs.getString("mem_pw"));
            result.setMemName(rs.getString("mem_name"));
            result.setMemScore(rs.getInt("score"));

        }

        rs.close();
        ps.close();

        return result;
    }

    // 아이디 중복 체크
    public int dupleCheck(Connection conn, String id) throws SQLException {
        StringBuffer query = new StringBuffer();

        query.append("SELECT                 ");
        query.append("  COUNT(*) AS count    ");
        query.append("  FROM                 ");
        query.append("  members              ");
        query.append("  WHERE  1=1           ");
        query.append("  AND mem_id = ?       ");


        PreparedStatement ps = conn.prepareStatement(query.toString());
        int idx = 1;
        ps.setString(idx++, id);


        ResultSet rs = ps.executeQuery();
        int result = 0;
        if (rs.next()) {
            result = rs.getInt("count");
        }

        rs.close();
        ps.close();

        return result;
    }


}

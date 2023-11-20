package member.service;

// ConnectionPool로부터 Connection 객체 대여/반납 해가며
// DAO의 메소드에 대한 예외처리 담당



import jdbc.ConnectionPool;
import member.dao.MemberDAO;
import member.dto.MemberDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberService {


    private MemberService() {

    }

    private static MemberService instance = new MemberService();

    public static MemberService getInstance() {
        return instance;
    }

    private ConnectionPool cp = ConnectionPool.getInstance();
    private MemberDAO dao = MemberDAO.getInstance();

    public MemberDTO login;

    public ArrayList<MemberDTO> getMemberList() {
        Connection conn = cp.getConnection();
        ArrayList<MemberDTO> result;
        try {
            result = dao.getMemberList(conn);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            cp.realeaseConnection(conn);
        }

        return result;
    }



    // 회원가입
    public boolean singUp(MemberDTO member) {
        Connection conn = cp.getConnection();
        boolean result = false;
        try {
            int cnt = dao.signUp(conn, member);
            if (cnt == 1) {
                result = true;
            }
            System.out.println("회원가입에 성공하였습니다.");
        } catch (SQLException e) {
            // 중복된 아이디가 입력되었으면 에러가 발생
            System.out.println("회원가입에 실패하였습니다.");
            System.out.println("아이디가 중복됩니다.");
        } finally {
            cp.realeaseConnection(conn);
        }
        return result;
    }

    // 로그인
    public MemberDTO login(MemberDTO member) {
        Connection conn = cp.getConnection();
        MemberDTO result;
        try {
            result = dao.login(conn, member);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            cp.realeaseConnection(conn);
        }

        return result;

    }

    public boolean dupleCheck(String id) {
        // 중복되면 true, 아니면 false
        Connection conn = cp.getConnection();
        int duple;
        boolean result;

        try {
            duple = dao.dupleCheck(conn, id);

            if (duple == 1) {
                result = true;
            } else {
                result = false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            cp.realeaseConnection(conn);
        }


        return result;


    }

    public String getMyPw(String id, String name){
        Connection conn = cp.getConnection();;
        String result;
        try {
           result =  dao.getMyPw(conn, id, name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            cp.realeaseConnection(conn);
        }

        return result;
    }


}
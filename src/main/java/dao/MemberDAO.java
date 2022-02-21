package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.mindrot.jbcrypt.BCrypt;

import vo.MemberVo;

public class MemberDAO {
	
//	멤버변수 DataSource 관련 참조변수 
	Context initCtx;
	Context envCtx;
	DataSource ds;
	
//	멤버변수 SQL 쿼리 관련 참조변수
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
//	싱글톤 패턴 시작
	private static MemberDAO instance = new MemberDAO();
	private MemberDAO() {
		try{
			initCtx = new InitialContext();
			envCtx = (Context)initCtx.lookup("java:comp/env");
			ds = (DataSource)envCtx.lookup("jdbc/MysqlDB");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public static MemberDAO getInstance() {
		if(instance==null) {
			instance=new MemberDAO();
		}
		return instance;
	}
//	싱글톤 패턴 끝
	

//	회원 가입
	public boolean MemberJoin(MemberVo vo) {
		boolean flag=false;
		try{
			conn=ds.getConnection();
			pstmt=conn.prepareStatement("insert into member_tbl values(?,?,?,?,?,?)");
			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, BCrypt.hashpw(vo.getPwd(), BCrypt.gensalt()));
			pstmt.setInt(3, vo.getZipcode());
			pstmt.setString(4, vo.getAddr1());
			pstmt.setString(5, vo.getAddr2());
			pstmt.setString(6, "ROLE_USER");
			int result = pstmt.executeUpdate();
			if(result!=0) {
				flag=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
//	회원 조회
	public MemberVo MemberSearch(String email) {
		MemberVo vo = null;
		try {
			conn=ds.getConnection();
			pstmt=conn.prepareStatement("select * from member_tbl where email=?");
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					vo=new MemberVo(
							rs.getString("email"),
							rs.getString("pwd"),
							rs.getInt("zipcode"),
							rs.getString("addr1"),
							rs.getString("addr2")
							);
					vo.setRole(rs.getString("role"));
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
//	회원 수정
//	회원 삭제
}

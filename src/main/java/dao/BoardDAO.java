package dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import vo.BoardVO;
import vo.MemberVo;
import vo.ReplyVO;

public class BoardDAO {

//	멤버변수 DataSource 관련 참조변수 
	Context initCtx;
	Context envCtx;
	DataSource ds;
	
//	멤버변수 SQL 쿼리 관련 참조변수
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
//	업로드 경로 추가
	public static final String SAVEFOLDER = "/upload";
	
//	싱글톤 패턴 시작
	private static BoardDAO instance = new BoardDAO();
	private BoardDAO() {
		try{
			initCtx = new InitialContext();
			envCtx = (Context)initCtx.lookup("java:comp/env");
			ds = (DataSource)envCtx.lookup("jdbc/MysqlDB");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public static BoardDAO getInstance() {
		if(instance==null) {
			instance=new BoardDAO();
		}
		return instance;
	}
//	싱글톤 패턴 끝
	
	
	
	//게시물 읽어오기(Start, end)
	public Vector<BoardVO> getBoardList(int start, int end){
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		Vector<BoardVO> list = new Vector();
	
		try {
			conn=ds.getConnection();
			pstmt=conn.prepareStatement("select * from board_tbl order by num desc limit ?,?");
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs=pstmt.executeQuery();
			
			if(rs!=null) {
				while(rs.next()) {
					//읽어온 테이블 각행의 값을 BoardVO로 저장
					BoardVO vo = new BoardVO(
						rs.getInt("num"),
						rs.getString("email"),
						rs.getString("pwd"),
						rs.getString("subject"),
						rs.getString("content"),
						rs.getString("regdate"),
						rs.getString("ip"),
						rs.getInt("count"),
						rs.getString("filename"),
						rs.getInt("filesize")
					);
					
					//list에 넣기
					list.add(vo);
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {rs.close();}catch(Exception e) {}
			try {pstmt.close();}catch(Exception e) {}
			try {conn.close();}catch(Exception e) {}
		}
		return list;
	}
	
	
	//전체 게시물 갯수 받아오기
	public int getTotalCount(){
			
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int cnt=0;
			
		try {
				conn=ds.getConnection();
				pstmt=conn.prepareStatement("select count(*) from board_tbl");
				rs=pstmt.executeQuery();
				
				if(rs!=null) {
					while(rs.next()) {
						cnt=rs.getInt(1);
					}
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {rs.close();}catch(Exception e) {}
				try {pstmt.close();}catch(Exception e) {}
				try {conn.close();}catch(Exception e) {}
			}
			return cnt;
		}
	
	
	
	
	
	public void BoardPost(HttpServletRequest req) {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		int filesize=0;
		String filename=null;
		try {
			
			conn=ds.getConnection();
			
			//업로드 폴더 생성(미존재시)
			HttpSession session = req.getSession();
			MemberVo vo = (MemberVo)session.getAttribute("vo");
			String email = vo.getEmail();
			
			File updir = new File(SAVEFOLDER+File.separator+email);
			if(updir.exists()==false) {
				updir.mkdirs();
			}
			
			//파일 추출
			Part part = req.getPart("uploadfile");	//post.jsp에서 뽑아옴
			
			//파일 이름 추출
			filename=getFilename(part);
			
			//파일 업로드 할게 있는지 여부 판단
			if(filename!=null) {
				
				//업로드-filename이 null값이 아니라면,
				part.write(updir+File.separator+filename);
			}else {
				//filename의 값이 null이라면,
				filename="파일 없음";
				System.out.println("TEST!!!!!");
			}
			
			//DB에 게시물 저장
			pstmt=conn.prepareStatement("insert into board_tbl values(null,?,?,?,?,now(),?,0,?,?)");
			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, req.getParameter("pwd"));
			pstmt.setString(3, req.getParameter("subject"));
			pstmt.setString(4, req.getParameter("content"));
			pstmt.setString(5, req.getParameter("ip"));
			pstmt.setString(6, filename);
			filesize=(int)part.getSize();
			pstmt.setInt(7, filesize);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {pstmt.close();} catch(Exception e1) {}
			try {conn.close();} catch(Exception e1){}
		}
	}
	
	//파일 이름 추출
	private String getFilename(Part part) {
		System.out.println("----파일이름 추출 함수로 진입----");
		String contentDisp=part.getHeader("content-disposition");
		String[] tokens=contentDisp.split(";");				// ";" 을 기준으로 나누겠다는 뜻
		
		//filename 추출
		String filename=tokens[2];
	
		System.out.println(filename);
		if(filename.equals(" filename=\"\"")) {
			return null;
		}else {
		
			//확장자 추출하기
			int idx=filename.lastIndexOf(".");		//확장자 임시 저장
			String tmp=filename.substring(idx, filename.length()-1);
			System.out.println("파일확장자 : " +tmp);
		
			//UUID
			UUID rand=UUID.randomUUID();
			//파일명_UUID.확장자
		
			//파일 이름(확장자를 제외)
			filename = filename.substring(11, idx);
			System.out.println("Filename : " + filename);
			System.out.println("----파일이름 추출 함수 끝----");
			return filename+"_"+rand+tmp;
		}
	}
	
	//게시물 UpCount
	public void UpCount(int num) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=ds.getConnection();
			pstmt=conn.prepareStatement("update board_tbl set count=count+1 where num=?");
			pstmt.setInt(1,  num);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {pstmt.close();}catch(Exception e) {}
			try {conn.close();}catch(Exception e) {}
		}
	}
	
	//게시물 READ
	public BoardVO getBoardVO(int num) {
		
		//카운트 증가
		UpCount(num);
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		BoardVO vo=null;
		
		try {
			conn=ds.getConnection();
			pstmt=conn.prepareStatement("select * from board_tbl where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs!= null) {
				while(rs.next()) {
					vo = new BoardVO(
							rs.getInt("num"),
							rs.getString("email"),
							rs.getString("pwd"),
							rs.getString("subject"),
							rs.getString("content"),
							rs.getString("regdate"),
							rs.getString("ip"),
							rs.getInt("count"),
							rs.getString("filename"),
							rs.getInt("filesize")
							);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {rs.close();}catch(Exception e1) {}
			try {pstmt.close();}catch(Exception e1) {}
			try {conn.close();}catch(Exception e1) {}
		}
		return vo;
	}
	
	//게시물 update
	public void BoardUpdate(BoardVO vo) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=ds.getConnection();
			pstmt=conn.prepareStatement("update board_tbl set subject=?, content=? where num=?");
			pstmt.setString(1, vo.getSubject());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getNum());
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {pstmt.close();}catch(Exception e1) {}
			try {conn.close();}catch(Exception e1) {}
		}
	}
	
	//게시물 Delete
	public void BoardDelete(int num) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		try {
			conn=ds.getConnection();
			
			//파일 이름 조회해서 해당 파일 삭제
			pstmt=conn.prepareStatement("select email, filename from board_tbl where num=?");
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			
			String email=null;
			String filename=null;
			if(rs!=null) {
				while(rs.next()) {
					email=rs.getString("email");
					filename=rs.getString("filename");
				}
			}
			if(!filename.equals("NONE")) {
				//파일 위치 찾기
				File file = new File(SAVEFOLDER+File.separator+email+File.separator+filename);
				if(file.exists()) {
					file.delete();
				}
			}
			//게시물 삭제
			pstmt=conn.prepareStatement("delete from board_tbl where num=?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {pstmt.close();}catch(Exception e) {}
			try {pstmt.close();}catch(Exception e) {}
			try {conn.close();}catch(Exception e) {}
		}
	}
	
	//reply_post
	public void ReplyPost(ReplyVO vo) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			
			conn=ds.getConnection();
			pstmt=conn.prepareStatement("insert into reply_tbl values(null, ?,?,?,now())");
			pstmt.setInt(1, vo.getBnum());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getComment());
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			
		}finally {
			try {pstmt.close();} catch(Exception e) {}
			try {conn.close();} catch(Exception e) {}
		}
	}
	
	//reply_list
	public Vector<ReplyVO> getReplyList(int num) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		Vector<ReplyVO> list = new Vector();
		ReplyVO vo = null;
		
		
		try {
			
			conn=ds.getConnection();
			pstmt=conn.prepareStatement("select * from reply_tbl where b_num=? order by r_num desc");
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					vo=new ReplyVO(
							rs.getInt("b_num"),
							rs.getString("writer"),
							rs.getString("comment"),
							rs.getString("reg_date")
							);
					list.add(vo);
				}
			}
			
		}catch(Exception e) {
			
		}finally {
			try {rs.close();} catch(Exception e) {}
			try {pstmt.close();} catch(Exception e) {}
			try {conn.close();} catch(Exception e) {}
		}
		return list;
	}
}


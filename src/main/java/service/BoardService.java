package service;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Vector;

import dao.BoardDAO;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vo.BoardVO;
import vo.ReplyVO;

public class BoardService {

	//멤버 변수 추가
	BoardDAO dao;
	
	//	싱글톤 패턴 시작
	private static BoardService instance = new BoardService();
	private BoardService() {
		dao=BoardDAO.getInstance();
	};
	public static BoardService getInstance() {
		if(instance==null) {
			instance = new BoardService();
		}
		return instance;
	}
	//	싱글톤 패턴 끝
	
	
	
	
	//게시물 읽어오기
	//public 반환형? getBoardList(int start, int end)
	public Vector<BoardVO> getBoardList(int start, int end){
		return dao.getBoardList(start, end);
	}
	
	//전체 게시물 개수 가져오기
	public int getTotalCount() {
		return dao.getTotalCount();
	}
	
	//게시물 가져오기 Post
	public void BoardPost(HttpServletRequest req) {
		
		//DAO에서 req로 그대로 넘기기
		dao.BoardPost(req);
	}
	
	//게시물 READ
	public BoardVO getBoardVO(int num) {
		return dao.getBoardVO(num);
	}
	
	//게시물 update
	public void BoardUpdate(BoardVO vo) {
		dao.BoardUpdate(vo);
	}
	
	//게시물 delete
	public void BoardDelete(int num) {
		dao.BoardDelete(num);
	}
	
	//게시물의 파일 다운로드
	public void Download(HttpServletRequest req, HttpServletResponse resp) {
		String savedir = BoardDAO.SAVEFOLDER;
		HttpSession session = req.getSession();
		BoardVO vo = (BoardVO)session.getAttribute("BoardVO");
		
		String filename=vo.getFilename();
		String filepath=savedir+File.separator+vo.getEmail()+File.separator+filename;
		
		System.out.println(filename);
		
			try {
			//한글파일 인코딩
			filename = URLEncoder.encode(filename, "utf-8").replaceAll("\\+", "%20");
		
			//MIME 타입 설정
			resp.setContentType("application/octet-stream");
		
			//헤더 설정
			resp.setHeader("Content-Disposition", "attachment; fileName= " + filename);
		
		
			//파일을 받을 버퍼: 4kbyte로 받도록 설정
			byte[] buff = new byte[4096];
		
			//파일의 경로에서 server로 보내는 stream만듦
			FileInputStream fin = new FileInputStream(filepath);
		
			//클라이이너트로 부터 보낼 경로 -> bout(브라우저아웃)
			ServletOutputStream bout = resp.getOutputStream();
		
			int read;
			while(true) {
				//파일 방향에서 서버방향으로 데이터 스트림이 만들어지면 읽겠다는 것!
				//그리고 만들어지면 buff에 담음 -> 0번째 인덱스부터 길이만큼
				//잘 받으면 read로 그 값을 리턴할 것이다
				read = fin.read(buff,0,buff.length);
				//근데 안에 내용이 없으면 -1을 보내고 반복문 해제	
				if(read==-1) {
					break;
				}
				//브라우져로 날려줌
				bout.write(buff, 0, read);
			}
			bout.flush();
			bout.close();
			fin.close();
		
			System.out.println("다운로드 완료");
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//reply
	public void ReplyPost(ReplyVO vo) {
		dao.ReplyPost(vo);
	}
	
	//reply_list
	public Vector<ReplyVO> getReplyList(int num){
		return dao.getReplyList(num);
	}
}

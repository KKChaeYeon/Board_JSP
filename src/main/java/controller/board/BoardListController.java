package controller.board;

import java.util.Vector;

import controller.Controller;
import controller.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardService;
import vo.BoardVO;

public class BoardListController implements Controller{

	@Override
	public void Execute(HttpServletRequest req, HttpServletResponse resp) {
		
		//파라미터값 받아오기(게시물 시작 위치, 읽어들일 개수)
		String s=req.getParameter("start");
		String e=req.getParameter("end");
		String n=req.getParameter("nowPage");
		
		int start=0;	//해당 페이지의 시작번호
		int end=10;		//해당 페이지의 끝번호
		int nowPage=1;	//현재 페이지
		
		
		//입력값 검증
		if(n != null) {
			nowPage=Integer.parseInt(n);
			start=Integer.parseInt(s);
			end=Integer.parseInt(e);
		}
		
		//서비스 실행
		BoardService service = BoardService.getInstance();
		Vector<BoardVO> list = service.getBoardList(start, end);
		int tcnt=service.getTotalCount();
		
		
		//View 이동
		req.setAttribute("list", list);
		req.setAttribute("tcnt", tcnt);
		HttpUtil.Forward(req, resp, "/WEB-INF/View/board/list.jsp");
		
	}

}

package controller.board;

import java.io.IOException;

import controller.Controller;
import controller.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.BoardService;
import vo.BoardVO;

public class BoardUpdateController implements Controller{
	
	//UpdatwReq에서 update 요청 "/Board/Update.do"  ->   FrontController    ->   BoardUpdateController 이동
	@Override
	public void Execute(HttpServletRequest req, HttpServletResponse resp) {
		
		//
		String flag=req.getParameter("flag");
		if(flag!=null)	//update.jsp form 띄우기
		{
			HttpUtil.Forward(req, resp, "/WEB-INF/View/board/update.jsp");
			System.out.println("!!!!!!!!!");
		}
		else	//update 버튼을 누른 경우
		{
			//파라미터 = 읽고 있는 게시물
			String subject = req.getParameter("subject");
			String content = req.getParameter("content");
			
			//입력값
			if(subject.isEmpty()||content.isEmpty()) {
				//잘못된 입력 페이지 처리
				//updateform페이지로 이동
			}
			//서비스
			BoardService service = BoardService.getInstance();
			HttpSession session = req.getSession();
			BoardVO boardvo = (BoardVO)session.getAttribute("BoardVO");	//현재 읽고 있는 게시물 꺼내기
			BoardVO vo = new BoardVO(boardvo.getNum(), subject, content);	//게시물을 꺼내오기 위해서 vo에 객체 생성
			service.BoardUpdate(vo);
			
			//이동(읽고 있는 페이지로 이동)
			try {
				//Redirect를 하면 리셋이 되기때문에 다시 셋
				int num=boardvo.getNum();
				String nowPage = req.getParameter("nowPage");
				String start = req.getParameter("start");
				String end = req.getParameter("end");
				String url="/Board/read.do?nowPage="+nowPage+"&start="+start+"&end="+end+"&num="+num;
				
				resp.sendRedirect(url);
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

	
}

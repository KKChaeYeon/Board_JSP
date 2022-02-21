package controller.board;

import java.io.IOException;

import controller.Controller;
import controller.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.BoardService;
import vo.BoardVO;

public class BoardDeleteController implements Controller{

	@Override
	public void Execute(HttpServletRequest req, HttpServletResponse resp) {
		
			//서비스
			BoardService service = BoardService.getInstance();
			HttpSession session = req.getSession();
			//현재 읽고 있는 게시물을 삭제해야 하니깐, 읽고 있는 게시물 불러오기
			BoardVO boardvo = (BoardVO)session.getAttribute("BoardVO");
			int num = boardvo.getNum();
			service.BoardDelete(num);
			
			//이동(읽고있는 게시물 삭제)
			try {
				//Redirect를 하면 리셋이 되기때문에 다시 셋
				String nowPage = req.getParameter("nowPage");
				String start = req.getParameter("start");
				String end = req.getParameter("end");
				String url="/Board/list.do?nowPage="+nowPage+"&start="+start+"&end="+end+"&num="+num;
				
				resp.sendRedirect(url);
			}catch(IOException e) {
				e.printStackTrace();
			}
		
	}

}

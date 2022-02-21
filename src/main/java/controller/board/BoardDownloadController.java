package controller.board;

import controller.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.BoardService;
import vo.BoardVO;

public class BoardDownloadController implements Controller{

	@Override
	public void Execute(HttpServletRequest req, HttpServletResponse resp) {
		
		
		
		
		//입력값 검증 -> read.jsp에서 filename이 "파일없음"이면 <a>태그 없애기 

		//서비스실행
		BoardService service = BoardService.getInstance();
		service.Download(req,resp);
		
		//view
		
		
	}

}

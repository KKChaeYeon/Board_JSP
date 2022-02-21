package controller.board;

import controller.Controller;
import controller.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardService;

public class BoardPostController implements Controller{

	@Override
	public void Execute(HttpServletRequest req, HttpServletResponse resp) {
		
		//파라미터(매개변수) 받기
		String flag=req.getParameter("flag");
		if(flag.equals("true")) {
			
			//View 이동
			HttpUtil.Forward(req, resp, "/WEB-INF/View/board/post.jsp");
			
		}else {	//폼에 입력을 다한 다음 Post 처리 요청
			
			//서비스 실행
			BoardService service = BoardService.getInstance();
			service.BoardPost(req);	//BoardPost에 넘김
			
			try {
				resp.sendRedirect("/Board/list.do");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}

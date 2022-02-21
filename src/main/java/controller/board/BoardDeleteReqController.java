package controller.board;

import controller.Controller;
import controller.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vo.BoardVO;

public class BoardDeleteReqController implements Controller{

	@Override
	public void Execute(HttpServletRequest req, HttpServletResponse resp) {
		
		String flag=req.getParameter("flag");
		
		if(flag!=null) {
			HttpUtil.Forward(req, resp, "/WEB-INF/View/board/isDelete.jsp");
		}else {
			
			//getBoardVO에서 입력받아 저장하고 있는 PWD와 일치한지 확인
			String curpwd = req.getParameter("pwd");
			
			//1. Session에서 읽고 있는 게시물 pw 추출
			HttpSession session = req.getSession();
			BoardVO vo=(BoardVO)session.getAttribute("BoardVO");
			
			//2. 일치여부 확인
			if(curpwd.equals(vo.getPwd())) {
				
				//2-1 PWD가 일치한다면,
				int num=vo.getNum();
				String start = req.getParameter("start");
				String end = req.getParameter("end");
				String nowPage = req.getParameter("nowPage");
				HttpUtil.Forward(req, resp, "/Board/delete.do?flag=init&num="+num+"&start="+start+"&end="+end+"&nowPage="+nowPage);
			}else {
				
				//2-2pwd가 불일치한다면,
				req.setAttribute("MSG", "패스워드가 일치하지 않습니다.");
				int num=vo.getNum();
				String start = req.getParameter("start");
				String end = req.getParameter("end");
				String nowPage = req.getParameter("nowPage");
				String url = "/Board/read.do?num="+num+"&start="+start+"&end="+end+"&nowPage="+nowPage;
				HttpUtil.Forward(req, resp, url);
			}
		}
	}
}

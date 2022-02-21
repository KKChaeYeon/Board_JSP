package controller.notice;

import controller.Controller;
import controller.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class NoticePostController implements Controller{

	@Override
	public void Execute(HttpServletRequest req, HttpServletResponse resp) {
		
		
		//View
		HttpUtil.Forward(req, resp, "/WEB-INF/View/notice/post.jsp");
		
	}

}

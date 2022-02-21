package controller.home;

import controller.Controller;
import controller.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HomeForwardingController implements Controller{

	@Override
	public void Execute(HttpServletRequest req, HttpServletResponse resp) {
		
		//View로 이동
		HttpUtil.Forward(req, resp, "/WEB-INF/View/usermain.jsp");
		
	}

}

package controller.auth;

import java.io.IOException;

import controller.Controller;
import controller.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutProcController implements Controller{

	@Override
	public void Execute(HttpServletRequest req, HttpServletResponse resp) {
		// 1. 현재 접속 중인 계정의 세션 객체 꺼내오기
		HttpSession session = req.getSession();
		// 2. 세션 해제
		session.invalidate();
		// 3. View로 이동(Login.jsp로 이동)
		try {
			resp.sendRedirect("/Login.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}

package controller.member;

import controller.Controller;
import controller.HttpUtil;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MemberService;
import vo.MemberVo;

public class MemberJoinController implements Controller{

	@Override
	public void Execute(HttpServletRequest req, HttpServletResponse resp) {
//		System.out.println("회원가입 컨트롤러");
//		01 파라미터 받기
		String email = req.getParameter("email");
		String pwd = req.getParameter("pwd");
		int zipcode = Integer.parseInt(req.getParameter("zipcode"));
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		System.out.printf("%s %s %s %s %d\n", email,pwd,addr1,addr2,zipcode);
		
//		02 입력값 검증(Front-Javascript로 처리 가능)
		if(email.isEmpty()||pwd.isEmpty()||addr1.isEmpty()||addr2.isEmpty()||req.getParameter("zipcode").isEmpty()) {
			req.setAttribute("MSG", "입력값이 올바르지 않습니다.");
			HttpUtil.Forward(req, resp, "이동시킬 URL");
		}
		
		
//		03 Service 작업
		MemberService service = MemberService.getInstance();
		MemberVo vo = new MemberVo(email,pwd,zipcode,addr1,addr2);
		service.MemberJoin(vo);
		
		
//		04 Page 이동(View) - Redirect vsForward
		try {
			resp.sendRedirect("Login.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

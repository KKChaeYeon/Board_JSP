package controller.auth;

import org.mindrot.jbcrypt.BCrypt;

import controller.Controller;
import controller.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.MemberService;
import vo.MemberVo;

public class LoginProcController implements Controller{

	@Override
	public void Execute(HttpServletRequest req, HttpServletResponse resp) {
		// 1. 파라미터 받기(ID/PW)
		String email = req.getParameter("email");
		String pwd = req.getParameter("pwd");
		
		// 2. 입력값 검증(ID 미입력 시 처리, 패스워드 미입력 시 or 정책에 맞지 않은 패스워드 입력 시)
		if(email.isEmpty()||pwd.isEmpty()) {
			req.setAttribute("MSG", "ID/PW를 입력하지 않았습니다.");
			HttpUtil.Forward(req, resp, "Login.jsp");
		}
		
		// 3. 받은 계정 vs DB에 있는 계정 비교(Service -> DAO -> 계정 가져오기)
		MemberService service = MemberService.getInstance();
		MemberVo vo = service.MemberSearch(email);
		if(vo==null) {	//계정 조회가 안되는 경우	
			req.setAttribute("MSG", "ID가 조회되지 않습니다.");
			HttpUtil.Forward(req, resp, "/Login.jsp");
			return;
		}else {	//계정 조회 되는 경우
			/* if(pwd.equals(vo.getPwd())) { */
			if(BCrypt.checkpw(pwd, vo.getPwd())) {
				//ID/PW 모두 일치한 경우 진입
				
				// 4. 세션 객체에 ID/PW 저장
				HttpSession session = req.getSession();
				session.setAttribute("vo", vo);
				// 5. 일치한다면 usermain.jsp로 이동
				// 5-1 계정 역할
				System.out.println("ROLE : " + vo.getRole());
				HttpUtil.Forward(req, resp, "/WEB-INF/View/usermain.jsp");
				return;
			}else {
				req.setAttribute("MSG", "PW가 일치하지 않습니다.");
				HttpUtil.Forward(req, resp, "/Login.jsp");
				return;
			}
		}
	}
}

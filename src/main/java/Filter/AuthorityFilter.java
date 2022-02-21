package Filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vo.MemberVo;

public class AuthorityFilter implements Filter{
	
	//권한에 대한 정보를 담는 저장소가 필요하다
		//0:anon
		//1:user
		//2:admin
	Map<String, Integer> RoleMap = new HashMap();
	
	
	//init : 최초로 한번 실행되는 것
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//Notice 공지사항 관련
		RoleMap.put("/Notice/list.do", 0); // 익명, 일반, 관리자 모두 가능
		RoleMap.put("/Notice/post.do", 2); // 관리자만 권한 가능
		
		//Board 자유게시판 관련
		
	}



	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		//req에 있는 session 꺼내기
		HttpServletRequest request = (HttpServletRequest)req;
		HttpSession session = request.getSession();
		
		//Role 꺼내오기
		MemberVo vo=(MemberVo)session.getAttribute("vo");
		if(vo!=null) {
			String tmp=vo.getRole();
			int myrole=0;
			if(tmp.equals("ROLE_ADMIN")) {
				myrole=2;
			}else if(tmp.equals("ROLE_USER")) {
				myrole=1;
			}else {
				myrole=0;
			}
			
			//현재 읽고 있는 url 가져오기
			String url = request.getRequestURI();
			int pagerole=0;
			if(RoleMap.get(url)==null) {
				pagerole=0;
			}else {
				pagerole=RoleMap.get(url);
			}
			
			//접근 금지 처리
			if(pagerole >=1 && myrole==0) {
				throw new ServletException("익명계정 권한으로는 접근이 불가능합니다.");
			}
			else if(pagerole==2 && myrole<2) {
				throw new ServletException("관리자만 접근이 가능한 페이지 입니다.");
			}
		}
		
		
		
		
		
		System.out.println("필터 시작");
		chain.doFilter(req, resp);
		System.out.println("필터 끝");
		
		
	}
	
	

}

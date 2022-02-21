package service;

import dao.MemberDAO;
import vo.MemberVo;

public class MemberService {
	
//	DAO 연결용 참조변수
	MemberDAO dao=null;
	
//	싱글톤 패턴 시작
	private static MemberService instance = new MemberService();
	private MemberService() {
		dao=MemberDAO.getInstance();
	};
	public static MemberService getInstance() {
		if(instance==null) {
			instance = new MemberService();
		}
		return instance;
	}
//	싱글톤 패턴 끝
	
//	회원 가입 함수 : boolean(참 or 거짓) 값에 따라서 MemberJoin을 실행
	public boolean MemberJoin(MemberVo vo) {
		return dao.MemberJoin(vo);
		
	}
	
//	회원 조회 함수
	public MemberVo MemberSearch(String email) {
		 return dao.MemberSearch(email);
		 
	}
//	회원 삭제 함수
//	회원 수정 함수
}

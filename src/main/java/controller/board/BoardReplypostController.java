package controller.board;

import controller.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.BoardService;
import vo.BoardVO;
import vo.MemberVo;
import vo.ReplyVO;

public class BoardReplypostController implements Controller{

	@Override
	public void Execute(HttpServletRequest req, HttpServletResponse resp) {
		
		//파라미터
		String comment = req.getParameter("comment");
		
		HttpSession session = req.getSession();
		MemberVo membervo = (MemberVo)session.getAttribute("vo");
		BoardVO boardvo = (BoardVO)session.getAttribute("BoardVO");
		
		ReplyVO replyvo = new ReplyVO(
				boardvo.getNum(),
				membervo.getEmail(),
				comment
				);
		
		//서비스 실행
		BoardService service = BoardService.getInstance();
		service.ReplyPost(replyvo);
		
		//이동
	}

}

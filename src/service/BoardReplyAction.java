package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dto.BoardBean;

public class BoardReplyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardReplyAction");
		
		request.setCharacterEncoding("utf-8");
		
		int board_num=Integer.parseInt(request.getParameter("board_num"));
		int board_re_ref=Integer.parseInt(request.getParameter("board_re_ref"));
		int board_re_lev=Integer.parseInt(request.getParameter("board_re_lev"));
		int board_re_seq=Integer.parseInt(request.getParameter("board_re_seq"));
		String page = request.getParameter("page");
		
		BoardBean bean = new BoardBean();
		bean.setBoard_re_ref(board_re_ref);
		bean.setBoard_re_lev(board_re_lev);
		bean.setBoard_re_seq(board_re_seq);
		bean.setBoard_name(request.getParameter("board_name"));
		bean.setBoard_subject(request.getParameter("board_subject"));
		bean.setBoard_content(request.getParameter("board_content"));
		bean.setBoard_pass(request.getParameter("board_pass"));
		
		BoardDAO dao = BoardDAO.getInstance();
		int num = dao.boardReply(bean);		
		System.out.println("num="+num);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
//		forward.setPath("./BoardListAction.do?page="+page);
		forward.setPath("./BoardDetailAction.do?num="+board_num+"&page="+page);		
		
		return forward;
	}

}

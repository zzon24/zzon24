package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dto.BoardBean;

public class BoardModifyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardModifyAction");		
		
		response.setContentType("text/html;charset=utf-8");
		
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		int num= Integer.parseInt(request.getParameter("board_num"));
		String page = request.getParameter("page");
		String board_subject = request.getParameter("board_subject"); 
		String board_content = request.getParameter("board_content");
		String board_pass =request.getParameter("board_pass");
		
		BoardBean bean = new BoardBean();
		bean.setBoard_num(num);
		bean.setBoard_subject(board_subject);
		bean.setBoard_content(board_content);	
		
		BoardDAO dao = BoardDAO.getInstance();
		BoardBean db = dao.getDetail(num);
		
		if(db.getBoard_pass().equals(board_pass)){			
			boolean result = dao.boardModify(bean);
			System.out.println("result="+result);
		}else{			
			out.print("<script>");
			out.print("alert('비밀번호가 일치하지 않습니다.');");
			out.print("history.go(-1);");
			out.print("</script>");
			out.close();
		}		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
//		forward.setPath("./BoardListAction.do?page="+page);
		forward.setPath("./BoardDetailAction.do?num="+num+"&page="+page);		
		
		return forward;
	}

}

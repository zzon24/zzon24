package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import dao.BoardDAO;
import dto.BoardBean;

public class BoardDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardDeleteAction");
		
		response.setContentType("text/html; charset=utf-8"); 
		PrintWriter out = response.getWriter();		
		
		int num = Integer.parseInt(request.getParameter("num"));
		String page = request.getParameter("page");
		String board_pass = request.getParameter("board_pass");
					
		BoardDAO dao = BoardDAO.getInstance();
		BoardBean db = dao.getDetail(num);
		
		if(db.getBoard_pass().equals(board_pass)){
			boolean result=dao.boardDelete(num);
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
		forward.setPath("./BoardListAction.do?page="+page);
//		forward.setPath("./BoardDetailAction.do?num="+num+"&page="+page);
		
		return forward;
	}

}

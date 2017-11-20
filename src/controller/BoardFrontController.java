package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Action;
import service.ActionForward;
import service.BoardAddAction;
import service.BoardDeleteAction;
import service.BoardDetailAction;
import service.BoardListAction;
import service.BoardModifyAction;
import service.BoardModifyView;
import service.BoardReplyAction;
import service.BoardReplyView;

/*이 java에서는 값을 받아서 어디로 보낼지만 정해준다*/

/**
 * Servlet implementation class BoardFrontController
 */
@WebServlet("*.do")  /*jsp에서 확장자가do이면 찾아옴..*/
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String RequestURI = request.getRequestURI();	/*전체경로 저장 action =이부분{ /model2/BoardAddAction.do }*/
		String contextPath = request.getContextPath();	 /*프로젝트명 저장 /model*/
		String command = RequestURI.substring(contextPath.length());  /*contextPath.length() = 7  , subString(x) x부터 끝까지*/ 
		
		System.out.println("RequestURI=" + RequestURI);
		System.out.println("contextPath=" + contextPath);
		System.out.println("command=" + command);
		
		Action action = null;
		ActionForward forward = null;
		
		// 글작성
		if(command.equals("/BoardAddAction.do")){
			try{
				action = new BoardAddAction();	/*업캐스팅   부모 = new 자식*/
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		
		// 글목록	
		}else if(command.equals("/BoardListAction.do")){
			try{
				action = new BoardListAction();
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		// 글작성 폼	
		}else if(command.equals("/BoardWrite.do")){
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./board/qna_board_write.jsp");
		
		// 상세 페이지
		}else if(command.equals("/BoardDetailAction.do")){
			try{
				action = new BoardDetailAction();
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		// 댓글 폼	
		}else if(command.equals("/BoardReplyView.do")){
			try{
				action = new BoardReplyView();
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		// 댓글작성
		}else if(command.equals("/BoardReplyAction.do")){
			try{
				action = new BoardReplyAction();
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		// 수정폼	
		}else if(command.equals("/BoardModifyView.do")){
			try{
				action = new BoardModifyView();
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		// 글수정
		}else if(command.equals("/BoardModifyAction.do")){
			try{
				action = new BoardModifyAction();
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		// 글삭제 폼
		}else if(command.equals("/BoardDeleteView.do")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/qna_board_delete.jsp");		
		
		// 글 삭제
		}else if(command.equals("/BoardDeleteAction.do")){
			try{
				action = new BoardDeleteAction();
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
		if(forward != null){
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
			}else{
				RequestDispatcher dispatcher = request
						.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
			
		}	
		
		
	}// doProcess() end	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("get");
		
		doProcess(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("post");  /*중간확인..잘 찾아왓는지*/
		
		doProcess(request, response);
	}

}

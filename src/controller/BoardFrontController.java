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

/*�� java������ ���� �޾Ƽ� ���� �������� �����ش�*/

/**
 * Servlet implementation class BoardFrontController
 */
@WebServlet("*.do")  /*jsp���� Ȯ���ڰ�do�̸� ã�ƿ�..*/
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String RequestURI = request.getRequestURI();	/*��ü��� ���� action =�̺κ�{ /model2/BoardAddAction.do }*/
		String contextPath = request.getContextPath();	 /*������Ʈ�� ���� /model*/
		String command = RequestURI.substring(contextPath.length());  /*contextPath.length() = 7  , subString(x) x���� ������*/ 
		
		System.out.println("RequestURI=" + RequestURI);
		System.out.println("contextPath=" + contextPath);
		System.out.println("command=" + command);
		
		Action action = null;
		ActionForward forward = null;
		
		// ���ۼ�
		if(command.equals("/BoardAddAction.do")){
			try{
				action = new BoardAddAction();	/*��ĳ����   �θ� = new �ڽ�*/
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		
		// �۸��	
		}else if(command.equals("/BoardListAction.do")){
			try{
				action = new BoardListAction();
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		// ���ۼ� ��	
		}else if(command.equals("/BoardWrite.do")){
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./board/qna_board_write.jsp");
		
		// �� ������
		}else if(command.equals("/BoardDetailAction.do")){
			try{
				action = new BoardDetailAction();
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		// ��� ��	
		}else if(command.equals("/BoardReplyView.do")){
			try{
				action = new BoardReplyView();
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		// ����ۼ�
		}else if(command.equals("/BoardReplyAction.do")){
			try{
				action = new BoardReplyAction();
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		// ������	
		}else if(command.equals("/BoardModifyView.do")){
			try{
				action = new BoardModifyView();
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		// �ۼ���
		}else if(command.equals("/BoardModifyAction.do")){
			try{
				action = new BoardModifyAction();
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		// �ۻ��� ��
		}else if(command.equals("/BoardDeleteView.do")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/qna_board_delete.jsp");		
		
		// �� ����
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
		System.out.println("post");  /*�߰�Ȯ��..�� ã�ƿӴ���*/
		
		doProcess(request, response);
	}

}

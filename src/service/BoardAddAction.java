package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.BoardDAO;
import dto.BoardBean;

public class BoardAddAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardAddAction");		
		
		String realFolder = request.getRealPath("boardUpload");
		int fileSize = 1024 * 1024; /*1메가바이트*/
		
		MultipartRequest multi = 
				new MultipartRequest(
									 request, 
									 realFolder, 
									 fileSize, 
									 "utf-8", 
									 new DefaultFileRenamePolicy());   /*중복문제 해결*/
		
		BoardBean bean = new BoardBean(); /*BoardBean =  DTA클래스*/
		bean.setBoard_name(multi.getParameter("board_name"));
		bean.setBoard_pass(multi.getParameter("board_pass"));
		bean.setBoard_subject(multi.getParameter("board_subject"));
		bean.setBoard_content(multi.getParameter("board_content"));
		bean.setBoard_file(multi.getFilesystemName("board_file"));
	/*	---------- 여기까지 하면 서버측에 저장된상태 아직 테이블에는 저장 안되있음-----------------*/
		BoardDAO dao = BoardDAO.getInstance();
		boolean result = dao.boardInsert(bean);
		System.out.println("result="+result);
	/*---------------여기까지 하면 db에 저장은 됬지만 바로 view페이지에서 볼수는 없음(db에서 꺼내는 작업이 필요함)--------------*/	
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./BoardListAction.do");   /*-------컨트롤러로 다시 돌아가서실행*/
		/*	-----------------여기에서 set메소드 호출------------*/
		return forward;
	}

}

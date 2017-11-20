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
		int fileSize = 1024 * 1024; /*1�ް�����Ʈ*/
		
		MultipartRequest multi = 
				new MultipartRequest(
									 request, 
									 realFolder, 
									 fileSize, 
									 "utf-8", 
									 new DefaultFileRenamePolicy());   /*�ߺ����� �ذ�*/
		
		BoardBean bean = new BoardBean(); /*BoardBean =  DTAŬ����*/
		bean.setBoard_name(multi.getParameter("board_name"));
		bean.setBoard_pass(multi.getParameter("board_pass"));
		bean.setBoard_subject(multi.getParameter("board_subject"));
		bean.setBoard_content(multi.getParameter("board_content"));
		bean.setBoard_file(multi.getFilesystemName("board_file"));
	/*	---------- ������� �ϸ� �������� ����Ȼ��� ���� ���̺��� ���� �ȵ�����-----------------*/
		BoardDAO dao = BoardDAO.getInstance();
		boolean result = dao.boardInsert(bean);
		System.out.println("result="+result);
	/*---------------������� �ϸ� db�� ������ ������ �ٷ� view���������� ������ ����(db���� ������ �۾��� �ʿ���)--------------*/	
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./BoardListAction.do");   /*-------��Ʈ�ѷ��� �ٽ� ���ư�������*/
		/*	-----------------���⿡�� set�޼ҵ� ȣ��------------*/
		return forward;
	}

}

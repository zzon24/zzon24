package service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;

public class BoardListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardListAction");
		
		BoardDAO dao = BoardDAO.getInstance();		
		
		List boardlist=new ArrayList();
		
		int page=1; // ���� ������ ��ȣ
		int limit=10; // �� ȭ�鿡 ����� ���ڵ� ����
		
		if(request.getParameter("page") != null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		int listcount=dao.getListCount(); //�� ����Ʈ ���� �޾ƿ�
		boardlist = dao.getBoardList(page,limit); //����Ʈ�� �޾ƿ�/////////////////////@!@!@
		
		//�� ������ ��
 		int maxpage=(int)((double)listcount/limit+0.95); //0.95�� ���ؼ� �ø� ó��
 		
 		//���� �������� ������ ���� ������ ��(1, 11, 21 ��...)
 		int startpage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
 		
 		//���� �������� ������ ������ ������ ��(10, 20, 30 ��...)
		int endpage = startpage+10-1;

 		if(endpage> maxpage) endpage= maxpage;
 		
// 		int number = listcount-(page-1)*10; 		
 		
 		/*setAttribute : name���� ������ �̸��� value���� �Ҵ��Ѵ�
 		 * qna_board_list�� ���� �������ش�*/
 	/*	request�� �����Ǵ� ���� ���� �̷��� �ϸ� jsp���� c:set value="${page}" �̷��� �ҷ��ü� ����.. */ 
 		request.setAttribute("page", page); //���� ������ ��
 		request.setAttribute("maxpage", maxpage); //�ִ� ������ ��
 		request.setAttribute("startpage", startpage); //���� �������� ǥ���� ù ������ ��
 		request.setAttribute("endpage", endpage); //���� �������� ǥ���� �� ������ ��
		request.setAttribute("listcount",listcount); //�� ��
		
		request.setAttribute("boardlist", boardlist);		
		
		ActionForward forward= new ActionForward();
	 	forward.setRedirect(false);   /*false���߸� dispatcher������� ������..*/
 		forward.setPath("./board/qna_board_list.jsp");
 		
 		return forward;
	}

}

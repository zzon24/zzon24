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
		
		int page=1; // 현재 페이지 번호
		int limit=10; // 한 화면에 출력할 레코드 갯수
		
		if(request.getParameter("page") != null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		int listcount=dao.getListCount(); //총 리스트 수를 받아옴
		boardlist = dao.getBoardList(page,limit); //리스트를 받아옴/////////////////////@!@!@
		
		//총 페이지 수
 		int maxpage=(int)((double)listcount/limit+0.95); //0.95를 더해서 올림 처리
 		
 		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
 		int startpage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
 		
 		//현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등...)
		int endpage = startpage+10-1;

 		if(endpage> maxpage) endpage= maxpage;
 		
// 		int number = listcount-(page-1)*10; 		
 		
 		/*setAttribute : name으로 지정한 이름에 value값을 할당한다
 		 * qna_board_list에 값을 공유해준다*/
 	/*	request로 공유되는 값들 설정 이렇게 하면 jsp에서 c:set value="${page}" 이렇게 불러올수 있음.. */ 
 		request.setAttribute("page", page); //현재 페이지 수
 		request.setAttribute("maxpage", maxpage); //최대 페이지 수
 		request.setAttribute("startpage", startpage); //현재 페이지에 표시할 첫 페이지 수
 		request.setAttribute("endpage", endpage); //현재 페이지에 표시할 끝 페이지 수
		request.setAttribute("listcount",listcount); //글 수
		
		request.setAttribute("boardlist", boardlist);		
		
		ActionForward forward= new ActionForward();
	 	forward.setRedirect(false);   /*false여야만 dispatcher방식으로 공유됨..*/
 		forward.setPath("./board/qna_board_list.jsp");
 		
 		return forward;
	}

}

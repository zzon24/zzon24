<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
//	List boardList=(List)request.getAttribute("boardlist");
//	int listcount=((Integer)request.getAttribute("listcount")).intValue();
//	int nowpage=((Integer)request.getAttribute("page")).intValue();
//	int maxpage=((Integer)request.getAttribute("maxpage")).intValue();
//	int startpage=((Integer)request.getAttribute("startpage")).intValue();
//	int endpage=((Integer)request.getAttribute("endpage")).intValue();

//	int number = listcount-(nowpage-1)*10;
%>

<html>
<head>
	<title>MVC 게시판</title>
</head>

<body>
<!-- 게시판 리스트 -->

<table align=center width=600 border="1" cellpadding="0" cellspacing="0">
<%
//if(listcount > 0){
%>

<!-- 레코드가 있으면 -->
<c:if test="${listcount > 0}">

	<tr align="center" valign="middle">
		<td colspan="4">MVC 게시판</td>
		<td align=right>
			<font size=2>글 개수 : ${listcount }</font> <%--  서비스 클래스에서 공유된값${listcount } --%>
		</td>
	</tr>
	
	<tr align="center" valign="middle" bordercolor="#333333">
		<td style="font-family:Tahoma;font-size:8pt;" width="8%" height="26">
			<div align="center">번호</div>
		</td>
		<td style="font-family:Tahoma;font-size:8pt;" width="50%">
			<div align="center">제목</div>
		</td>
		<td style="font-family:Tahoma;font-size:8pt;" width="14%">
			<div align="center">작성자</div>
		</td>
		<td style="font-family:Tahoma;font-size:8pt;" width="17%">
			<div align="center">날짜</div>
		</td>
		<td style="font-family:Tahoma;font-size:8pt;" width="11%">
			<div align="center">조회수</div>
		</td>
	</tr>
	
	<%//int number = listcount-(nowpage-1)*10;
//		for(int i=0;i<boardList.size();i++){
//			BoardBean bl=(BoardBean)boardList.get(i);
	%>	 
	 
	<!-- 화면 출력 번호 -->		
	<c:set var="num" value="${listcount-(page-1)*10}"/> 	 <!-- BoardListAction에서 공유된 값을 사용한다 -->
	
	<c:forEach var="b" items="${boardlist}">	
	
	<tr align="center" valign="middle" bordercolor="#333333"
		onmouseover="this.style.backgroundColor='F8F8F8'"
		onmouseout="this.style.backgroundColor=''">
		<td height="23" style="font-family:Tahoma;font-size:10pt;">
			
			<!-- 번호 출력 부분 -->	
			<c:out value="${num}"/>			
			<c:set var="num" value="${num-1}"/>	
					
		</td>
		
		<td style="font-family:Tahoma;font-size:10pt;">
			<div align="left">
			<%--if(bl.getBOARD_RE_LEV()!=0){ // 답글    --%>
				<%--for(int a=0;a<=bl.getBOARD_RE_LEV()*2;a++){ --%>
				<!-- &nbsp; -->
				<%--} --%>
				<!-- ▶ -->
			<%--}else{  // 원문 --%>
				<!-- ▶ -->
			<%--} --%>
			
			<!-- 답변글 제목앞에 여백 처리 부분 -->
		    <c:if test="${b.board_re_lev != 0}"> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ▶				
			</c:if>   
			<c:if test="${b.board_re_lev == 0}"> 
				&nbsp; ▶ 
			</c:if> 			
			
			
			<a href="./BoardDetailAction.do?num=${b.board_num}&page=${page}">
				<%--bl.getBOARD_SUBJECT()--%>
				${b.board_subject}
			</a>
			</div>
		</td>
		
		<td style="font-family:Tahoma;font-size:10pt;">
			<div align="center"><%--bl.getBOARD_NAME() --%></div>
					${b.board_name}
		</td>
		<td style="font-family:Tahoma;font-size:10pt;">
			<div align="center"><%--bl.getBOARD_DATE() --%></div>
					${b.board_date}
		</td>	
		<td style="font-family:Tahoma;font-size:10pt;">
			<div align="center"><%--bl.getBOARD_READCOUNT() --%></div>
					${b.board_readcount}
		</td>
	</tr>
	
	</c:forEach>
	<%-- </c:if> --%>
	<%//}// for end %>
	
	
	<tr align=center height=20>
		<td colspan=7 style=font-family:Tahoma;font-size:10pt;>
			<%--if(nowpage<=1){ --%>
					<!-- [이전]&nbsp; -->			
			<%--}else{ --%>			
			<%-- <a href="./BoardList.bo?page=<%=nowpage-1 %>">[이전]</a>&nbsp; --%>			
			<%--} --%>
			
			<c:if test="${page <= 1}">
				[이전]&nbsp;
			</c:if>
			<c:if test="${page > 1 }">			
				 <a href="./BoardListAction.do?page=${page-1}">[이전]</a>&nbsp;
			</c:if>
			
			
			
			<%--for(int a=startpage; a<=endpage; a++){
				if(a==nowpage){ // 현재 페이지 --%>
				<%-- [<%=a %>] --%>
				<%--}else{ --%>
				<%-- <a href="./BoardList.bo?page=<%=a %>">[<%=a %>]</a>&nbsp; --%>
				<%--} --%>
			<%--} --%>
			
			<c:forEach var="a" begin="${startpage}" end="${endpage}">
				<c:if test="${a == page }">
					[${a}]
				</c:if>
				<c:if test="${a != page }">
					<a href="./BoardListAction.do?page=${a}">[${a}]</a>&nbsp;
				</c:if>
			</c:forEach>
			


			<%--if(nowpage>=maxpage){ --%>
				<!-- [다음] -->
			<%--}else{ --%>
			<%-- <a href="./BoardList.bo?page=<%=nowpage+1 %>">[다음]</a> --%>
			<%--} --%>
			
			<c:if test="${page >= maxpage }">
				[다음] 
			</c:if>
			<c:if test="${page < maxpage }">
				<a href="./BoardListAction.do?page=${page+1}">[다음]</a>
			</c:if>
			
			
		</td>
	</tr>
	
	</c:if>
	<%
//    }else{
	%>
	
	<!-- 레코드가 없으면 -->
	<c:if test="${listcount == 0 }">
	<tr align="center" valign="middle">
		<td colspan="4">MVC 게시판</td>
		<td align=right>
			<font size=2>등록된 글이 없습니다.</font>
		</td>
	</tr>
	</c:if>
	
	<%
//	}
	%>
	
	<tr align="right">
		<td colspan="5">
	   		<a href="./BoardWrite.do">[글쓰기]</a>
		</td>
	</tr>
</table>

</body>
</html>
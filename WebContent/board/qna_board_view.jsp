<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
//	BoardBean board = (BoardBean)request.getAttribute("boarddata");
//	String nowpage = (String)request.getAttribute("page");
%>

<html>
<head>
	<title>MVC 게시판</title>
</head>

<body>
<!-- 게시판 수정 -->
<table cellpadding="0" cellspacing="0" align=center border=1>
	<tr align="center" valign="middle">
		<td colspan="5">MVC 게시판</td>
	</tr>
	
	<tr>
		<td style="font-family:돋음; font-size:12" height="16">
			<div align="center">제 목&nbsp;&nbsp;</div>
		</td>
		
		<td style="font-family:돋음; font-size:12">
		<%--board.getBOARD_SUBJECT()--%>
		${bean.board_subject}		
		</td>
	</tr>
	
	<tr bgcolor="cccccc">
		<td colspan="2" style="height:1px;">
		</td>
	</tr>
	
	<tr>
		<td style="font-family:돋음; font-size:12">
			<div align="center">내 용</div>
		</td>
		<td style="font-family:돋음; font-size:12">
			<table border=1 width=490 height=250 style="table-layout:fixed">
				<tr>
					<td valign=top style="font-family:돋음; font-size:12">
					<%-- <pre><%=board.getBOARD_CONTENT() %></pre> --%>
					<pre>${bean.board_content}</pre>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12">
			<div align="center">첨부파일</div>
		</td>
		<td style="font-family:돋음; font-size:12">
		
		<%--if(!(board.getBOARD_FILE()==null)){ --%>		
		<%-- <a href="/Model2_Board11/board/file_down.jsp?file_name=<%=board.getBOARD_FILE()%>">
			<%=board.getBOARD_FILE()%></a> --%>	
		<%--} --%>
		
		
		<!-- 첨부 파일이 있으면 출력하는 부분 -->
		<%-- <c:if test="${empty bean.board_file}">			
				<a href="/model2/board/file_down.jsp?file_name=${bean.board_file}">
				${bean.board_file}</a>
		</c:if> --%>
		<c:if test="${!empty bean.board_file}">
			<a href="/model2/board/file_down.jsp?file_name=${bean.board_file}">
				${bean.board_file}</a>
		</c:if>		
		
		</td>
	</tr>
	
	<tr bgcolor="cccccc">
		<td colspan="2" style="height:1px;"></td>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	
	<tr align="center" valign="middle">
		<td colspan="5">
			<font size=2>
			<a href="./BoardReplyView.do?num=${bean.board_num}&page=${page}">
			[답변]</a>&nbsp;&nbsp;
			
			<a href="./BoardModifyView.do?num=${bean.board_num}&page=${page}">
			[수정]</a>&nbsp;&nbsp;
			
			<a href="./BoardDeleteView.do?num=${bean.board_num}&page=${page}">
			[삭제]</a>&nbsp;&nbsp;
			
			<a href="./BoardListAction.do?page=${page}">[목록]</a>&nbsp;&nbsp;			
			</font>
		</td>
	</tr>
</table>
<!-- 게시판 수정 -->
</body>
</html>
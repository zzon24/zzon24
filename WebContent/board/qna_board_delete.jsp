<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%
//	int num=Integer.parseInt(request.getParameter("num"));
//	String nowpage = request.getParameter("page");
%>
<html>
<head>
<title>MVC 게시판</title>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	
	<script>
		$(document).ready(function(){
			$("form").submit(function(){				
				if($("#board_pass").val()==""){
					alert("비밀번호를 입력 하세요?");
					$("#board_pass").focus();
					return false;
				}						
			});			
		});	
	</script>

</head>
<body>

<form action="/model2/BoardDeleteAction.do" method="post">
<input type=hidden name=num value="${param.num}">
<input type=hidden name=page value="${param.page}">
<table border=1 align=center>
<tr>
	<td>
		<font size=2>글 비밀번호 : </font>
	</td>
	<td>
		<input name="board_pass" id="board_pass" type="password">
	</td>
</tr>
<tr>
	<td colspan=2 align=center>
		<input type=submit value="삭제">
		<input type=button value="취소" onClick="history.go(-1)">
	</td>
</tr>
</table>
</form>
</body>
</html>
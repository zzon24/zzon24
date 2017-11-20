<%@ page language="java" contentType="text/html; charset=utf-8"%>

<%
//	BoardBean board=(BoardBean)request.getAttribute("boarddata");
//	String nowpage = (String)request.getAttribute("page");
%>

<html>
<head>
	<title>MVC 게시판</title>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="/model2/board/script.js"></script>
	
	<script>
		/* $(document).ready(function(){
			$("form").submit(function(){
				if($("#board_name").val()==""){
					alert("이름을 입력 하세요?");
					$("#board_name").focus();
					return false;
				}				
				if($("#board_subject").val()==""){
					alert("제목을 입력 하세요?");
					$("#board_subject").focus();
					return false;
				}
				if($("#board_content").val()==""){
					alert("내용을 입력 하세요?");
					$("#board_content").focus();
					return false;
				}
				if($("#board_pass").val()==""){
					alert("비밀번호를 입력 하세요?");
					$("#board_pass").focus();
					return false;
				}
			});			
		});	 */
	</script>
</head>
<body>

<form action="/model2/BoardReplyAction.do" method="post" name="boardform">
<input type="hidden" name="board_num" value="${bean.board_num}">
<input type="hidden" name="board_re_ref" value="${bean.board_re_ref}">
<input type="hidden" name="board_re_lev" value="${bean.board_re_lev}">
<input type="hidden" name="board_re_seq" value="${bean.board_re_seq}">
<input type="hidden" name="page" value="${page}">

<table cellpadding="0" cellspacing="0" align=center border=1>
	<tr align="center" valign="middle">
		<td colspan="5">MVC 게시판</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12" height="16">
			<div align="center">글쓴이</div>
		</td>
		<td>
			<input name="board_name" id="board_name" type="text"/>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12" height="16">
			<div align="center">제 목</div>
		</td>
		<td>
			<input name="board_subject" id="board_subject" type="text" size="50" 
				maxlength="100" value="Re: ${bean.board_subject}"/>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12">
			<div align="center">내 용</div>
		</td>
		<td>
			<textarea name="board_content" id="board_content" cols="67" rows="15"></textarea>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12">
			<div align="center">비밀번호</div>
		</td>
		<td>
			<input name="board_pass" id="board_pass" type="password">
		</td>
	</tr>
	
	<tr bgcolor="cccccc">
		<td colspan="2" style="height:1px;">
		</td>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	
	<tr align="center" valign="middle">
		<td colspan="5">
		    <input type=submit value="등록">
			<input type=button value="취소" onClick="history.go(-1)">		    
		</td>
	</tr>
</table>
</form>

</body>
</html>
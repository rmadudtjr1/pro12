<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<form method="post" action="/pro12/member/addMember.do">
	<table border="1" align="center" width="80%">
		<tr align="center" bgcolor="lightgreen">
			<th>아이디</th>
			<th>암호</th>
			<th>이름</th>
			<th>이메일</th>
		</tr>
		<tr align="center">
			<td><input type="text" name="id"></td>
			<td><input type="password" name="pwd"></td>
			<td><input type="text" name="name"></td>
			<td><input type="email" name="email"></td>
		</tr>
		<tr align="center">
			<td colspan="4">
			<input type="submit" value="회원가입">
			<input type="reset" value="다시입력">
			</td>
		</tr>
	</table>
	</form>
</body>
</html>





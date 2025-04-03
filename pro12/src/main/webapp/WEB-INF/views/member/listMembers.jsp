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
<title>회원정보출력</title>
</head>
<body>
	<table border="1" align="center" width="80%">
		<tr align="center" bgcolor="lightgreen">
			<th>아이디</th>
			<th>암호</th>
			<th>이름</th>
			<th>이메일</th>
			<th>가입일</th>
			<th>삭제</th>
		</tr>
	<c:forEach	var="m" items="${membersList }">
		<tr align="center">
			<td>
				<a href="/pro12/member/memberDetail.do?id=${m.id }">
					${m.id }
				</a>
			</td>
			<td>${m.pwd }</td>
			<td>${m.name }</td>
			<td>${m.email }</td>
			<td>${m.joinDate }</td>
			<td>
				<a href="/pro12/member/memberDel.do?id=${m.id }">
					<button>삭제</button>
				</a>
			</td>
		</tr>
	</c:forEach>
	</table>
	<h1 style="text-align:center">
		<a href="/pro12/member/memberForm.do">회원가입</a>
	</h1>
</body>
</html>





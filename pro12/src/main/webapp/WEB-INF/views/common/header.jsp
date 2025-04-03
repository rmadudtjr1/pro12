<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" 
	value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>헤더부분</title>
</head>
<body>
	<table border="0" width="100%">
	<tr>
		<td>
			<a href="${contextPath }/main.do">
				<img src="${contextPath }/resources/image/frog.png">
			</a>
		</td>
		<td>
			<h1>스프링프레임워크 홈페이지!!</h1>
		</td>
		<td>
			<c:choose>
				<c:when test="${isLogon == true && id != null }">
					<h3>환영합니다. ${id }님</h3>
					<h3>
						<a href="${contextPath }/member/logout.do">
							로그아웃
						</a>
					</h3>
				</c:when>
				<c:otherwise>
					<h3>
						<a href="${contextPath }/member/loginForm.do">
							로그인
						</a>
					</h3>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	</table>
</body>
</html>
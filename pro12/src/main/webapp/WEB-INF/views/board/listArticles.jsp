<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" 
	value="${pageContext.request.contextPath }" />
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table align="center" border="1" width="100%">
		<tr height="10" align="center" bgcolor="lightgreen">
			<td>글번호</td>
			<td>작성자</td>
			<td>제목</td>
			<td>작성일자</td>
		</tr>
		<c:choose>
			<c:when test="${articleList == null }">
			<tr height="10">
				<td colspan="4">
					<b>
					<span style="font-size:9pt;">
						등록된 글이 없습니다.
					</span>
					</b>
				</td>
			</tr>
			</c:when>
			<c:when test="${articleList != null }">
				<c:forEach	var="article" items="${articleList }">
				<tr>
					<td width="5%">${article.articleNo }</td>
					<td width="10%">${article.id }</td>
					<td align="left" width="35%">
						<span style="padding-right:30px;"></span>
						<c:choose>
							<c:when test="${article.level > 1 }">
								<c:forEach begin="1" end="${article.level }" step="1">
									<span style="padding-left:15px;"></span>
								</c:forEach>
								<span style="font-size:12px;">[답변]</span>
								<a href="${contextPath }/board/viewArticle.do?articleNo=${article.articleNo}">
									${article.title }</a>									
							</c:when>
							<c:otherwise>
								<a href="${contextPath }/board/viewArticle.do?articleNo=${article.articleNo}">
									${article.title }
								</a>
							</c:otherwise>
						</c:choose>
					</td>
					<td width="10%">${article.writeDate }</td>
				</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>
	<h3 style="text-align: center;">
		<a href="${contextPath }/board/articleForm.do">
			글쓰기
		</a>
	</h3>
</body>
</html>








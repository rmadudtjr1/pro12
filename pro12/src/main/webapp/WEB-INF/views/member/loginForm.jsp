<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<form method="post" action="/pro12/member/login.do">
		<table border="1" width="80%" align="center">
			<tr align="center">
				<th>아이디</th>
				<th>암호</th>
			</tr>
			<tr align="center">
				<td><input type="text" name="id"></td>
				<td><input type="password" name="pwd"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="로그인">
					<input type="reset" value="다시입력">
				</td>
			</tr>	
		</table>
	</form>
</body>
</html>
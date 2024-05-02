<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 상세정보 페이지</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>유저 번호</th>
			<th>${userInfo.uiNum}</th>
		</tr>

		<tr>
			<th>유저 이름</th>
			<th>${userInfo.uiName}</th>
		</tr>

		<tr>
			<th>유저 아이디</th>
			<th>${userInfo.uiId}</th>
		</tr>

		<tr>
			<th>유저 비밀번호</th>
			<th>${userInfo.uiPwd}</th>
		</tr>
		<tr>
			<th colspan="2">
				<button onclick="location.href='/user-info/list'">뒤로</button>
			</th>
		<tr>
	</table>

</body>
</html>
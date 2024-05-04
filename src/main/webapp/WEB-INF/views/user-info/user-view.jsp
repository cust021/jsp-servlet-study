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
<form action="/user-info/delete" method="POST">
<input type="hidden" name="uiNum" value="${userInfo.uiNum }">
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
				<button type="button" onclick="location.href='/user-info/update?uiNum=${userInfo.uiNum}'">수정</button>
				<button>삭제</button>
				<button type="button" onclick="location.href='/user-info/list'">뒤로</button> <!-- type을 지정해두면 form태그에 영향을 받지 않는다. -->
			</th>
		<tr>
	</table>
</form>
</body>

</html>
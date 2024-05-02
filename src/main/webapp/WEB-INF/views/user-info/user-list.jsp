<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 리스트 페이지</title>
</head>
<body>
	유저 리스트가 될 페이지 입니다.
	
	<table border="1">
	<tr>
		<th>유저 번호</th>
		<th>유저 이름</th>
		<th>유저 아이디</th>
		<th>유저 비밀번호</th>
	</tr>
	<c:forEach items="${userInfoList}" var="userInfo"> <!-- repository에 있는 userInfoList를 가지고온다-->
		<tr>
			<td>${userInfo.uiNum}</td>
			<td><a href="/user-info/view?uiNum=${userInfo.uiNum}">${userInfo.uiName}</a></td>
			<td>${userInfo.uiId}</td>
			<td>${userInfo.uiPwd}</td>
		</tr>
	 </c:forEach>
	<tr>
	
	<td align="right" colspan="4"><button onclick="location.href='/user-info/insert'">유저 등록</button>
	</table>
</body>
</html>
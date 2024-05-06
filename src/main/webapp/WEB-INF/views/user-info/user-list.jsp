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
	유저 리스트가 될 페이지 입니다.<br>
	<form action="/user-info/list?" method="GET"> <!-- form을 이용해 검색을 누를씨 action동작 -->
	<input type="text" name="uiName" placeholder="유저 이름" > <!-- 파라미터 값인 uiName을 끌고 오기위해 name값에 uiName값 삽입 -->
	<button type="submit">검색</button>	
	</form>
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
	<button onclick="location.href='/'">인덱스로 돌아가기</button>
</body>
</html>
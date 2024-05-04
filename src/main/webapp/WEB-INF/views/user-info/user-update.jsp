<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원수정</title>
</head>
<body>
	
<form action="/user-info/update" method="POST">
	<input type="hidden" name="uiNum" value="${userInfo.uiNum}"><br>
	<input type="text" name="uiName" placeholder="수정할 이름" value="${userInfo.uiName}"><br>
	<input type="text" name="uiId" placeholder="수정할 아이디" value="${userInfo.uiId}"><br>
	<input type="text" name="uiPwd" placeholder="수정할 패스워드" value="${userInfo.uiPwd}"><br>
	<button>수정</button>
	<button type="button" onclick="location.href='/user-info/list'">뒤로</button>
</form>
	
</body>
</html>
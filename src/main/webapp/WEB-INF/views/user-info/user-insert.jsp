<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<form action="/user-info/insert" method="POST">
		<input type="text" name="uiName" placeholder="유저 이름"><br>
		<input type="text" name="uiId" placeholder="유저 아이디"><br>
		<input type="password" name="uiPwd" placeholder="유저 비밀번호">
		<button>등록</button>
		<button type="button" onclick="location.href='/user-info/list'">뒤로</button><!-- type을 지정해두면 form태그에 영향을 받지 않는다. -->
	</form>

</body>
</html>
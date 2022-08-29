<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<form action="login" method="POST">
	<label for="userId">아이디 : </label>
	<input type="text" id="userId" name="id" required/>
	
	<br>
	
	<label>비밀번호 : </label>
	<input type="password" id="userPwd" name="password" required/>
	
	<br><br>
	
	<input type="submit" value="로그인" />
</form>
</body>
</html>

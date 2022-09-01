<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<c:set var="path" value="${ pageContext.request.contextPath }"/>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<c:if test="${ empty loginMember }">
	<form action="${ path }/login" method="POST">
		<label for="userId">아이디 : </label>
		<input type="text" id="userId" name="id" required/>
		
		<br>
		
		<label>비밀번호 : </label>
		<input type="password" id="userPwd" name="password" required/>
		
		<br><br>
		
		<input type="button" value="회원가입" onclick="location.href='${ path }/member/enroll'">
		<input type="submit" value="로그인" />
	</form>
</c:if>
<c:if test="${ !empty loginMember }">
	<a href="${ path }/member/myPage">
		${ loginMember.name }
	</a> 님 ! 안녕하세요 ♡
	<button onclick="location.replace('${ path }/logout')">로그아웃</button>
</c:if>
</body>
</html>

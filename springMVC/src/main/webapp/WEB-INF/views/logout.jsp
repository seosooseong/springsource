<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- EL에서 $를 붙여서 사용 할 수 있는 값은 request, session에 담긴 값만 사용가능 -->
	<!-- 근데 왜 나오지? :  -->
	<h1>LogOut</h1>
	<!-- @Service() 이런식이라면 앞글자가 소문자로 기본 값 -->
	<h2>아이디   : ${loginVO.userid}</h2>
	<h2>비밀번호 : ${loginVO.password}</h2>

	<hr/>	
	<h2>아이디   : ${login.userid}</h2>
	<h2>비밀번호 : ${login.password}</h2>
</body>
</html>
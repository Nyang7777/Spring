<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>결과 2 </h1>
	<h2>선택 1: ${s1}</h2>
	<h2>선택 2: ${s2}</h2>
	<h2>선택 연산: ${op}</h2>
	<h2>계산 : ${s1} ${op} ${s2} = ${str}</h2>
	<c:forEach var = "k" items="${hobby}">
		<li>선택 운동: ${k}</li>
	</c:forEach>
	
	<hr>
	<h1>결과 3 </h1>
	<h2>선택 1: ${vo.s1}</h2>
	<h2>선택 2: ${vo.s2}</h2>
	<h2>선택 연산: ${vo.op}</h2>
	<h2>계산 : ${vo.s1} ${vo.op} ${vo.s2} = ${str}</h2>
	<c:forEach var = "k" items="${vo.hobby}">
		<li>선택 운동: ${k}</li>
	</c:forEach>
	
	
	
</body>
</html>
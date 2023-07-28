<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>결 과</h1>
	<h2>올린 사람: ${name }</h2>
	<h2>파일 이름: ${f_name }</h2>
	<h2>파일 타입: ${file_type }</h2>
	<h2>파일 크기: ${size }</h2>
	<img src="resources/images/${f_name}">
</body>
</html>
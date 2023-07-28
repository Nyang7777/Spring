<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>결과 4</h1>
	<h2>올린사람: ${name }</h2>
	<h2>파일이름: ${f_name }</h2>
	<h2>파일이름: ${f_name2 }</h2>
	<h2>파일타입: ${file_type }</h2>
	<h2>파일크기: ${size }</h2>
	<h2>파일날짜: ${lastday }</h2>
	<img src="/resources/images/${f_name2}">
	<hr>
	
	<h3>
		<li>올린 사람: ${name }</li>
		<li>업로드 파일이름: ${f_name }</li>
		<li>저장된 파일이름: ${f_name2 }</li>
		<li>파일 타입: ${file_type }</li>
		<li>파일 크기: ${size }</li>
		<li>파일 수정날짜: ${lastday }</li>
		<img src="resources/images/${f_name2}" style="width: 150px;">
	</h3>
	
</body>
</html>
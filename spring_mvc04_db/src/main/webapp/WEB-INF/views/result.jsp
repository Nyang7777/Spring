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
	<table>
		<thead>
			<tr><td>지역</td><td>온도</td><td>상태</td><td>아이콘</td></tr>
		</thead>
		<tbody>
			<c:forEach var="k" items="${list}">
				<tr>
					<td>${k.local}</td>
					<td>${k.ta}</td>
					<td>${k.desc}</td>
					<td><img src="http://www.kma.go.kr/images/icon/NW/NB${k.icon}.png"></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
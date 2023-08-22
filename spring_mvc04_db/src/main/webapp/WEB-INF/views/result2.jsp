<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<thead>
			<td>시·도별(1)</td><td>총인구 (명)</td><td>1차 접종 누계</td><td>1차 접종 퍼센트</td><td>2차 접종 누계</td><td>2차 접종 퍼센트</td></tr>
		</thead>
		<tbody>
			<c:forEach var="k" items="${list}">
				<tr>
					<td>${k.city}</td>
					<td><f:formatNumber value="${k.totalcount}" pattern="#,###.##"/> </td>
					<td><f:formatNumber value="${k.first}" pattern="#,###.##"/></td>
					<td><f:formatNumber value="${k.fristcount}" pattern="#,###.##"/>%</td>
					<td><f:formatNumber value="${k.second}" pattern="#,###.##"/></td>
					<td><f:formatNumber value="${k.secondcount}" pattern="#,###.##"/>%</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
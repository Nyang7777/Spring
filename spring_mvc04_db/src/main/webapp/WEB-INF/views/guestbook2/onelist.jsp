<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방 명 록</title>
<style type="text/css">
a {
	text-decoration: none;
}

table {
	width: 600px;
	border-collapse: collapse;
	text-align: center;
}

table, th, td {
	border: 1px solid black;
	padding: 3px
}

div {
	width: 600px;
	margin: auto;
	text-align: center;
}
</style>
<script type="text/javascript">
	function edit_go(f) {
		f.action = "/guestbook2_update_form.do";
		f.submit();
	}
	function delete_go(f) {
		f.action = "/guestbook2_delete_form.do";
		f.submit();
	}
</script>
</head>
<body>
	<div>
		<h2>방명록 : 상세보기 화면</h2>
		<hr />
		<p>
			[<a href="/guestbook2list.do">목록으로 이동</a>]
		</p>

		<!-- 파일 첨부하려면  -->
		<form method="post">
			<table>
				<tr align="center">
					<td bgcolor="#99ccff">작성자</td>
					<td>${gvo2.name }</td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">제 목</td>
					<td>${gvo2.subject }</td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">email</td>
					<td>${gvo2.email }</td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">첨부파일</td>
					<c:choose>
						<c:when test="${empty gvo2.f_name}">
							<td><b>첨부 파일 없음</b></td>
						</c:when>
						<c:otherwise>
							<td><a href="/guestbook2_down.do?f_name=${gvo2.f_name}">
									<img src="resources/images/${gvo2.f_name}" style="width: 80px;">
							</a></td>
						</c:otherwise>
					</c:choose>

				</tr>
				<tr align="center">
					<td colspan="2" style="text-align: left"><pre
							style="padding-left: 15px">${gvo2.content }</pre></td>
				</tr>
				<tfoot>
					<tr align="center">
						<td colspan="2"><input type="hidden" name="idx"	value="${gvo2.idx}">
							<input type="button" value="수정"	onclick="edit_go(this.form)" /> 
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" value="삭제" onclick="delete_go(this.form)" />
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>

</body>
</html>
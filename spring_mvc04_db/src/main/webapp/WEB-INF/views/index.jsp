<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function go_members() {
		location.href = "/memberslist.do";
	}
	function go_guestbook() {
		location.href = "/guestbooklist.do";
	}
	function go_guestbook2() {
		location.href = "/guestbook2list.do";
	}
</script>
</head>
<body>
	<button onclick="go_members()">Members</button>
	<button onclick="go_guestbook()">GeustBook</button>
	<button onclick="go_guestbook2()">GeustBook2</button>
</body>
</html>
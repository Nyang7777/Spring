<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var login = "${login}";
		if (login == "fail") {
			alert("틀림");
			return;
		} else if (login == "ok") {
			$("#login").css("display","none"); // 감추기
			$("#login_ok").css("display","block"); // 나타내기
		}
	});
</script>
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
	function reg_add_go() {
		location.href = "/member_reg.do";
	}
</script>
</head>
<body>
	<button onclick="go_members()">Members</button>
	<button onclick="go_guestbook()">GeustBook</button>
	<button onclick="go_guestbook2()">GeustBook2</button>
	<hr>
	<div id="login" style="margin: 30px;">
		 <form action="/member_login.do" method="post">
			<p>	아이디: <input type="text" name="m_id" required></p>
			<p>	비밀번호: <input type="password" name ="m_pw" required></p>
			<input type="submit" value="로그인">
		</form>
	</div>
	<div id="login_ok" style="display: none;">
		<h2>${m2vo.m_id} 로그인 성공</h2>
	</div>
	<div id="btns" style="margin: 30px;">
		<button onclick="reg_add_go()">회원가입</button>
		<button onclick="id_find_go()">아이디 찾기</button>
		<button onclick="pw_find_go()">비밀번호 찾기</button>
	</div>
</body>
</html>
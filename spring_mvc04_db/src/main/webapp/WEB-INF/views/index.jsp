<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	function member_logout() {
		location.href = "/member_logout.do";
	}
	function go_bbs() {
		location.href = "/bbs.do";
	}
	function go_board() {
		location.href = "/board_list.do";
	}
	function go_shop() {
		location.href = "/shop_list.do";
	}
	function go_email() {
		location.href = "/email_send.do";
	}
</script>
<!-- jQuery Ajax -->
<script type="text/javascript">
	$(document).ready(function(){
		$("#btn1").on("click",function(){
			$("#result").empty();
			$.ajax({
				url: "/test01.do", // 서버 주소
				method: "post", // 전달 방식
				dataType: "text", // 가져오는 결과 타입
				//data: "", // 서버에 보낼 때 같이 가는 데이터(파라미터)
				//async: true, // 비동기(디폴트, 생략가능), 동기 false
				success: function(data){
					$("#result").append(data);
				},
				error: function(){
					alert("읽기실패");
				}
			});
		});
		
		$("#btn2").on("click",function(){
			$("#result").empty();
			$.ajax({
				url: "/test02.do", // 서버 주소
				method: "post", // 전달 방식
				dataType: "xml", // 가져오는 결과 타입
				//data: "", // 서버에 보낼 때 같이 가는 데이터(파라미터)
				//async: true, // 비동기(디폴트, 생략가능), 동기 false
				success: function(data){
//					console.log(data);
					var table = "<table>";
					table += "<thead><tr><th>종류</th><th>가격</th></tr></thead>";
					table += "<tbody>";
					$(data).find("product").each(function(){
						var name = $(this).find("name").text();
						var price = $(this).find("price").text();
						table += "<tr>";
						table += "<td>" + name + "</td>";
						table += "<td>" + price + "</td>";
						table += "</tr>";
					});
					table += "</tbody>";
					table += "</table>";
					$("#result").append(table);
				},
				error: function(){
					alert("읽기실패");
				}
			});
		});
		
		$("#btn3").on("click",function(){
			$("#result").empty();
			$.ajax({
				url: "/test03.do", // 서버 주소
				method: "post", // 전달 방식
				dataType: "xml", // 가져오는 결과 타입
				//data: "", // 서버에 보낼 때 같이 가는 데이터(파라미터)
				//async: true, // 비동기(디폴트, 생략가능), 동기 false
				success: function(data){
//					console.log(data);
					var table = "<table>";
					table += "<thead><tr><th>번호</th><th>종류</th></tr></thead>";
					table += "<tbody>";
					$(data).find("product").each(function(){
						var name = $(this).attr("name");
						var count = $(this).attr("count");
						table += "<tr>";
						table += "<td>" + count + "</td>";
						table += "<td>" + name + "</td>";
						table += "</tr>";
					});
					table += "</tbody>";
					table += "</table>";
					$("#result").append(table);
				},
				error: function(){
					alert("읽기실패");
				}
			});
		});
		
		$("#btn4").on("click",function(){
			$("#result").empty();
			$.ajax({
				url: "/test04.do", // 서버 주소
				method: "post", // 전달 방식
				dataType: "xml", // 가져오는 결과 타입
				//data: "", // 서버에 보낼 때 같이 가는 데이터(파라미터)
				//async: true, // 비동기(디폴트, 생략가능), 동기 false
				success: function(data){
//					console.log(data);
					var table = "<table>";
					table += "<thead><tr><th>번호</th><th>종류</th><th>회사</th></tr></thead>";
					table += "<tbody>";
					$(data).find("product").each(function(){
						var name = $(this).attr("name");
						var count = $(this).attr("count");
						var company = $(this).text();
						table += "<tr>";
						table += "<td>" + count + "</td>";
						table += "<td>" + name + "</td>";
						table += "<td>" + company + "</td>";
						table += "</tr>";
					});
					table += "</tbody>";
					table += "</table>";
					$("#result").append(table);
				},
				error: function(){
					alert("읽기실패");
				}
			});
		});
		
		$("#btn5").on("click",function(){
			$("#result").empty();
			$.ajax({
				url: "/test05.do", // 서버 주소
				method: "post", // 전달 방식
				dataType: "xml", // 가져오는 결과 타입
				//data: "", // 서버에 보낼 때 같이 가는 데이터(파라미터)
				//async: true, // 비동기(디폴트, 생략가능), 동기 false
				success: function(data){
//					console.log(data);
					var table = "<table>";
					table += "<thead><tr><th>지역</th><th>온도</th><th>상태</th><th>아이콘</th></tr></thead>";
					table += "<tbody>";
					$(data).find("local").each(function(){
						var local = $(this).text();
						var ta = $(this).attr("ta");
						var desc = $(this).attr("desc");
						var icon = $(this).attr("icon");
						table += "<tr>";
						table += "<td>" + local + "</td>";
						table += "<td>" + ta + "</td>";
						table += "<td>" + desc + "</td>";
						table += "<td>" + "<img src='http://www.kma.go.kr/images/icon/NW/NB"+icon+".png' + </td>";
						table += "</tr>";
					});
					table += "</tbody>";
					table += "</table>";
					$("#result").append(table);
				},
				error: function(){
					alert("읽기실패");
				}
			});
		});
		
		$("#btn6").on("click",function(){
			$("#result").empty();
			$.ajax({
				url: "/test06.do", // 서버 주소
				method: "post", // 전달 방식
				dataType: "json", // 가져오는 결과 타입
				success: function(data){
					var table = "<table>";
					table += "<thead><tr>";
					table += "<td>시·도별(1)</td><td>총인구 (명)</td><td>1차 접종 누계</td><td>1차 접종 퍼센트</td><td>2차 접종 누계</td><td>2차 접종 퍼센트</td></tr></thead>";
					table += "<tbody>";
					// 천단위 콤마 : toLocaleString() toLocaleString('ko-KR',{maximumFractionDigits:2})
					$.each(data,function(k,v){
						table += "<tr>";
						table += "<td>"+ v["시·도별(1)"] +"</td>";
						table += "<td>"+ v["총인구 (명)"].toLocaleString() +"</td>";
						table += "<td>"+ v["1차 접종 누계"].toLocaleString() +"</td>";
						table += "<td>"+ v["1차 접종 퍼센트"].toLocaleString('ko-KR',{maximumFractionDigits:2}) +"</td>";
						table += "<td>"+ v["2차 접종 누계"].toLocaleString() +"</td>";
						table += "<td>"+ v["2차 접종 퍼센트"].toLocaleString('ko-KR',{maximumFractionDigits:2}) +"</td>";
						table += "</tr>";
					});
					table += "</tbody></table>";
					$("#result").append(table);
				},
				error: function(){
					alert("읽기실패");
				}
			});
		});
	});
</script>
<script type="text/javascript">
	function kma(){
		location.href = "/kma.do";
	}
</script>
<script type="text/javascript">
	function kma_go2(){
		location.href = "/kma_go2.do";
	}
	function json_go(){
		location.href = "/json_go.do";
	}
	function kakao_map01(){
		location.href = "/kakaomap01.do";
	}
	function kakao_map02(){
		location.href = "/kakaomap02.do";
	}
	function kakao_map03(){
		location.href = "/kakaomap03.do";
	}
	function kakao_map04(){
		location.href = "/kakaomap04.do";
	}
	function kakao_addr(){
		location.href = "/kakaoaddr.do";
	}
	function transaction_go(){
		location.href = "/transaction.do";
	}
</script>
</head>
<body>
	<button onclick="go_members()">Members</button>
	<button onclick="go_guestbook()">GeustBook</button>
	<button onclick="go_guestbook2()">GeustBook2</button>
	<button onclick="go_bbs()">BBS</button>
	<button onclick="go_board()">Board</button>
	<button onclick="go_shop()">Shop</button>
	<button onclick="go_email()">Email</button>
	<hr>
	<c:set var="REST_API_KEY" value="2f6a866a0f9d707e2f98472afa68c08a"/>
	<c:set var="REDIRECT_URI" value="http://localhost:8090/kakaologin.do"/>
	<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}">
		<img alt="" src="/resources/images/kakao_login_medium_narrow.png">
	</a>
	<button onclick="kakao_map01()">카카오 지도 연습1</button>
	<button onclick="kakao_map02()">카카오 지도 연습2</button>
	<button onclick="kakao_map03()">카카오 지도 연습3</button>
	<button onclick="kakao_map04()">카카오 지도 연습4</button>
	<button onclick="kakao_addr()">다음 주소 찾기</button>
	<button onclick="transaction_go()">트랜잭션</button>
	<hr>
	<div id="login" style="margin: 30px;">
		 <form action="/member_login.do" method="post">
			<p>	아이디: <input type="text" name="m_id" required></p>
			<p>	비밀번호: <input type="password" name ="m_pw" required></p>
			<input type="submit" value="로그인">
		</form>
	</div>
	<div id="login_ok" style="display: none;">
		<h2>${mvo.m_id} 로그인 성공</h2>
		<button onclick="member_logout()">로그아웃</button>
	</div>
	<div id="btns" style="margin: 30px;">
		<button onclick="reg_add_go()">회원가입</button>
		<button onclick="id_find_go()">아이디 찾기</button>
		<button onclick="pw_find_go()">비밀번호 찾기</button>
	</div>
	<hr>
	<h2>Spring Ajax</h2>	
	<hr>
	<button id="btn1">텍스트</button>
	<button id="btn2">XML2</button>
	<button id="btn3">XML3</button>
	<button id="btn4">XML4</button>
	<button id="btn5">날씨_XML</button>
	<button onclick="kma()">날씨2_DOM</button>
	<button onclick="kma_go2()">날씨2_SAX</button>
	<button id="btn6">json_ajax</button>
	<button onclick="json_go()">json_ajax2</button>
	<hr>
	<div id="result"></div>
</body>
</html>
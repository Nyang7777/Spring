<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table{
		width: 600px; border-collapse: collapse; text-align: center; margin: auto;
	}
	
	table, th , td{
		border: 1px solid black; padding: 3px;
	}
	
	h2{
		text-align: center;
	}
	
	input{
		padding: 3px;
	}
</style>
<script type="text/javascript">
	function addMembers(f) {
		if (f.m_id.value.trim().length <= 0) {
			alert("아이디 입력");
			f.m_id.focus();
			return;
		}
		
		if (f.m_pw.value.trim().length <= 0) {
			alert("비밀번호 입력");
			f.m_pw.focus();
			return;
		}
		
		if (f.m_name.value.trim().length <= 0) {
			alert("이름 입력");
			f.m_name.focus();
			return;
		}
		
		f.action = "/member_add.do"; // 적절한 URL로 수정
		f.submit();
	}
</script>

</head>
<body>
	<h2> 회원가입 </h2>
	<div>
		<form method="post">
			<table>
				<tbody>
					<tr>
						<th>아이디**</th>
						<td><input type="text" name="m_id"></td>
					</tr>
					<tr>
						<th>비밀번호**</th>
						<td><input type="password" name="m_pw"></td>
					</tr>
					<tr>
						<th>이름**</th>
						<td><input type="text" name="m_name"></td>
					</tr>
					<tr>
						<th>나이</th>
						<td><input type="number" name="m_age"></td>
					</tr>
				</tbody>
				<tfoot>
					<tr><td colspan="2"><input type="button" value="회원가입" onclick="addMembers(this.form)"> </td></tr>
				</tfoot>
			</table>
		</form>
	</div>
</body>
</html>
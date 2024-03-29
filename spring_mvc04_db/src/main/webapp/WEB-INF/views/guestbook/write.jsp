<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 방 명 록 </title>
<link rel="stylesheet" href="resources/css/summernote-lite.css">
<style type="text/css">
	a { text-decoration: none;}
	table{width: 800px; border-collapse:collapse; text-align: center;}
	table,th,td{border: 1px solid black; padding: 3px}
	div{width: 800px; margin:auto; text-align: center;}
	.note-btn-group{width: auto;}
    .note-toolbar{width: auto;}
</style>
<script type="text/javascript">
	function save_go(f) {
		f.action="/jsp/GuestController";
		f.submit();
	}
</script>
</head>
<body>
	<div>
		<h2>방명록 : 작성화면</h2>
		<hr />
		<p>[<a href="">목록으로 이동</a>]</p>
		<form method="post">
			<table>
				<tr align="center">
					<td bgcolor="#99ccff">작성자</td>
					<td><input type="text" name="name" size ="20"/></td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">제  목</td>
					<td><input type="text" name="subject" size ="20"/></td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">email</td>
					<td><input type="text" name="email" size ="20"/></td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">비밀번호</td>
					<td><input type="password" name="pwd" size ="20"/></td>
				</tr>
				<tr align="center">
					<td colspan="2">
						<textarea rows="10" cols="60" name="content" id="content"></textarea>
					</td>
				</tr>
				<tfoot>
					<tr align="center">
						<td colspan="2">
							<input type="hidden" value="write_ok" name="cmd">
							<input type="button" value="저장" onclick="save_go(this.form)" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value="취소" />
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
		<script src="https://code.jquery.com/jquery-3.6.0.min.js" crossorigin="anonymous"></script>
	    <script src="resources/js/summernote-lite.js"></script>
	    <script src="resources/js/lang/summernote-ko-kr.js"></script>
	    <script type="text/javascript">
	    	$(document).ready(function() {
	    		$('#content').summernote({
	    			lang:"ko-KR",
	    			height:300,
	    			focus:true,
	    			callbacks:{
	    				onImageUpload: function(files, editor){
	    					for (var i = 0; i < files.length; i++) {
								sendImage(files[i], editor)
							}
	    				}
	    			}
	    		});
			});
	    	
	    	function sendImage(file, editor) {
	    		console.log("1")
				var frm = new FormData();
				frm.append("s_file",file);
				$.ajax({
					url:"/saveImage.do",
					data: frm,
					type: "post",
					dataType: "json",
					cache:false,
				}).done(function(data){
					console.log("2")
					var path = data.path; // 이미지가 저장된 경로
					var fname = data.fname; // 파일명
					$("#content").summernote("editor.insertImage", path+"/"+fname);
				});
			}
	    	
	    </script>
</body>
</html>
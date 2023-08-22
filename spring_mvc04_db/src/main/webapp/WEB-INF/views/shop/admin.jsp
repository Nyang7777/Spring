<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/summernote-lite.css">
<style type="text/css">
	table, th, td{
		border: 1px solid black;
		border-collapse: collapse;
		text-align: center;
	}
	table {
		width: 600px;
    }
    input{
    	padding: 5px;
    	margin: 5px;
    }
    div{
    	width: 600px;
    	margin: 0px auto;
    }
    h2{
    	text-align: center;
    }
    .note-btn-group{width: auto;}
	.note-toolbar{width: auto;}
</style>
<script type="text/javascript">
	function product_add(f) {
		// 유효성 검사 
		f.action="/product_add.do";
		f.submit();
	}
</script>
</head>
<body>
	<div>
		<h2>상품 등록</h2>
		<form method="post" enctype="multipart/form-data">
			<table>
				<tbody>
					<tr>
						<th>분류</th>
						<td>
							<input type="radio" name="category" value="com001">컴퓨터
							<input type="radio" name="category" value="ele002">가전제품
							<input type="radio" name="category" value="sp003">스포츠
						</td>
					</tr>
					<tr>
						<th>제품번호</th>
						<td><input type="text" name="p_num" > </td>
					</tr>
					<tr>
						<th>제품명</th>
						<td><input type="text" name="p_name" > </td>
					</tr>
					<tr>
						<th>판매사</th>
						<td><input type="text" name="p_company" > </td>
					</tr>
					<tr>
						<th>상품가격</th>
						<td><input type="number" name="p_price" > </td>
					</tr>
					<tr>
						<th>할인가격</th>
						<td><input type="number" name="p_saleprice" > </td>
					</tr>
					<tr>
						<th>상품이미지-S</th>
						<td><input type="file" name="file_s" > </td>
					</tr>
					<tr>
						<th>상품이미지-L</th>
						<td><input type="file" name="file_l" > </td>
					</tr>
					<tr>
						<th colspan="2">상품내용</th>
					</tr>
					<tr>
						<td colspan="2"><textarea id="content" rows="10" cols="50" name="p_content"></textarea></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<input type="button" value="상품등록" onclick="product_add(this.form)">
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" crossorigin="anonymous"></script>
    	<script src="resources/js/summernote-lite.js"></script>
    	<script src="resources/js/lang/summernote-ko-KR2.js"></script>
    	<script type="text/javascript">
    	$(function(){
    		$('#content').summernote({
    			lang : 'ko-KR',
    			height : 300,
    			focus : true,
    			callbacks : {
    				onImageUpload :  function(files, editor){
    					for (var i = 0; i < files.length; i++) {
							sendImage(files[i], editor);
						}
    				}
    			}
			});
    	});
    	
    	function sendImage(file, editor) {
			var frm = new FormData();
			frm.append("s_file",file);
			$.ajax({
				url : "/saveImg.do",
				data : frm,
				type : "post",
				contentType : false,
				processData : false,
				dataType : "json",
			}).done(function(data) {
				var path = data.path;
				var fname = data.fname;
				alert("path : "+path+"\nfname : "+fname);
				$("#content").summernote("editor.insertImage",path+"/"+fname);
			});
		}
    	</script>
</body>
</html>
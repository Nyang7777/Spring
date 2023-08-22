<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function result_go(f){
		f.countnum.value = f.amount.value;
		f.action = "/result4.do";
		f.submit();
	}
</script>
</head>
<body>
	<!-- 트랜잭션: 데이터베이스를 처리할 때 여러개의 작업을 하나의 작업단위로 묶어서 처리하는 것
		더 이상 쪼갤 수 없는 업무의 최소단위를 말한다, 모든 작업이 수행되거나 수행되지 않아야 한다
		일부에서라도 오류가 생긴하면 전체 다 롤백 해야 된다 
		
		root-context.xml에 spring transaction manager를 설정한다-->
		
	<h2> 결재 하기 </h2>
    <form method="post">
        <p> 고객 ID : <input type="text" name="customerId"></p>
        <p> 티켓 구매수 : <input type="number" name="amount"></p>
        <input type="hidden" name="countnum" value="">
        <input type="button" value="구매하기" onclick="result_go(this.form)">
        <input type="reset" value="취소하기">
    </form>
</body>
</html>
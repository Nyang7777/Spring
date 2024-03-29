<%@page import="ex08_web_Annotation.MyCalc2"%>
<%@page import="ex07_web_DI.MyCalc"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="ex06_web_DI.HelloImpl"%>
<%@page import="org.springframework.context.support.GenericXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--
	   // 자바 main에서 사용한 내용 그대로 코딩 
	    ApplicationContext context =
	    	new GenericXmlApplicationContext("ex06_web_DI/config.xml");
		HelloImpl hello = (HelloImpl)context.getBean("hello");
		String msg = hello.sayHello();
		out.println("<h2>"+ msg + "</h2>");
	--%>
     <%
     // config.xml => 환경 설정 파일  
          	// => web에서는  /WEB-INF/spring/root-context.xml => application으로 정해져있다.
          	
          	WebApplicationContext context =
          		WebApplicationContextUtils.getWebApplicationContext(application);
          	HelloImpl hello = (HelloImpl)context.getBean("hello");
     		String msg = hello.sayHello();
     		out.println("<h2>"+ msg + "</h2>");
     		
     		
     		MyCalc mc = (MyCalc)context.getBean("myCalc");
     		int result = mc.plus(10, 20);
     		out.println("<h2> 결과 : 10 + 20 = " + result + "</h2>") ;
     		pageContext.setAttribute("result", result);
     		
     		mc.minus(20, 10);
     		int result2 = mc.getResult();
     		out.println("<h2> 결과 : 20 - 10 = " + result2 + "</h2>") ;
     		pageContext.setAttribute("result2", result2);
     		
     		
     		MyCalc2 mc2 = (MyCalc2)context.getBean("myCalc2");
     		int result3 = mc2.mul(10, 20);
     		out.println("<h2> 결과 : 10 * 20 = " + result3 + "</h2>") ;
     		pageContext.setAttribute("result3", result);
     		
     %>	
     <hr>
     
     <h2>결과 : 10 + 20 = ${result}</h2>
     <h2>결과 : 20 - 10 = ${result2}</h2>
     <h2>결과 : 10 * 20 = ${result3}</h2>
     
</body>
</html>

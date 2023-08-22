package com.ict.ajax.exam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/*
 * 두 개 return이 servlet-context.xml의 ViweResolver로 가서 prefix, suffix를 받아서 뷰가
 * 만들어지고 만들어진 뷰로 넘어간다 prefix -> /WEB-INF/views/"+result+".jsp <- suffix
 * 
 * @RequestMapping("/test.do") public ModelAndView Test01() { ModelAndView mv =
 * new ModelAndView("result");
 * 
 * return mv; }
 * 
 * @RequestMapping("/test.do") public String Test02() { ModelAndView mv = new
 * ModelAndView("result");
 * 
 * return mv; }
 */

// servlet으로 리턴되지 않고 브러우저에 촐력 된다
// 반환형이 String 경우 문서타입이 contentType="text/html" 타임으로 자동 처리
@RestController
public class TestAjaxController {

	@RequestMapping("/test01.do")
	@ResponseBody
	public String Text_TextExam01() {
		String msg = "<h2>한글 한글 Hello World Spring Ajax </h2>";
		return msg;
	}

	@RequestMapping(value = "/test02.do", produces = "text/xml; charset=utf-8")
	@ResponseBody
	public String XML_TextExam01() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<products>");
		sb.append("<product>");
		sb.append("<name>흰우유</name>");
		sb.append("<price>950</price>");
		sb.append("</product>");
		sb.append("<product>");
		sb.append("<name>딸기우유</name>");
		sb.append("<price>1050</price>");
		sb.append("</product>");
		sb.append("<product>");
		sb.append("<name>초코우유</name>");
		sb.append("<price>1100</price>");
		sb.append("</product>");
		sb.append("<product>");
		sb.append("<name>바나나우유</name>");
		sb.append("<price>1550</price>");
		sb.append("</product>");
		sb.append("</products>");
		return sb.toString();
	}

	@RequestMapping(value = "/test03.do", produces = "text/xml; charset=utf-8")
	@ResponseBody
	public String XML_TextExam02() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<products>");
		sb.append("<product count=\"5\" name=\"제너시스\" />");
		sb.append("<product count=\"7\" name=\"카렌스\" />");
		sb.append("<product count=\"9\" name=\"카니발\" />");
		sb.append("<product count=\"5\" name=\"카스타\" />");
		sb.append("<product count=\"2\" name=\"트위치\" />");
		sb.append("</products>");
		return sb.toString();
	}

	@RequestMapping(value = "/test04.do", produces = "text/xml; charset=utf-8")
	@ResponseBody
	public String XML_TextExam03() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<products>");
		sb.append("<product count=\"5\" name=\"제너시스\"> 현대자동자 </product>");
		sb.append("<product count=\"7\" name=\"카렌스\"> 기아자동자 </product>");
		sb.append("<product count=\"9\" name=\"카니발\"> 기아자동자 </product>");
		sb.append("<product count=\"5\" name=\"카스타\"> 기아자동자 </product>");
		sb.append("<product count=\"2\" name=\"트위치\"> 르노자동자 </product>");
		sb.append("</products>");
		return sb.toString();
	} 
	
	@RequestMapping(value = "/test05.do", produces = "text/xml; charset=utf-8")
	@ResponseBody
	public String XML_TextExam04() {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			URL url = new URL("http://www.kma.go.kr/XML/weather/sfc_web_map.xml");
			URLConnection conn = url.openConnection();
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
			String msg = "";
			while ((msg = br.readLine()) !=null) {
				sb.append(msg);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return sb.toString();
		
	} 
	
	@RequestMapping(value = "/test06.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String JSON_Exam05() {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			URL url = new URL("https://raw.githubusercontent.com/paullabkorea/coronaVaccinationStatus/main/data/data.json");
			URLConnection conn = url.openConnection();
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
			String msg = "";
			while ((msg = br.readLine()) !=null) {
				sb.append(msg);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return sb.toString();
	} 

}

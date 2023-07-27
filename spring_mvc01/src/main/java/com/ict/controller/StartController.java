package com.ict.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//어노테이션이 아닌 컨트롤러는 Controller를 상속 받아야 한다
public class StartController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("result");

		// 일 처리 -> db처리, 비지니스 로직처리
		// 일 처리 후 데이터 저장
		mv.addObject("name", "희동이");
		request.setAttribute("age", 3);
		request.getSession().setAttribute("addr", "제주도 서귀포시");
		return mv;
	}

}

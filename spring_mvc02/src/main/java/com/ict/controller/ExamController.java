package com.ict.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExamController {
	
	// a 링크는 get 방식이다
	@GetMapping("/exam01.do")
	public ModelAndView exec01(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("output");
		mv.addObject("name","오천사");
		mv.addObject("age", "1004");
		mv.addObject("addr", "천국");
		return mv;
	}
	
	@GetMapping("/exam02.do")
	public ModelAndView exec02(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("admin/output");
		List<String> dogName = new ArrayList<String>();
		dogName.add("바둑이");
		dogName.add("일");
		dogName.add("이");
		dogName.add("삼");
		dogName.add("상");
		
		mv.addObject("dogName",dogName);
		return mv;
	}
}

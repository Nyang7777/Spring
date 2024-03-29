package com.ict.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StartController {
	
//	@RequestMapping(value = "/start.do", method = RequestMethod.GET)
	@GetMapping(value = "/start.do")
	public ModelAndView start(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("admin/output");
		// 배열
		String[] dogName = { "초복이", "중복이", "말복이", "바둑이" };
		mv.addObject("dogName", dogName);

		return mv;
	}
}

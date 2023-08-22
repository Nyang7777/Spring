package com.ict.ajax.naver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Naver {
	@RequestMapping("/callback")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("callback");
		return mv;
	}
	
	
}

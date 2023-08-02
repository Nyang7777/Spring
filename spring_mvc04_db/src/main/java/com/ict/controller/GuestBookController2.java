package com.ict.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.model.service.GuestBookService2;
import com.ict.model.vo.GuestBookVO;
import com.ict.model.vo.GuestBookVO2;

@Controller
public class GuestBookController2 {
	
	@Autowired
	private GuestBookService2 guestBookService2;
	
	@GetMapping("/guestbook2list.do")
	public ModelAndView getlist() {
		ModelAndView mv = new ModelAndView("guestbook2/list");
		List<GuestBookVO2> glist = guestBookService2.getGuestBookList();
		mv.addObject("list", glist);
		return mv;
	}
	
	@GetMapping("/guestbook2write.do")
	public ModelAndView getwrite() {
		ModelAndView mv = new ModelAndView("guestbook2/write");
		return mv;
	}
	
	@PostMapping("/guestbook2_writeOK.do")
	public ModelAndView getwriteOK(GuestBookVO2 gvo2) {
		ModelAndView mv = new ModelAndView("redirect:/guestbook2list.do");
//		int result = guestBookService2.getGuestBookInsert(gvo2);
		
		System.out.println(gvo2.getF_name());
		return mv;
	}
	
	@GetMapping("/guestbook2onelist.do")
	public ModelAndView getonelist(@ModelAttribute("idx") String idx) {
		ModelAndView mv = new ModelAndView("guestbook2/onelist");
		GuestBookVO2 gvo2 = guestBookService2.getGuestBookOneList(idx);
		mv.addObject("vo",gvo2);
		return mv;
	}
	
}

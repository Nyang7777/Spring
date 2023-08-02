package com.ict.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.model.service.GuestBookService;
import com.ict.model.vo.GuestBookVO;

@Controller
public class GuestBookController {
	// 일처리가 있으면 서비스로 간다
	@Autowired
	private GuestBookService guestBookService;
	
	public GuestBookService getGuestBookService() {
		return guestBookService;
	}

	public void setGuestBookService(GuestBookService guestBookService) {
		this.guestBookService = guestBookService;
	}

	@GetMapping("/guestbooklist.do")
	public ModelAndView getGuestBooklist() {
		ModelAndView mv = new ModelAndView("guestbook/list");
		List<GuestBookVO> glist = getGuestBookService().getGuestBookList();
		mv.addObject("glist", glist);
		return mv;
	}
	
	@GetMapping("/guestbookAddForm.do")
	public ModelAndView getguestbookAddForm() {
		return new ModelAndView("guestbook/write3");
	}
	
	@PostMapping("/guestbook_writeOK.do")
	public ModelAndView getguestbookInsert(GuestBookVO gvo) {
		ModelAndView mv = new ModelAndView("redirect:/guestbooklist.do");
		int result = guestBookService.getGuestBookInsert(gvo);
		return mv;
	}
	
	@GetMapping("/guestbook_onelist.do")
	public ModelAndView getGuestBookOneList(@ModelAttribute("idx") String idx) {
		ModelAndView mv = new ModelAndView("guestbook/onelist");
		GuestBookVO gvo = guestBookService.getGuestBookOneList(idx);
		mv.addObject("gvo",gvo);
		return mv;
	}
	
	@PostMapping("/guestbook_delete_Form.do")
	public ModelAndView getGuestBookDeleteForm(@ModelAttribute("idx") String idx) {
		ModelAndView mv = new ModelAndView("guestbook/delete");
		// 삭제할 때 비밀번호를 검사하기 위해서 getGuestBookOneList()를 실행한다
		GuestBookVO gvo = guestBookService.getGuestBookOneList(idx);
		mv.addObject("gvo",gvo);
		return mv;
	}
	
	@PostMapping("/guestbook_delete.do")
	public ModelAndView getGuestBookDeleteOK(String idx) {
		ModelAndView mv = new ModelAndView("redirect:/guestbooklist.do");
		int result = guestBookService.getGeustBookDelete(idx);
		return mv;
	}
	
	@PostMapping("/guesjbook_edit_Form.do")
	public ModelAndView getGuestBookEditForm(@ModelAttribute("idx") String idx) {
		ModelAndView mv = new ModelAndView("guestbook/update");
		GuestBookVO gvo = guestBookService.getGuestBookOneList(idx);
		mv.addObject("gvo",gvo);
		return mv;
	}
	
	@PostMapping("/guestbook_edit.do")
	public ModelAndView getGuestBookEditOK(GuestBookVO gvo) {
		ModelAndView mv = new ModelAndView("redirect:/guestbook_onelist.do?idx="+gvo.getIdx());
		int result = guestBookService.getGuestBookUpdate(gvo);
		return mv;
	}
	
	
}

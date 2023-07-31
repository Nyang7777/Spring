package com.ict.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.model.service.MembersService;
import com.ict.model.vo.MembersVO;

@Controller
public class MembersController {
	@Autowired
	private MembersService membersService;
	
	public MembersService getMembersService() {
		return membersService;
	}

	public void setMembersService(MembersService membersService) {
		this.membersService = membersService;
	}

	@GetMapping("/memberslist.do")
	public ModelAndView getMembersList() {
		ModelAndView mv = new ModelAndView("members/list");
		List<MembersVO> list = membersService.membersList();
		mv.addObject("list",list);
		return mv;
	}
	
	@GetMapping("/members_addForm.do")
	public ModelAndView getMemberAddForm() {
		return new ModelAndView("members/addForm");
	}
	
	@PostMapping("/members_addMember.do")
	public ModelAndView getMemberAdd(MembersVO mvo) {
		// 리다이렉트
		ModelAndView mv = new ModelAndView("redirect:/memberslist.do");
		int result = membersService.memberAdd(mvo);
		return mv;
	}
	
	@GetMapping("/guestbooklist.do")
	public ModelAndView getGuestBookList() {
		ModelAndView mv = new ModelAndView("members/guestbook_list");
		List<MembersVO> list = membersService.guestList();
		mv.addObject("guest_list",list);
		return mv;
	}
	
	/*
	 * @PostMapping("/guestbookonelist.do") public ModelAndView
	 * getGuestBookOneList(MembersVO mvo) { ModelAndView mv = new
	 * ModelAndView("members/guestbook_onelist"); List<MembersVO> list =
	 * membersService.(); MembersVO mvo = membersService.
	 * mv.addObject("guest_onelist",list); return mv; }
	 */
	
}

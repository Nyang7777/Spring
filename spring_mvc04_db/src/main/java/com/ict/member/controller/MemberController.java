package com.ict.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.member.model.service.MemberService;
import com.ict.model.vo.MembersVO;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/member_reg.do")
	public ModelAndView getMemberReg() {
		return new ModelAndView("members/addForm");
	}
	
	@PostMapping("/member_add.do")
	public ModelAndView getMemberAdd(MembersVO m2vo) {
		ModelAndView mv = new ModelAndView("redirect:/");
		// 패스워드 암호화
		m2vo.setM_pw(bCryptPasswordEncoder.encode(m2vo.getM_pw()));
		int result = memberService.getMemberAdd(m2vo);
		return mv;
	}
	
	@PostMapping("/member_login.do")
	public ModelAndView getMemberLogin(MembersVO m2vo, HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:/");
		// 입력한 아이디에 해당하는 패스워드를 db에서 가져와서 입력한 패스워드와 비교해서 일치하면 성공 다르면 실패
		// id로 패스워드 가져오기
		String pwd = memberService.getMemberPwd(m2vo.getM_id());
		if(!bCryptPasswordEncoder.matches(m2vo.getM_pw(), pwd)) {
			session.setAttribute("login", "fail");
			return mv;
		}else {
			session.setAttribute("login", "ok");
			session.setAttribute("m2vo", m2vo);
			return mv;
		}
	}
	
}

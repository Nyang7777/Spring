package com.ict.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.member.model.service.MemberService;
import com.ict.member.model.vo.MemberVO;
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
	public ModelAndView getMemberLodgIn(MemberVO m2vo, HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:/");
		
		// 입력한 id의 패스워드를 DB에 가져와서 입력한 pwd와 비교해서 맞으면 성공 틀리면 실패
		// id로 DB에 저장된 pwd 가져오기 
		MemberVO mvo = memberService.getMemberPwd(m2vo.getM_id());
		if(! bCryptPasswordEncoder.matches(m2vo.getM_pw(), mvo.getM_pw())) {
			session.setAttribute("login", "fail");
			return mv;
		}else {
			session.setAttribute("mvo", mvo);
			session.setAttribute("login", "ok");
			// admin 성공시
			if(mvo.getM_id().equals("admin")) {
				session.setAttribute("admin", "ok");
			}
			return mv;
		}
	}
	
	@GetMapping("/member_logout.do")
	public ModelAndView getMemberLogout(HttpSession session) {
		// 세션 초기화
		session.removeAttribute("m2vo");
		session.removeAttribute("login");
		session.removeAttribute("admin");
		return new ModelAndView("redirect:/");
	}
	
}

package com.ict.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.dsig.keyinfo.PGPData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.board.model.service.Board_Service;
import com.ict.board.model.vo.Board_VO;
import com.ict.common.Paging;

@Controller
public class Board_Controller {
	@Autowired
	private Board_Service board_Service;
	@Autowired
	private Paging paging;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping("/board_list.do")
	public ModelAndView boardList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("board/list");
		
		int count = board_Service.getTotalCount();
		paging.setTotalRecord(count);
		
		if (paging.getTotalRecord() <= paging.getNumPerPage()) {
			paging.setTotalPage(1);
		}else {
			paging.setTotalPage(paging.getTotalRecord() / paging.getNumPerPage());
			if (paging.getTotalRecord() % paging.getNumPerPage() != 0) {
				paging.setTotalPage(paging.getTotalPage()+1);
			}
		}
		
		String cPage = request.getParameter("cPage");
		if(cPage == null) {
			paging.setNowPage(1);
		}else {
			paging.setNowPage(Integer.parseInt(cPage));
		}
		
		// begin, end 대신에 limit, offset
		// limit = paging.getNumPerPage()
		// offset = limit * (paging.getNowpage()-1)
		paging.setOffset(paging.getNumPerPage() * (paging.getNowPage()-1));
		
		// 시작블록, 끝블록
		paging.setBeginBlock((int)((paging.getNowPage()-1) / paging.getPagePerBlock()) * paging.getPagePerBlock()+1);
		paging.setEndBlock(paging.getBeginBlock()+ paging.getPagePerBlock()-1);
		
		// 주의사항: endblock이 total페이보다 클때가 있다
		if (paging.getEndBlock() > paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalBlock());
		}
		
		List<Board_VO> board_list = board_Service.getList(paging.getOffset(), paging.getNumPerPage());
		mv.addObject("board_list", board_list);
		mv.addObject("paging", paging);
		return mv;
	}
	
	@GetMapping("/board_insertForm.do")
	public ModelAndView boardInsertForm() {
		return new ModelAndView("board/write");
	}
	
	@PostMapping("/board_insert.do")
	public ModelAndView boardInsert(Board_VO bv, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/board_list.do");
		try {
			String path = request.getSession().getServletContext().getRealPath("/resources/images");
			MultipartFile file = bv.getFile();
			if (file.isEmpty()) {
				bv.setF_name("");
			} else {
				// 이름 안겹치게 UUID
				UUID uuid = UUID.randomUUID();
				String f_name = uuid.toString()+"_"+bv.getFile().getOriginalFilename();
				bv.setF_name(f_name);
				
				// 이미지 저장하기
				byte[] in = bv.getFile().getBytes();
				File out = new File(path,f_name);
				FileCopyUtils.copy(in, out);
				
			}
			
			// 패스워드 암호화
			bv.setPwd(bCryptPasswordEncoder.encode(bv.getPwd()));
			int res = board_Service.getInsert(bv);
			return mv;
		} catch (Exception e) {
		}
		return null;
	}
	
	@GetMapping("/board_onelist.do")
	public ModelAndView boardOneList(@ModelAttribute("cPage")String cPage,@ModelAttribute("idx")String idx) {
		ModelAndView mv = new ModelAndView("board/onelist");
		// hit 업데이트
		int hit = board_Service.getBoardHit(idx);
		
		// 상세보기
		Board_VO bv = board_Service.getOneList(idx);
		
		mv.addObject("bv", bv);
		return mv;
	}
	
	@GetMapping("/board_down.do")
	public void boardDown(@RequestParam("f_name")String f_name,HttpServletRequest request, HttpServletResponse response){
		try {
			String path = request.getSession().getServletContext().getRealPath("/resources/images/"+f_name);
			String r_path = URLEncoder.encode(path,"utf-8");
			response.setContentType("appilcation/x-msdownload");
			response.setHeader("content-Disposition", "attachment; filename="+r_path);
			
			File file = new File(new String(path.getBytes()));
			FileInputStream in = new FileInputStream(file);
			OutputStream out = response.getOutputStream();
			FileCopyUtils.copy(in, out);
		} catch (Exception e) {
		}
	}
	
	@PostMapping("/ans_writeFrom.do")
	public ModelAndView boardAnsInsertForm(@ModelAttribute("cPage")String cPage,@ModelAttribute("idx")String idx) {
		return new ModelAndView("board/board_ans_write");
	}
	
	@PostMapping("/ans_insert.do")
	public ModelAndView boardAnsInsert(@ModelAttribute("cPage")String cPage,@ModelAttribute("idx")String idx,Board_VO bv, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/board_list.do");
		try {
			// 상세보기에서 groups, step, lev를 가져온다
			Board_VO bvo = board_Service.getOneList(idx);
			
			int groups = Integer.parseInt(bvo.getGroups());
			int step = Integer.parseInt(bvo.getStep());
			int lev = Integer.parseInt(bvo.getLev());
			
			// step, lev 하나씩 올리기
			step ++;
			lev ++;
			
			// DB의 그룹과 레벨을 업데이트 한다
			// 그룹과 같은 원글을 찾아서 레벨이 갖거나 크면 증가 시킨다
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("groups", groups);
			map.put("lev", lev);
			
			int result = board_Service.getLevupdate(map);
			
			bv.setGroups(String.valueOf(groups));
			bv.setStep(String.valueOf(step));
			bv.setLev(String.valueOf(lev));
			
			// 첨부파일
			String path = request.getSession().getServletContext().getRealPath("/resources/images");
			MultipartFile file = bv.getFile();
			if (file.isEmpty()) {
				bv.setF_name("");
			} else {
				// 이름 안겹치게 UUID
				UUID uuid = UUID.randomUUID();
				String f_name = uuid.toString()+"_"+bv.getFile().getOriginalFilename();
				bv.setF_name(f_name);
				
				// 이미지 저장하기
				byte[] in = bv.getFile().getBytes();
				File out = new File(path,f_name);
				FileCopyUtils.copy(in, out);
				
			}
			
			// 패스워드 암호화
			bv.setPwd(bCryptPasswordEncoder.encode(bv.getPwd()));
			int res = board_Service.getAnsInsert(bv);					
			return mv;
		} catch (Exception e) {
		}
		return null;
	}
	
	@PostMapping("/board_deleteForm.do")
	public ModelAndView boardDeleteForm(@ModelAttribute("cPage")String cPage,@ModelAttribute("idx")String idx) {
		ModelAndView mv = new ModelAndView("board/delete");
		return mv;
	}
	
	@PostMapping("/board_delete.do")
	public ModelAndView boardDelete(@RequestParam("pwd")String pwd,@ModelAttribute("cPage")String cPage,@ModelAttribute("idx")String idx) {
		ModelAndView mv = new ModelAndView();
		Board_VO bv = board_Service.getOneList(idx);
		String dbpwd = bv.getPwd();
		
		if (! bCryptPasswordEncoder.matches(pwd, dbpwd)) {
			mv.setViewName("board/delete");
			mv.addObject("pwchk", "fail");
			return mv;
		} else {
			int result = board_Service.getDelete(idx);
			mv.setViewName("redirect:/board_list.do");
			return mv;
		}
	}
	
	@PostMapping("/board_updateForm.do")
	public ModelAndView boardUpdateForm(@ModelAttribute("cPage")String cPage,@ModelAttribute("idx")String idx) {
		ModelAndView mv = new ModelAndView("board/update");
		Board_VO bv = board_Service.getOneList(idx);
		mv.addObject("bv",bv);
		return mv;
	}
	
	@PostMapping("/board_update.do")
	public ModelAndView boardUpdate(HttpServletRequest request,Board_VO bv,@ModelAttribute("cPage")String cPage,@ModelAttribute("idx")String idx) {
		ModelAndView mv = new ModelAndView();
		
		Board_VO bv2 = board_Service.getOneList(idx);
		String dbpwd = bv.getPwd();
		
		if (! bCryptPasswordEncoder.matches(dbpwd,bv2.getPwd())) {
			mv.setViewName("board/update");
			mv.addObject("pwchk", "fail");
			return mv;
		} else {
			try {
				String path = request.getSession().getServletContext().getRealPath("/resources/images");
				MultipartFile file = bv.getFile();
				if (file.isEmpty()) {
					bv.setF_name("");
				} else {
					// 이름 안겹치게 UUID
					UUID uuid = UUID.randomUUID();
					String f_name = uuid.toString()+"_"+bv.getFile().getOriginalFilename();
					bv.setF_name(f_name);
					
					// 이미지 저장하기
					byte[] in = bv.getFile().getBytes();
					File out = new File(path,f_name);
					FileCopyUtils.copy(in, out);
					
				}
				
				int result = board_Service.getUpdate(bv);
				mv.setViewName("redirect:/board_onelist.do");
				return mv;
				
			} catch (Exception e) {
				return null;
			}
		}
	}
}

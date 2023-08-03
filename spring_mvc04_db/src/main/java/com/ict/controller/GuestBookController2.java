package com.ict.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.model.service.GuestBookService2;
import com.ict.model.vo.GuestBookVO2;

@Controller
public class GuestBookController2 {
	
	@Autowired
	private GuestBookService2 guestBookService2;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/guestbook2list.do")
	public ModelAndView getGuesetBook2List() {
		ModelAndView mv = new ModelAndView("guestbook2/list");
		List<GuestBookVO2> glist = guestBookService2.getGuestBookList();
		mv.addObject("list", glist);
		return mv;
	}
	
	@GetMapping("/guestbook2write.do")
	public ModelAndView getwrite() {
		return new ModelAndView("guestbook2/write");
	}
	
	
	
	 @PostMapping("/guestbook2_insert.do")
		public ModelAndView guestbook2writeOk(GuestBookVO2 gvo2, HttpServletRequest request) {
	     ModelAndView mv = new ModelAndView("redirect:/guestbook2list.do");
	     try {
			String path = request.getSession().getServletContext().getRealPath("/resources/images");
			// 파라미터로 받은 file을 이용해서 DB에 저장할 f_name을 채워야 하지만 같은 이름의 파일이 있으면 덮어쓰기가 된다
			// 그래서 파일이름을 변경해서 DB에 저장해야 되므로 UUID를 이용한다
			UUID uuid = UUID.randomUUID();
			String f_name = uuid.toString()+"_"+gvo2.getFname2().getOriginalFilename();
			
			if (gvo2.getFname2().getSize()<= 0) {
				gvo2.setFname2(new MultipartFile() {
					
					@Override
					public void transferTo(File dest) throws IOException, IllegalStateException {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public boolean isEmpty() {
						// TODO Auto-generated method stub
						return false;
					}
					
					@Override
					public long getSize() {
						// TODO Auto-generated method stub
						return 0;
					}
					
					@Override
					public String getOriginalFilename() {
						// TODO Auto-generated method stub
						return null;
					}
					
					@Override
					public String getName() {
						// TODO Auto-generated method stub
						return null;
					}
					
					@Override
					public InputStream getInputStream() throws IOException {
						// TODO Auto-generated method stub
						return null;
					}
					
					@Override
					public String getContentType() {
						// TODO Auto-generated method stub
						return null;
					}
					
					@Override
					public byte[] getBytes() throws IOException {
						// TODO Auto-generated method stub
						return null;
					}
				});
				
				// 패스워드 암호화 하기
				String pwd = bCryptPasswordEncoder.encode(gvo2.getPwd());
				gvo2.setPwd(pwd);
				System.out.println("변경후 "+gvo2.getPwd());
				
				// DB에 저장하기
				int res = guestBookService2.getGuestBookInsert(gvo2);
				if (res > 0) {
					return mv;
				} else {
					return null;
				}
				
			}else {
				
				System.out.println("파일 이름 "+gvo2.getFname2().getName());
				System.out.println("파일 크기 "+gvo2.getFname2().getSize());
				gvo2.setF_name(f_name);
				// 이미지를 /resources/images 안에 저장하기
				byte[] in = gvo2.getFname2().getBytes();
				File out = new File(path,f_name);
				FileCopyUtils.copy(in, out);
				
				System.out.println("변경전 "+gvo2.getPwd());
				// 패스워드 암호화 하기
				String pwd = bCryptPasswordEncoder.encode(gvo2.getPwd());
				gvo2.setPwd(pwd);
				System.out.println("변경후 "+gvo2.getPwd());
				
				// DB에 저장하기
				int res = guestBookService2.getGuestBookInsert(gvo2);
				if (res > 0) {
					return mv;
				} else {
					return null;
				}
			}
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	 }
	 
	 
	 @GetMapping("/guestbook2onelist.do")
	 public ModelAndView getGeustBook2OneList(String idx){
		ModelAndView mv = new ModelAndView("guestbook2/onelist");
		GuestBookVO2 gvo2 = guestBookService2.getGuestBookOneList(idx);
		mv.addObject("gvo2", gvo2);
		return mv;
	}
	 
	 @GetMapping("/guestbook2_down.do")
	 public void getGeustBook2Down(String f_name, HttpServletRequest request, HttpServletResponse response){
		 String path = request.getSession().getServletContext().getRealPath("/resources/images/"+f_name);
		 try {
			String r_path = URLEncoder.encode(path,"utf-8");
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename"+ r_path);
			
			File file = new File(new String(path.getBytes()));
			FileInputStream in = new FileInputStream(file);
			OutputStream out = response.getOutputStream();
			FileCopyUtils.copy(in, out);
		} catch (Exception e) {
			// TODO: handle exception
		}
		 
	 }
	 
	 @PostMapping("/guestbook2_update_form.do")
	 public ModelAndView getGusetBook2UpdateForm(String idx) {
		 ModelAndView mv = new ModelAndView("guestbook2/update");
		 GuestBookVO2 gvo2 = guestBookService2.getGuestBookOneList(idx);
		 mv.addObject("gvo2", gvo2);
		 return mv;
	 }
	 
	 @PostMapping("/getGuestBook2UpdateOK.do")
	 public ModelAndView getGuestBook2UpdateOK(GuestBookVO2 gvo2, HttpServletRequest request) {
		 ModelAndView mv = new ModelAndView();
		// 비밀번호가 맞는지 틀린지 검사를 해야되는데 암호로 되어있다
		// update 페이지에서 입력한 비밀번호
		String cpwd = gvo2.getPwd();
		
		// DB에서 비밀번호 가져오기
		GuestBookVO2 vo2 = guestBookService2.getGuestBookOneList(gvo2.getIdx());
		String dpwd = vo2.getPwd();
		
		// bCryptPasswordEncoder.matches(입력한 비밀번호, DB에서 가져온 암호화된 비밀번호)
		if(bCryptPasswordEncoder.matches(cpwd, dpwd)) {
			String path = request.getSession().getServletContext().getRealPath("/resources/images");
			try {
				MultipartFile f_param = gvo2.getFname2();
				String old_f_name = gvo2.getOld_f_name();
				
				if (f_param.isEmpty()) {
					gvo2.setF_name(old_f_name);
				}else {
					UUID uuid = UUID.randomUUID();
					String f_name = uuid.toString()+"_"+gvo2.getFname2().getOriginalFilename();
					gvo2.setF_name(f_name);
					
					byte[] in = gvo2.getFname2().getBytes();
					File out = new File(path,f_name);
					FileCopyUtils.copy(in, out);
				}
				int result = guestBookService2.getGuestBookUpdate(gvo2);
				
				mv.setViewName("redirect:/guestbook2onelist.do?idx="+gvo2.getIdx());
				return mv;
				
			} catch (Exception e) {

			}
				return null;
		}else {
			mv.setViewName("guestbook2/update");
			mv.addObject("pwd", "fail");
			mv.addObject("gvo2", gvo2);
			return mv;
		}
		
		
	 }
	 
	 @PostMapping("/guestbook2_delete_form.do")
	 public ModelAndView getGusetBook2DeleteForm(String idx) {
		 ModelAndView mv = new ModelAndView("guestbook2/delte");
		 GuestBookVO2 gvo2 = guestBookService2.getGuestBookOneList(idx);
		 mv.addObject("gvo2", gvo2);
		 return mv;
	 }
	 
}

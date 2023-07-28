package com.ict.test01;

import java.io.File;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class MyController2 {
	
	// 파일 업로드는 post방식이어야 한다
	// pom.xml에 cos 라이브러리를 넣어줘야 한다
	@PostMapping(value ="/f_up.do")
	public ModelAndView getFileUp(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("test01/result3");
		String path = request.getSession().getServletContext().getRealPath("/resources/images");
		
		try {
			MultipartRequest mr = new MultipartRequest(request, path, 500*1024*1024, "utf-8", new DefaultFileRenamePolicy() );
			String name = mr.getParameter("name");
			String f_name = mr.getOriginalFileName("f_name");
			String f_name2 = mr.getFilesystemName("f_name");
			String file_type = mr.getContentType("f_name");
			
			File file = mr.getFile("f_name");
			long size = file.length()/1024; // KB으로 단위 환산
			SimpleDateFormat day = new SimpleDateFormat("yy.MM.dd hh:mm:dd E");
			String lastday = day.format(file.lastModified());
			
			mv.addObject("name",name);
			mv.addObject("f_name",f_name);
			mv.addObject("f_name2",f_name2);
			mv.addObject("file_type",file_type);
			mv.addObject("size",size);
			mv.addObject("lastday",lastday);
			return mv;
		} catch (Exception e) {

		}
		return null;
	}
	
	// 스프링에서 지원하는 파일 업로드
	// servlet-context.xml에 파일 업로드용 클래스 등록
	// 업로드 시 파일 용량 제한 설정
	// 업로드할 때의 이름만 기억
	// @RequestParam -> 파라미터를 받을 때 사용가능
	@PostMapping(value = "/f_up2.do")
	public ModelAndView getFileUp2(@RequestParam("f_name") MultipartFile file,
									@RequestParam("name") String name,
									HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("test01/result4");
		String path = request.getSession().getServletContext().getRealPath("/resources/images");
		try {
			// 같은 이름의 파일이 존재하면 안된다(파일이름 검사 후 같은 이름이 있으면 변경 시킨다)
			String f_name = file.getOriginalFilename();
			String file_type = file.getContentType();
			long size = file.getSize()/1024;
			
			// 실제 이미지를 올리는 작업
			byte[] in = file.getBytes();
			
			// 올릴 장소와 이름을 저장
			File out = new File(path,f_name);
			
			// 파일 복사(upload)
			FileCopyUtils.copy(in, out);
			
			mv.addObject("name",name);
			mv.addObject("f_name",f_name);
			mv.addObject("size",size);
			mv.addObject("file_type",file_type);
			
			return mv;
		} catch (Exception e) {
			return null;
		}
		
	}
}

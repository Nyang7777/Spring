package com.ict.email.model.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	@Autowired
	private JavaMailSender mailSender;
	private MimeMessage message;
	private MimeMessageHelper messageHelper;
	
	public void sendEmail(String randomNumber, String toMail) {
		MailHandler sendMail;
		try {
			sendMail = new MailHandler(mailSender);
			sendMail.setSubject("ICT EDU 인증"); // 메일 제목
			// 내용
			sendMail.setText(""
					+"<table><tbody>"
					+"<tr><td><h2>ICT EDU 메일</h2></td></tr>"
					+"<tr><td><h3>ICT EDU 내용</h3></td></tr>"
					+"<tr><td><font size='20px>'인증 번호 안내</font></td></tr>"
					+"<tr><td></td></tr>"
					+"<tr><td><font size='20px>'인증 번호 생성"+randomNumber+"</font></td></tr>"
					+"<tr><td></td></tr>"
					+"</tbody></table>");
			// 보내는이
			sendMail.setForm("ehdvywoddl@gmail", "ictedu");
			
			// 받는이
			sendMail.setTo(toMail);
			sendMail.send();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

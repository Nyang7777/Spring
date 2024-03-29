package com.ict.email.model.service;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailHandler {
	private JavaMailSender mailsender;
	private MimeMessage message;
	private MimeMessageHelper messageHelper;
	
	public MailHandler(JavaMailSender mailsender) throws Exception {
		this.mailsender = mailsender;
		message = this.mailsender.createMimeMessage();
		messageHelper = new MimeMessageHelper(message, true, "UTF-8");
	}
	
	// 제목
	public void setSubject(String subject) throws Exception{
		messageHelper.setSubject(subject);
	}
	
	// 내용
	public void setText(String text) throws Exception{
		messageHelper.setText(text,true);
	}
	
	// 보낸 이메일
	public void setForm(String email, String name) throws Exception{
		messageHelper.setFrom(email,name);
	}
	
	// 받는 이메일
	public void setTo(String email)throws Exception{
		messageHelper.setTo(email);
	}
	
	// 보내기
	public void send() {
		mailsender.send(message);
	}
}

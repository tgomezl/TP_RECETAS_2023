package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
	
	@Autowired
	private JavaMailSender mailsender;
	
	public void sendEmail(String destinatario, String subject, String cuerpo) {
		SimpleMailMessage mensaje= new SimpleMailMessage();
		mensaje.setFrom("gomez.tahiel@gmail.com");
		mensaje.setTo("tachagl92@gmail.com");
		mensaje.setText("mail de prueba enviado desde java");;
		mensaje.setSubject("tema del mensaje");
		
		mailsender.send(mensaje);
		
		System.out.println("mensaje enviado");
	}
	
	public void sendEmail() {
		SimpleMailMessage mensaje= new SimpleMailMessage();
		mensaje.setFrom("gomez.tahiel@gmail.com");
		mensaje.setTo("tachagl92@gmail.com");
		mensaje.setText("mail de prueba enviado desde java");;
		mensaje.setSubject("tema del mensaje");
		
		mailsender.send(mensaje);
		
		System.out.println("mensaje enviado");
	}

}

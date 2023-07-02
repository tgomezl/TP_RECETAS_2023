package com.example.demo.vistas;

import java.io.Serializable;

public class MailYnickname implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mail;
	
	private String nickname;

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
}



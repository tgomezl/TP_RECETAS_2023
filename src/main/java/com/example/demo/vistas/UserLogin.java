package com.example.demo.vistas;

import java.io.Serializable;

//ES UN DTO
public class UserLogin  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nickname;
	
	private String contrasenia;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String clave) {
		this.contrasenia = clave;
	}
	
	
	
}

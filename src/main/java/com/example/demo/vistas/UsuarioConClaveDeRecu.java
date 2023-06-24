package com.example.demo.vistas;

import java.io.Serializable;

//ES UN DTO
public class UsuarioConClaveDeRecu implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mail;
	
	private String nickname;
	
	private String nombre;
	
	private String avatar;
	
	private String tipo_usuario;
	
	private String contrasenia;
	
	private Integer clavederecu;

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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getTipo_usuario() {
		return tipo_usuario;
	}

	public void setTipo_usuario(String tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Integer getClavederecu() {
		return clavederecu;
	}

	public void setClavederecu(Integer clavederecu) {
		this.clavederecu = clavederecu;
	}
	
	
}

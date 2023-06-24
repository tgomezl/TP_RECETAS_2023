package com.example.demo.vistas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//ES UNA VISTA,devuelve solo las datos del user y de sus recetas
public class UserConReceta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private Integer idUsuario;

	private String mail;
	
	private String nickname;
	
	private String habilitado; //si o no 
	private String nombre;
	private String avatar; //url de la imagen del avatar.
	private String tipo_usuario; //usuario o visitante

//TENDRIAN QUE SER RECETAS-VISTA??????
	private List<RecetasVista> recetas=new ArrayList<>();
	//SON LAS RECETAS CREADAS POR EL USER!!!
	
/*
	private List<ListaRecetas> listas=new ArrayList<>();
	
	ESTO ES TODO
*/	
	public UserConReceta() {
		
	}

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

	public String getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(String habilitado) {
		this.habilitado = habilitado;
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

	public List<RecetasVista> getRecetas() {
		return recetas;
	}

	public void setRecetas(List<RecetasVista> recetas) {
		this.recetas = recetas;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
	

}

package com.example.demo.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuarios implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUsuario")
	private Integer idUsuario;
	//el id es identity pero en sql. no se como es aca
	//ACA TAMBIEN VA IDENTITY!!
	
	
	/*
	private ArrayList<Recetas> recetas=new ArrayList<>();
	
	private ArrayList<Calificaciones> calificaciones=new ArrayList<>();
	*/
	private String mail;
	
	private String nickname;
	
	private String habilitado; //si o no 
	private String nombre;
	private String avatar; //url de la imagen del avatar.
	private String tipo_usuario; //usuario o visitante
	

	//@OneToMany(mappedBy="usuario",fetch = FetchType.EAGER)
	@OneToMany(mappedBy="usuario")
	private List<Recetas> recetas=new ArrayList<>();
	
	/*-----------------------------------------------------------------------*/
	public Usuarios() {
		recetas=new ArrayList<>();
	}
	
	
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
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


	public List<Recetas> getRecetas() {
		return recetas;
	}


	public void setRecetas(ArrayList<Recetas> recetas) {
		this.recetas = recetas;
	}




	/*------------------*/
	
	
	@Override
	public String toString() {
		return "Usuarios [idUsuario=" + idUsuario + ", mail=" + mail + ", nickname=" + nickname + ", habilitado="
				+ habilitado + ", nombre=" + nombre + ", avatar=" + avatar + ", tipo_usuario=" + tipo_usuario + "]";
	}

	
	

}

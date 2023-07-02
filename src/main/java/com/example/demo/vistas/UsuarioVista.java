package com.example.demo.vistas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entidades.ListaRecetas;
import com.example.demo.entidades.Recetas;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;

public class UsuarioVista implements Serializable{
	

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
	
	private String contrasenia;
	
	
	private Integer claveDeRecu;
	
	
	private Boolean esadmin=false;
	
	
	private List<RecetasVista> recetas=new ArrayList<>();
	//SON LAS RECETAS CREADAS POR EL USER!!!
	
	

	//private List<ListaRecetasVista> listas=new ArrayList<>();

	private ListaRecetasVista recetasAintentar=new ListaRecetasVista();


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



	public String getContrasenia() {
		return contrasenia;
	}



	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}



	public Integer getClaveDeRecu() {
		return claveDeRecu;
	}



	public void setClaveDeRecu(Integer claveDeRecu) {
		this.claveDeRecu = claveDeRecu;
	}



	public Boolean getEsadmin() {
		return esadmin;
	}



	public void setEsadmin(Boolean esadmin) {
		this.esadmin = esadmin;
	}



	public List<RecetasVista> getRecetas() {
		return recetas;
	}



	public void setRecetas(List<RecetasVista> recetas) {
		this.recetas = recetas;
	}



	public ListaRecetasVista getRecetasAintentar() {
		return recetasAintentar;
	}



	public void setRecetasAintentar(ListaRecetasVista recetasAintentar) {
		this.recetasAintentar = recetasAintentar;
	}


/*
	public List<ListaRecetasVista> getListas() {
		return listas;
	}



	public void setListas(List<ListaRecetasVista> listas) {
		this.listas = listas;
	}
	
	*/
	

}

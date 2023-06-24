package com.example.demo.vistas;

import java.io.Serializable;

import com.example.demo.entidades.Recetas;
import com.example.demo.entidades.Usuarios;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class CalificacionesVista implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer idCalificacion ;

	private Integer idusuario;


	private Integer idreceta;

	private Integer calificacion ;//es un puntaje

	private String comentarios;
	//es uno o son muchos??? debe ser solo uno

	public Integer getIdCalificacion() {
		return idCalificacion;
	}

	public void setIdCalificacion(Integer idCalificacion) {
		this.idCalificacion = idCalificacion;
	}

	public Integer getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public Integer getIdreceta() {
		return idreceta;
	}

	public void setIdreceta(Integer idreceta) {
		this.idreceta = idreceta;
	}

	public Integer getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	
	

}

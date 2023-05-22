package com.example.demo.entidades;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
//esta ok
@Entity
@Table(name="calificaciones")
public class Calificaciones implements Serializable{
	/*usuario-1-----N-calificacion*/
	
	/*receta-1-----N-calificacion*/
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCalificacion ;

	@ManyToOne
	@JoinColumn(name="idusuario")
	private Usuarios usuario;

	@ManyToOne
	@JoinColumn(name="idReceta")
	private Recetas receta;

	private Integer calificacion ;//es un puntaje

	private String comentarios;
	//es uno o son muchos??? debe ser solo uno

	
	public Calificaciones() {
		
		
	}


	public Integer getIdCalificacion() {
		return idCalificacion;
	}


	public void setIdCalificacion(Integer idCalificacion) {
		this.idCalificacion = idCalificacion;
	}


	public Usuarios getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}


	public Recetas getReceta() {
		return receta;
	}


	public void setReceta(Recetas receta) {
		this.receta = receta;
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

package com.example.demo.vistas;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entidades.Calificaciones;
import com.example.demo.entidades.Foto;
import com.example.demo.entidades.Pasos;
import com.example.demo.entidades.Tipo;
import com.example.demo.entidades.Usuarios;
import com.example.demo.entidades.Utilizado;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class RecetasVista implements Serializable{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idReceta;
	
	private Integer idusuario;
	
	private String nombre;
	
	private String descripcion;
	
	
	private Integer porciones;
	
	private Integer cantidadPersonas;
	
	private Boolean aprobada=false; //por defecto se crean con aprobacion pendiente
	

	private String URLfotounica;
	

	private Tipo idTipo;
	
	private LocalDate fechaCreacion;
	
	private String nombreUsuario;
	
	private List<FotoVista> fotos=new ArrayList<>();

	
	private List<UtilizadoVista> utilizados=new ArrayList<>();
	
	private List<CalificacionesVista> calificaciones=new ArrayList<>();
	
	private List<PasosVista> pasos=new ArrayList<>();
	
	public RecetasVista() {
		
	}


	public Integer getIdReceta() {
		return idReceta;
	}


	public void setIdReceta(Integer idReceta) {
		this.idReceta = idReceta;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

/*
	public Usuarios getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}
*/



	public Integer getPorciones() {
		return porciones;
	}


	public void setPorciones(Integer porciones) {
		this.porciones = porciones;
	}


	public Integer getCantidadPersonas() {
		return cantidadPersonas;
	}


	public void setCantidadPersonas(Integer cantidadPersonas) {
		this.cantidadPersonas = cantidadPersonas;
	}


	public Boolean getAprobada() {
		return aprobada;
	}


	public void setAprobada(Boolean aprobada) {
		this.aprobada = aprobada;
	}




	public Tipo getIdTipo() {
		return idTipo;
	}


	public void setIdTipo(Tipo idTipo) {
		this.idTipo = idTipo;
	}




	public String getNombreUsuario() {
		return nombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


	public Integer getIdusuario() {
		return idusuario;
	}


	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}


	public String getURLfotounica() {
		return URLfotounica;
	}


	public void setURLfotounica(String uRLfotounica) {
		URLfotounica = uRLfotounica;
	}


	public List<FotoVista> getFotos() {
		return fotos;
	}


	public void setFotos(List<FotoVista> fotos) {
		this.fotos = fotos;
	}


	public List<UtilizadoVista> getUtilizados() {
		return utilizados;
	}


	public void setUtilizados(List<UtilizadoVista> utilizados) {
		this.utilizados = utilizados;
	}


	public List<CalificacionesVista> getCalificaciones() {
		return calificaciones;
	}


	public void setCalificaciones(List<CalificacionesVista> calificaciones) {
		this.calificaciones = calificaciones;
	}


	public List<PasosVista> getPasos() {
		return pasos;
	}


	public void setPasos(List<PasosVista> pasos) {
		this.pasos = pasos;
	}
	
	
	
	

}

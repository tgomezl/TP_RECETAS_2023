package com.example.demo.vistas;

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

public class RecetasVista {
	
private Integer idReceta;
	
	private String nombre;
	
	private String descripcion;
	
	
	private LocalDate fechaCreacion;
	

	//private Usuarios usuario;  //es el user que creo la receta!!!!!!!!!
	
	
	private List<Foto> fotos=new ArrayList<Foto>();
	
	
	private List<Pasos> pasos=new ArrayList<Pasos>();
	
	
	private List<Calificaciones> calificaciones=new ArrayList<>();
	
	private Integer porciones;
	
	private Integer cantidadPersonas;
	
	private Boolean aprobada=false; //por defecto se crean con aprobacion pendiente
	

	private String fotounica;
	
	

	private Tipo idTipo;
	

	private List<Utilizado> utilizados=new ArrayList<>();
	
	private String nombreUsuario;
	
	
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

	public List<Foto> getFotos() {
		return fotos;
	}


	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}


	public List<Pasos> getPasos() {
		return pasos;
	}


	public void setPasos(List<Pasos> pasos) {
		this.pasos = pasos;
	}


	public List<Calificaciones> getCalificaciones() {
		return calificaciones;
	}


	public void setCalificaciones(List<Calificaciones> calificaciones) {
		this.calificaciones = calificaciones;
	}


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


	public String getFotounica() {
		return fotounica;
	}


	public void setFotounica(String fotounica) {
		this.fotounica = fotounica;
	}


	public Tipo getIdTipo() {
		return idTipo;
	}


	public void setIdTipo(Tipo idTipo) {
		this.idTipo = idTipo;
	}


	public List<Utilizado> getUtilizados() {
		return utilizados;
	}


	public void setUtilizados(List<Utilizado> utilizados) {
		this.utilizados = utilizados;
	}


	public String getNombreUsuario() {
		return nombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	
	
	

}

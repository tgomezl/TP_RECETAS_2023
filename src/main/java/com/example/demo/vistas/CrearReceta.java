package com.example.demo.vistas;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entidades.Calificaciones;
import com.example.demo.entidades.Foto;
import com.example.demo.entidades.Pasos;
import com.example.demo.entidades.Usuarios;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class CrearReceta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nombre;
	
	private String descripcion;

	
	private Integer porciones;
	
	private Integer cantidadPersonas;
	
	
	private String fotounica;
	
	
	private Integer idtipo;


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


	public String getFotounica() {
		return fotounica;
	}


	public void setFotounica(String fotounica) {
		this.fotounica = fotounica;
	}


	public Integer getIdtipo() {
		return idtipo;
	}


	public void setIdtipo(Integer idtipo) {
		this.idtipo = idtipo;
	}
	
	

}

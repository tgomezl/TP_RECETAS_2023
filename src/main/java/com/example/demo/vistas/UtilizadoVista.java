package com.example.demo.vistas;

import java.io.Serializable;

import com.example.demo.entidades.Ingrediente;
import com.example.demo.entidades.Recetas;
import com.example.demo.entidades.Unidades;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class UtilizadoVista implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idUtilizado;
	
	private Integer idReceta;
	
	
	private Ingrediente idIngrediente;
	
	private Integer cantidad ;
	
	
	private Integer idUnidad;
	
	
	private String observaciones;


	public Integer getIdUtilizado() {
		return idUtilizado;
	}


	public void setIdUtilizado(Integer idUtilizado) {
		this.idUtilizado = idUtilizado;
	}


	public Integer getIdReceta() {
		return idReceta;
	}


	public void setIdReceta(Integer idReceta) {
		this.idReceta = idReceta;
	}


	public Ingrediente getIdIngrediente() {
		return idIngrediente;
	}


	public void setIdIngrediente(Ingrediente idIngrediente) {
		this.idIngrediente = idIngrediente;
	}


	public Integer getCantidad() {
		return cantidad;
	}


	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}


	public Integer getIdUnidad() {
		return idUnidad;
	}


	public void setIdUnidad(Integer idUnidad) {
		this.idUnidad = idUnidad;
	}


	public String getObservaciones() {
		return observaciones;
	}


	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	

}

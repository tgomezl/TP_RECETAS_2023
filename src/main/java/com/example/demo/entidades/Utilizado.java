package com.example.demo.entidades;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//ESTA OK
@Entity
@Table(name="utilizados")
public class Utilizado implements Serializable{

	/**
	 * receta-1-----------n-utilizados
	 * ingrediente-1-------n-utilizados
	 * unidad-1------------n-utilizados
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUtilizado;
	
	@ManyToOne
	@JoinColumn(name="idReceta")
	private Recetas idReceta;
	
	@ManyToOne
	private Ingrediente idIngrediente;
	
	private Integer cantidad ;
	
	@ManyToOne
	private Unidades idUnidad;
	
	
	private String observaciones;
	
	public Utilizado() {
		
	}

	public Integer getIdUtilizado() {
		return idUtilizado;
	}

	public void setIdUtilizado(Integer idUtilizado) {
		this.idUtilizado = idUtilizado;
	}

	public Recetas getIdReceta() {
		return idReceta;
	}

	public void setIdReceta(Recetas idReceta) {
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

	public Unidades getIdUnidad() {
		return idUnidad;
	}

	public void setIdUnidad(Unidades idUnidad) {
		this.idUnidad = idUnidad;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	
	
	
	
}

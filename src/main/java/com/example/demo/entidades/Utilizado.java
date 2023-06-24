package com.example.demo.entidades;

import java.io.Serializable;

import com.example.demo.vistas.UtilizadoVista;

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
	@JoinColumn(name="idIngrediente")
	private Ingrediente idIngrediente;
	
	private Integer cantidad ;
	
	@ManyToOne
	@JoinColumn(name="idUnidad")
	private Unidades idUnidad;
	
	
	private String observaciones;
	/*-----------------------------------------------------------------------*/
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

	public UtilizadoVista toView(Utilizado u) {
		// TODO Auto-generated method stub
		
		System.out.println("  dentro de utilizado to view");
		UtilizadoVista utilizado=new UtilizadoVista();
		utilizado.setCantidad(u.getCantidad());
		utilizado.setIdIngrediente(u.getIdIngrediente());
		utilizado.setIdReceta(u.getIdReceta().getIdReceta());
		utilizado.setIdUnidad(u.getIdUnidad().getIdUnidad());
		utilizado.setObservaciones(u.getObservaciones());
		utilizado.setIdUtilizado(u.getIdUtilizado());
	
		return utilizado;
	}
	
	
	
	
	
}

package com.example.demo.vistas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entidades.Pasos;
import com.example.demo.entidades.Tipo;
import com.example.demo.entidades.Utilizado;

public class RecetaMultiplicadaVista implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean aprobada;

	private Integer cantidadPersonas;
	
	private String descripcion;

	private String fotounica;

	private Integer idTipo;
	
	private String nombre;

	private List<PasosVista> pasos=new ArrayList<PasosVista>();
	
	private Integer porciones;
	
	
	private List<UtilizadoVista> utilizados=new ArrayList<UtilizadoVista>();


	public boolean isAprobada() {
		return aprobada;
	}


	public void setAprobada(boolean aprobada) {
		this.aprobada = aprobada;
	}


	public Integer getCantidadPersonas() {
		return cantidadPersonas;
	}


	public void setCantidadPersonas(Integer cantidadPersonas) {
		this.cantidadPersonas = cantidadPersonas;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getFotounica() {
		return fotounica;
	}


	public void setFotounica(String fotounica) {
		this.fotounica = fotounica;
	}


	public Integer getIdTipo() {
		return idTipo;
	}


	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public List<PasosVista> getPasos() {
		return pasos;
	}


	public void setPasos(List<PasosVista> pasos) {
		this.pasos = pasos;
	}


	public Integer getPorciones() {
		return porciones;
	}


	public void setPorciones(Integer porciones) {
		this.porciones = porciones;
	}


	public List<UtilizadoVista> getUtilizados() {
		return utilizados;
	}


	public void setUtilizados(List<UtilizadoVista> utilizados) {
		this.utilizados = utilizados;
	}

	
}

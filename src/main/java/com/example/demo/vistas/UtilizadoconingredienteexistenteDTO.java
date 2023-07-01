package com.example.demo.vistas;

import java.io.Serializable;

public class UtilizadoconingredienteexistenteDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer idreceta;
	
	//si no existe debe recibir un -1 como idingrdiente
	private Integer idingrediente;
	
	private Integer cantidad;
	
	private Integer idunidad;
	
	private String observacion;
	
	private String nombreingrediente;

	public Integer getIdreceta() {
		return idreceta;
	}

	public void setIdreceta(Integer idreceta) {
		this.idreceta = idreceta;
	}

	public Integer getIdingrediente() {
		return idingrediente;
	}

	public void setIdingrediente(Integer idingrediente) {
		this.idingrediente = idingrediente;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getIdunidad() {
		return idunidad;
	}

	public void setIdunidad(Integer idunidad) {
		this.idunidad = idunidad;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getNombreingrediente() {
		return nombreingrediente;
	}

	public void setNombreingrediente(String nombreingrediente) {
		this.nombreingrediente = nombreingrediente;
	}
	
	

	
	
}

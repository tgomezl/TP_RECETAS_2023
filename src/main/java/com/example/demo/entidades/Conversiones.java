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
@Table(name="conversiones")
public class Conversiones implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * unidad-1------------n-conversiones
	 * 
	 * */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idconversion;

	@ManyToOne
	@JoinColumn(name="idUnidadOrigen")
	private Unidades unidadorigen;

	@ManyToOne
	@JoinColumn(name="idUnidadDestino")
	private Unidades unidaddestino;

	private Double factorConversiones;
	/*-----------------------------------------------------------------------*/
	
	public Conversiones() {
		
		
	}


	public Integer getIdconversion() {
		return idconversion;
	}


	public void setIdconversion(Integer idconversion) {
		this.idconversion = idconversion;
	}


	public Unidades getUnidadorigen() {
		return unidadorigen;
	}


	public void setUnidadorigen(Unidades unidadorigen) {
		this.unidadorigen = unidadorigen;
	}


	public Unidades getUnidaddestino() {
		return unidaddestino;
	}


	public void setUnidaddestino(Unidades unidaddestino) {
		this.unidaddestino = unidaddestino;
	}


	public Double getFactorConversiones() {
		return factorConversiones;
	}


	public void setFactorConversiones(Double factorConversiones) {
		this.factorConversiones = factorConversiones;
	}
	

	
}

package com.example.demo.vistas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entidades.Multimedia;
import com.example.demo.entidades.Recetas;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class PasosVista implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer idreceta;

	private Integer idPaso;
	
	
	private Integer nroPaso;
	
	private String texto;

	private List<MultimediaVista> multimedia=new ArrayList<>();

	public Integer getIdreceta() {
		return idreceta;
	}

	public void setIdreceta(Integer idreceta) {
		this.idreceta = idreceta;
	}

	public Integer getIdPaso() {
		return idPaso;
	}

	public void setIdPaso(Integer idPaso) {
		this.idPaso = idPaso;
	}

	public Integer getNroPaso() {
		return nroPaso;
	}

	public void setNroPaso(Integer nroPaso) {
		this.nroPaso = nroPaso;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public List<MultimediaVista> getMultimedia() {
		return multimedia;
	}

	public void setMultimedia(List<MultimediaVista> multimedia) {
		this.multimedia = multimedia;
	}

	
	
}

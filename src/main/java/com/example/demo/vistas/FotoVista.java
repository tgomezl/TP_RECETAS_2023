package com.example.demo.vistas;

import java.io.Serializable;

import com.example.demo.entidades.Recetas;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class FotoVista implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idfoto;
	
	private Integer idReceta;
	
	private String urlFoto;
	
	
	private String extension;


	public Integer getIdfoto() {
		return idfoto;
	}


	public void setIdfoto(Integer idfoto) {
		this.idfoto = idfoto;
	}


	public Integer getIdReceta() {
		return idReceta;
	}


	public void setIdReceta(Integer idReceta) {
		this.idReceta = idReceta;
	}


	public String getUrlFoto() {
		return urlFoto;
	}


	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}


	public String getExtension() {
		return extension;
	}


	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	


}

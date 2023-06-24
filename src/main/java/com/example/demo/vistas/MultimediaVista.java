package com.example.demo.vistas;

import java.io.Serializable;

import com.example.demo.entidades.Pasos;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


public class MultimediaVista implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idContenido;
	
	private String tipo_contenido; //foto, video, audio
	
	
	private Integer idpaso;
	
	private String extension;
	
	private String urlContenido;

	public Integer getIdContenido() {
		return idContenido;
	}

	public void setIdContenido(Integer idContenido) {
		this.idContenido = idContenido;
	}

	public String getTipo_contenido() {
		return tipo_contenido;
	}

	public void setTipo_contenido(String tipo_contenido) {
		this.tipo_contenido = tipo_contenido;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getUrlContenido() {
		return urlContenido;
	}

	public void setUrlContenido(String urlContenido) {
		this.urlContenido = urlContenido;
	}

	public Integer getIdpaso() {
		return idpaso;
	}

	public void setIdpaso(Integer idpaso) {
		this.idpaso = idpaso;
	}
	
	
	

}

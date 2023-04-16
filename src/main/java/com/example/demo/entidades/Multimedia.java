package com.example.demo.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Multimedia {
	
	public Multimedia() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idContenido;
	
	private String tipo_contenido; //foto, video, audio
	
	private Integer idPaso;
	
	private String extension;
	
	private String url;

	public Integer getIdcontenido() {
		return idContenido;
	}

	public void setIdcontenido(Integer idContenido) {
		this.idContenido = idContenido;
	}

	public String getTipo_contenido() {
		return tipo_contenido;
	}

	public void setTipo_contenido(String tipo_contenido) {
		this.tipo_contenido = tipo_contenido;
	}

	public Integer getIdPaso() {
		return idPaso;
	}

	public void setIdPaso(Integer idPaso) {
		this.idPaso = idPaso;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Multimedia [idContenido=" + idContenido + ", tipo_contenido=" + tipo_contenido + ", idPaso=" + idPaso
				+ ", extension=" + extension + ", url=" + url + "]";
	}

	
	

}

package com.example.demo.entidades;

import java.io.Serializable;

import com.example.demo.vistas.MultimediaVista;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//ESTA OK
@Entity
@Table(name="multimedia")
public class Multimedia implements Serializable{
	
	
	/**
	 * multimedia-n------------1-paso
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idContenido;
	
	private String tipo_contenido; //foto, video, audio
	
	@ManyToOne
	@JoinColumn(name="idPaso")
	private Pasos idPaso;
	
	private String extension;
	
	private String urlContenido;
	/*-----------------------------------------------------------------------*/
	
	public Multimedia() {
		idPaso=null;
	}


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


	public Pasos getIdPaso() {
		return idPaso;
	}


	public void setIdPaso(Pasos idPaso) {
		this.idPaso = idPaso;
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


	public MultimediaVista toView(Multimedia m) {
		// TODO Auto-generated method stub
		System.out.println("dentro de multimedia toview");
		
		MultimediaVista nuevo = new MultimediaVista();
		nuevo.setExtension(m.getExtension());
		nuevo.setIdContenido(m.getIdContenido());
		nuevo.setIdpaso(m.getIdPaso().getIdPaso());
		nuevo.setTipo_contenido(m.getTipo_contenido());
		nuevo.setUrlContenido(m.getUrlContenido());
		return nuevo;
	}

	
	
	
	

}

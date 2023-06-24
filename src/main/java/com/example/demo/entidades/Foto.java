package com.example.demo.entidades;

import java.io.Serializable;

import com.example.demo.vistas.FotoVista;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
//ESTA OK
@Entity
@Table(name="fotos")
public class Foto implements Serializable{

	/**
	 * 
	 
	 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idfoto;
	
	
	/*receta--1-------------n--foto es vider*/
	@ManyToOne
	@JoinColumn(name="idReceta")
	private Recetas idReceta;
	
	private String urlFoto;
	
	
	private String extension;

	/*-----------------------------------------------------------------------*/
	
	public Foto() {
		
	}



	public Integer getIdfoto() {
		return idfoto;
	}



	public void setIdfoto(Integer idfoto) {
		this.idfoto = idfoto;
	}



	public Recetas getIdReceta() {
		return idReceta;
	}



	public void setIdReceta(Recetas idReceta) {
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


	
	public FotoVista toview(Foto f) {
		// TODO Auto-generated method stub
		System.out.println("dentro de foto toview");
		FotoVista vista=new FotoVista();
		vista.setExtension(f.getExtension());
		vista.setIdfoto(f.getIdfoto());
		vista.setIdReceta(f.getIdReceta().getIdReceta());
		vista.setUrlFoto(f.getUrlFoto());
		return vista;
	}
	
	
	
}

package com.example.demo.vistas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entidades.Recetas;
import com.example.demo.entidades.Usuarios;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

public class ListaRecetasVista implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String nombrelista;
	

	//private Integer idUsuario;
	
	
	private List<RecetasVista> recetas = new ArrayList<>();


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNombrelista() {
		return nombrelista;
	}


	public void setNombrelista(String nombrelista) {
		this.nombrelista = nombrelista;
	}

/*
	public Integer getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
*/

	public List<RecetasVista> getRecetas() {
		return recetas;
	}


	public void setRecetas(List<RecetasVista> recetas) {
		this.recetas = recetas;
	}
	
	public void ADDRECETAVISTA(RecetasVista entidad) {
		this.recetas.add(entidad);
	}
	
	
	

}

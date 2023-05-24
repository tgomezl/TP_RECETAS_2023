package com.example.demo.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="listaDeRecetas")
public class ListaRecetas implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * 
	 * 
	 * */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nombrelista;
	
	//@ManyToOne   ESTA DEL OTRO LADO
	/*usuario-1------------n--listarecetas*/
	private Usuarios idUsuario;
	
	
	/*listarecetas-N-----------------N-recetas
	 * es unider*/
	@ManyToMany
	@JoinTable(name="listaRecetas_receta",
	joinColumns = @JoinColumn(name="listaRecetas_id"),
	inverseJoinColumns = @JoinColumn(name="receta_id"))
	private List<Recetas> recetas = new ArrayList<>();
	
	
	/*-------------------------------------------------*/
	public ListaRecetas() {
		
		
	}

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

	public Usuarios getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Usuarios idUsuario) {
		this.idUsuario = idUsuario;
	}

	public List<Recetas> getRecetas() {
		return recetas;
	}

	public void setRecetas(List<Recetas> recetas) {
		this.recetas = recetas;
	}
	
	
	
	

}

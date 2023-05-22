package com.example.demo.entidades;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//ESTA OK
@Entity
@Table(name="ingredientes")
public class Ingrediente implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer idIngrediente;
	
	private String nombre;
	/*-----------------------------------------------------------------------*/
	public Ingrediente() {
		
		
	}

	public Integer getIdIngrediente() {
		return idIngrediente;
	}

	public void setIdIngrediente(Integer idIngrediente) {
		this.idIngrediente = idIngrediente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}

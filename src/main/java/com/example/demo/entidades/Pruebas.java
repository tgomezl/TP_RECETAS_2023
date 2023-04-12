package com.example.demo.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Pruebas {
	
	@Id
	int id;
	
	String nombre;
	
	String apellido;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Pruebas() {
		super();
	}
	
	
	

}

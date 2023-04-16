package com.example.demo.entidades;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Recetas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nombre;
	
	
	private Integer idUsuario;
	
	private String descripcion;
	
	private Integer porciones;
	
	private Integer cantidadPersonas;
	
	//aca lleva alguna anotacion
	//private ArrayList<Pasos> pasos;
	
	//aca lleva alguna anotacion
	//private ArrayList<Calificaciones> calificaciones;
	
}

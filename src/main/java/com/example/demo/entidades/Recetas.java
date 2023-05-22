package com.example.demo.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="recetas")
public class Recetas implements Serializable{

	/**
	 *   recetas-1-----------N--utilizados
	 *  
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idReceta;
	
	private String nombre;
	
	private String descripcion;
	
	
	/*recetas--N----------1--usuario. 	*/
	/*deberia ser bidireccional??*/
	@ManyToOne
	@JoinColumn(name="idUsuario")
	private Usuarios usuario;
	
	/*recetas-1-----------N--fotos es bider */
	@OneToMany(mappedBy="idReceta",fetch = FetchType.EAGER)
	private List<Foto> fotos=new ArrayList<Foto>();
	
	/*recetas-1------------N-pasos  es bider*/
	@OneToMany(mappedBy="idreceta",fetch = FetchType.EAGER)
	private List<Pasos> pasos=new ArrayList<Pasos>();
	
	/* recetas--1-----------N-calificaciones es bider*/
	@OneToMany(mappedBy="receta",fetch = FetchType.EAGER)
	private List<Calificaciones> calificaciones=new ArrayList<>();
	
	private Integer porciones;
	
	private Integer cantidadPersonas;
	
	@Column(name="foto")/*siempre tiene al menos una*/
	private String fotounica;
	
	
	/*recetas-N-----------1--tipos por ahora es unider*/
	/*segun el DER la receta tien un unico TIPO*/
	@ManyToOne
	private Tipo idTipo;
	
	/*recetas-1-----------N--utilizados  es bider*/
	@OneToMany(mappedBy="idReceta",fetch = FetchType.EAGER)
	private List<Utilizado> utilizados=new ArrayList<>();
	
	/*-----------------------------------------------------------------------*/
	public Recetas() {
		
		
	}

	public Integer getIdReceta() {
		return idReceta;
	}

	public void setIdReceta(Integer idReceta) {
		this.idReceta = idReceta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public List<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(ArrayList<Foto> fotos) {
		this.fotos = fotos;
	}

	public List<Pasos> getPasos() {
		return pasos;
	}

	public void setPasos(ArrayList<Pasos> pasos) {
		this.pasos = pasos;
	}

	public List<Calificaciones> getCalificaciones() {
		return calificaciones;
	}

	public void setCalificaciones(ArrayList<Calificaciones> calificaciones) {
		this.calificaciones = calificaciones;
	}

	public Integer getPorciones() {
		return porciones;
	}

	public void setPorciones(Integer porciones) {
		this.porciones = porciones;
	}

	public Integer getCantidadPersonas() {
		return cantidadPersonas;
	}

	public void setCantidadPersonas(Integer cantidadPersonas) {
		this.cantidadPersonas = cantidadPersonas;
	}

	public String getFotounica() {
		return fotounica;
	}

	public void setFotounica(String fotounica) {
		this.fotounica = fotounica;
	}

	public Tipo getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Tipo idTipo) {
		this.idTipo = idTipo;
	}

	public List<Utilizado> getUtilizados() {
		return utilizados;
	}

	public void setUtilizados(ArrayList<Utilizado> utilizados) {
		this.utilizados = utilizados;
	}
	
	
	
	
}

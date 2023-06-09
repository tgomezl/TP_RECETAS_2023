package com.example.demo.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.vistas.RecetasVista;
import com.example.demo.vistas.UsuariosVista;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
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
	
	
	private LocalDate fechaCreacion;
	
	/*recetas--N----------1--usuario. 	*/
	/*deberia ser bidireccional??*/
	@ManyToOne
	@JoinColumn(name="idUsuario")
	@JsonBackReference
	private Usuarios usuario;  //es el user que creo la receta!!!!!!!!!
	
	/*recetas-1-----------N--fotos es bider */
	@OneToMany(mappedBy="idReceta",fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
	private List<Foto> fotos=new ArrayList<Foto>();
	
	/*recetas-1------------N-pasos  es bider*/
	@OneToMany(mappedBy="idreceta",fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
	@JsonManagedReference
	private List<Pasos> pasos=new ArrayList<Pasos>();
	
	/* recetas--1-----------N-calificaciones es bider*/
	@OneToMany(mappedBy="receta",fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
	private List<Calificaciones> calificaciones=new ArrayList<>();
	
	private Integer porciones;
	
	private Integer cantidadPersonas;
	
	private Boolean aprobada=false; //por defecto se crean con aprobacion pendiente
	
	@Column(name="foto")/*siempre tiene al menos una*/
	private String fotounica;
	
	
	/*recetas-N-----------1--tipos por ahora es unider*/
	/*segun el DER la receta tien un unico TIPO*/
	@ManyToOne
	@JoinColumn(name="idTipo")
	private Tipo idTipo;
	
	/*recetas-1-----------N--utilizados  es bider*/
	@OneToMany(mappedBy="idReceta",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
	private List<Utilizado> utilizados=new ArrayList<>();
	
	
	
	/*-----------------------------------------------------------------------*/
	public Recetas() {
		
		fechaCreacion=LocalDate.now();
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

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}



//metodo para agregar pasos
	public void ADDpasos(Pasos paso) {
		//va y viene
		this.pasos.add(paso);
		
		paso.setIdreceta(this);
		
	}
//metodo para quitar paso
	public void QUITARpasos(Pasos paso) {
		//va y viene
		this.pasos.remove(paso);
		
		paso.setIdreceta(null);
		
	}
	
	
	public Boolean getAprobada() {
		return aprobada;
	}




	public void setAprobada(Boolean aprobada) {
		this.aprobada = aprobada;
	}




	//es solo para crear recetas mock
	public void setearParametrosMock(Usuarios user, Tipo tipo) {
		// TODO Auto-generated method stub
		this.nombre="tarta de atun";
		
		this.descripcion="receta de atun de mi abuela";
		
		
		this.fechaCreacion=LocalDate.now();
		
		
		//this.usuario=user;  //No DeJARLO COMO NULL
		
	
		this.fotos=new ArrayList<Foto>();
		
		
		this.pasos=new ArrayList<Pasos>();
		
	
		this.calificaciones=new ArrayList<>();
		
		this.porciones=6;
		
		this.cantidadPersonas=2;
		

		this.fotounica="url de una foto";
		

		this.idTipo=tipo;
		
		this.utilizados=new ArrayList<>();
		
		
		
	}
	
	public RecetasVista crearRecetaVista(Recetas r) {
		
		RecetasVista recetavista= new RecetasVista();
		
		recetavista.setAprobada(r.getAprobada());
		recetavista.setCalificaciones(r.getCalificaciones());
		recetavista.setCantidadPersonas(r.getCantidadPersonas());
		recetavista.setDescripcion(r.getDescripcion());
		recetavista.setFechaCreacion(r.getFechaCreacion());
		recetavista.setFotos(r.getFotos());
		recetavista.setFotounica(r.getFotounica());
		recetavista.setIdReceta(r.getIdReceta());
		recetavista.setIdTipo(r.getIdTipo());
		recetavista.setNombre(r.getNombre());
		if(r.getUsuario()!=null) {
			recetavista.setNombreUsuario(r.getUsuario().getNombre());
		}
		
		recetavista.setPasos(r.getPasos());
		recetavista.setPorciones(r.getPorciones());
		recetavista.setUtilizados(r.getUtilizados());
		
		return recetavista;
		
	}








	
	
	
	
	
}

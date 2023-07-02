package com.example.demo.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.vistas.RecetaMultiplicadaVista;
import com.example.demo.vistas.CalificacionesVista;
import com.example.demo.vistas.FotoVista;
import com.example.demo.vistas.PasosVista;
import com.example.demo.vistas.RecetasVista;
import com.example.demo.vistas.UtilizadoVista;
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
	@OneToMany(mappedBy="idReceta",fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
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

	//public void setPasos(ArrayList<Pasos> pasos) {
	//	this.pasos = pasos;
	//}
	
	public void setPasos(List<Pasos> pasos) {
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
	
	public void setUtilizados(List<Utilizado> utilizados) {
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
	
	public RecetasVista toView(Recetas r) {
		//la receta tiene que converitr todo en una vista
		System.out.println("dentro de recetas toview");
		
		RecetasVista recetavista= new RecetasVista();
		
		recetavista.setAprobada(r.getAprobada());

		recetavista.setCantidadPersonas(r.getCantidadPersonas());
		recetavista.setDescripcion(r.getDescripcion());
		recetavista.setFechaCreacion(r.getFechaCreacion());
		recetavista.setURLfotounica(r.getFotounica());
		recetavista.setIdReceta(r.getIdReceta());
		recetavista.setIdusuario(r.getUsuario().getIdUsuario());
		recetavista.setIdTipo(r.getIdTipo());
		recetavista.setNombre(r.getNombre());
		if(r.getUsuario()!=null) {
			recetavista.setNombreUsuario(r.getUsuario().getNombre());
		}
		recetavista.setPorciones(r.getPorciones());
		
		//la receta tiene paso y multimedia
		List<Pasos> pasos=r.getPasos();
		List<PasosVista> pasosvista =new ArrayList<PasosVista>();
		for(Pasos p:pasos) {
			//el paso tiene multimedia
			pasosvista.add(p.toView(p));
		}
		
		recetavista.setPasos(pasosvista);
		
		//tiene fotos
		List<Foto>fotos=r.getFotos();
		List<FotoVista> fotosvista=new ArrayList<>();
		
		for(Foto f:fotos) {
			fotosvista.add(f.toview(f));
		}
		recetavista.setFotos(fotosvista);
		
		//tiene utilizados
		List<Utilizado> utilizados=r.getUtilizados();
		List<UtilizadoVista> utilizadosvista=new ArrayList<>();
		for(Utilizado u:utilizados) {
			utilizadosvista.add(u.toView(u));
		}
		
		recetavista.setUtilizados(utilizadosvista);
		//tiene calificaciones
		List<Calificaciones> calificacioens=r.getCalificaciones();
		List<CalificacionesVista> calivista=new ArrayList<>();
		for(Calificaciones c:calificacioens) {
			calivista.add(c.toView(c));
			
		}
		recetavista.setCalificaciones(calivista);
		
		//tiene el ID del user
		System.out.println(" construi la recetavista");
		return recetavista;
		
	}
	
	public void ADDutilizado(Utilizado util) {
		this.utilizados.add(util);
	}
	
	public void REMOVEutilizado(Utilizado util) {
		this.utilizados.remove(util);
	}
	
	public void ADDcalificacaion(Calificaciones cali) {
		this.calificaciones.add(cali);
	}
	
	public void REMOVEcalificacion(Calificaciones cali) {
		this.calificaciones.remove(cali);
	}
/*
	public RecetasVista crearVistaCompleta(Recetas r) {
		// TODO Auto-generated method stub
		RecetasVista recetavista= new RecetasVista();
		
		recetavista.setAprobada(r.getAprobada());
		recetavista.setAprobada(r.getAprobada());

		recetavista.setCantidadPersonas(r.getCantidadPersonas());
		recetavista.setDescripcion(r.getDescripcion());
		recetavista.setFechaCreacion(r.getFechaCreacion());
		recetavista.setURLfotounica(r.getFotounica());
		recetavista.setIdReceta(r.getIdReceta());
		recetavista.setIdTipo(r.getIdTipo());
		recetavista.setNombre(r.getNombre());
		if(r.getUsuario()!=null) {
			recetavista.setNombreUsuario(r.getUsuario().getNombre());
		}
		recetavista.setPorciones(r.getPorciones());
		
		//OJO:a la recetavista le faltan mas cosas
		//paso y multimedia
		List<Pasos> pasos=r.getPasos();
		List<PasosVista> pasosvista=new ArrayList<>();
		for(Pasos p:pasos) {
			pasosvista.add(p.toView(p));
		}
		//foto
		
		//utilizado
		
		//calificaion
		
		//usuario
		
		
		
		return recetavista;
		
	
	}


*/




	public void ADDFoto(Foto foto) {
		// TODO Auto-generated method stub
		this.fotos.add(foto);
		
	}
	
	public void REMOVEfoto(Foto foto) {
		this.fotos.remove(foto);
	}




	public Recetas multiplicar(Recetas esta, Integer factor) {
		// TODO Auto-generated method stub
		System.out.println("   multiplicando receta");
		Recetas multiplicada =new Recetas();
		multiplicada.setAprobada(esta.getAprobada());
		multiplicada.setCantidadPersonas(esta.getCantidadPersonas()*factor);
		multiplicada.setDescripcion(esta.getDescripcion());
		multiplicada.setFotounica(esta.getFotounica());
		multiplicada.setIdTipo(esta.getIdTipo());
		multiplicada.setNombre(esta.getNombre());
		multiplicada.setPasos(esta.getPasos());
		multiplicada.setPorciones(esta.getPorciones()*factor);
		multiplicada.setUsuario(null);
		List<Utilizado> utilizadosmodificados=new ArrayList<Utilizado>();
		List<Utilizado> utilizados=esta.getUtilizados();
		for(Utilizado u:utilizados) {
			Utilizado util=u.multiplicar(u,factor);
			utilizadosmodificados.add(util);
		}
		multiplicada.setUtilizados( utilizadosmodificados);
		
		return multiplicada;
		
	}




	public RecetaMultiplicadaVista toRecetaMultiplicadaVista(Recetas m) {
		// TODO Auto-generated method stub
		RecetaMultiplicadaVista nueva=new RecetaMultiplicadaVista();
		nueva.setAprobada(m.getAprobada());
		nueva.setCantidadPersonas(m.getCantidadPersonas());
		nueva.setDescripcion(m.getDescripcion());
		nueva.setFotounica(m.getFotounica());
		nueva.setIdTipo(m.getIdTipo().getIdTipo());
		nueva.setNombre(m.getNombre());
		

		//pasos to view???
		List<Pasos> pasos=m.getPasos();
		List<PasosVista> pasosvista =new ArrayList<PasosVista>();
		for(Pasos p:pasos) {
			//el paso tiene multimedia
			pasosvista.add(p.toView(p));
		}
		
		nueva.setPasos(pasosvista);
		
		
		nueva.setPorciones(m.getPorciones());
		
		

		//utilizados to view??
		//tiene utilizados
		List<Utilizado> utilizados=m.getUtilizados();
		List<UtilizadoVista> utilizadosvista=new ArrayList<>();
		for(Utilizado u:utilizados) {
			utilizadosvista.add(u.toView(u));
		}
		
		nueva.setUtilizados(utilizadosvista);
		
		
		return nueva;
	}



	
	
	
	
	
}

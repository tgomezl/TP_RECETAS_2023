package com.example.demo.entidades;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;


import com.example.demo.vistas.ListaRecetasVista;
import com.example.demo.vistas.RecetasVista;
import com.example.demo.vistas.UserConReceta;
import com.example.demo.vistas.UsuarioVista;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuarios implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUsuario")
	private Integer idUsuario;
	//el id es identity pero en sql. no se como es aca
	//ACA TAMBIEN VA IDENTITY!!
	
	
	/*
	private ArrayList<Recetas> recetas=new ArrayList<>();
	
	private ArrayList<Calificaciones> calificaciones=new ArrayList<>();
	*/
	private String mail;
	
	private String nickname;
	
	private String habilitado; //si o no 
	private String nombre;
	private String avatar; //url de la imagen del avatar.
	private String tipo_usuario; //usuario o visitante
	
	private String contrasenia;
	
	
	private Integer claveDeRecu;
	
	
	private Boolean esadmin=false;
	
	//@OneToMany(mappedBy="usuario",fetch = FetchType.EAGER)
	@OneToMany(mappedBy="usuario",fetch = FetchType.EAGER, cascade=CascadeType.PERSIST)
	@JsonManagedReference
	private List<Recetas> recetas=new ArrayList<>();
	//SON LAS RECETAS CREADAS POR EL USER!!!
	
	
//OJO.estas son sus recetas seguidas, pero no creadas por el
	//one---to----one
	//es unider
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="recetasAintentar")
	private ListaRecetas recetasAintentar=new ListaRecetas();
	//adentro tiene recetas
	
	/*-----------------------------------------------------------------------*/
	public Usuarios() {
		recetas=new ArrayList<>();
		
	}
	
	
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHabilitado() {
		return habilitado;
	}
	public void setHabilitado(String habilitado) {
		this.habilitado = habilitado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getTipo_usuario() {
		return tipo_usuario;
	}
	public void setTipo_usuario(String tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}


	public List<Recetas> getRecetas() {
		return recetas;
	}



	
	public String getContrasenia() {
		return contrasenia;
	}


	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	
	

	/*------------------*/

	

	public void setRecetas(List<Recetas> recetas) {
		//son las recetas creadas por el
		this.recetas = recetas;
	}




//metodo para agregar recetas
	public void addReceta(Recetas receta) {
		// TODO Auto-generated method stub
		this.recetas.add(receta);
		receta.setUsuario(this);
	}

	public void quitarReceta(Recetas receta) {
		this.recetas.remove(receta);
		receta.setUsuario(null);
		
	}


	public Integer getClaveDeRecu() {
		return claveDeRecu;
	}


	public void setClaveDeRecu(Integer claveDeRecu) {
		this.claveDeRecu = claveDeRecu;
	}


	public Boolean getEsadmin() {
		return esadmin;
	}


	public void setEsadmin(Boolean esadmin) {
		this.esadmin = esadmin;
	}
	

	public ListaRecetas getRecetasAintentar() {
		return recetasAintentar;
	}


	public void setRecetasAintentar(ListaRecetas recetasAintentar) {
		this.recetasAintentar = recetasAintentar;
	}


	public void borrarDatosingreso() {
		//solo aceptara mail y alias del user

		

		
		habilitado="no"; //si o no 
		nombre=null;
		avatar=null; //url de la imagen del avatar.
		tipo_usuario=null; //usuario o visitante
		
		contrasenia=null;
		
		
		claveDeRecu=null;
		
		
		esadmin=false;
		
		recetas=new ArrayList<>();
		//SON LAS RECETAS CREDAS POR EL USER!!!
		
		
	//OJO.estas son sus recetas seguidas, pero no creadas por el
		recetasAintentar=null;
		
		
	}
	public UserConReceta crearUserConReceta(Usuarios user) {
		
		UserConReceta uservista= new UserConReceta();
		
		uservista.setIdUsuario(user.getIdUsuario());
		uservista.setAvatar(user.getAvatar());
		uservista.setHabilitado(user.getHabilitado());
		uservista.setMail(user.getMail());
		uservista.setNickname(user.getNickname());
		uservista.setNombre(user.getNombre());
		//convierte sus recetas en recetas vista
		List<Recetas> recetas=user.getRecetas();
		List<RecetasVista> recetasvista=new ArrayList<>();
		for(Recetas r:recetas) {
			recetasvista.add(r.toView(r));
		}
		uservista.setRecetas(recetasvista ); 
		uservista.setTipo_usuario(user.getTipo_usuario());
		
		return uservista;
		
	}
	
	public UsuarioVista toView(Usuarios user) {
		System.out.println(" dentro de usuario to view");
		System.out.println("falta implementar");
		
		
		UsuarioVista vista= new UsuarioVista();
		vista.setAvatar(user.getAvatar());
		vista.setIdUsuario(user.getIdUsuario());
		vista.setMail(user.getMail());
		vista.setNickname(user.getNickname());
		vista.setNombre(user.getNombre());
		vista.setTipo_usuario(user.getTipo_usuario());
		//lsa recetas creadas por el
		List<RecetasVista> recetasvista=new ArrayList<>();
		
		List<Recetas> recetas=user.getRecetas();
		for(Recetas r:recetas) {
			recetasvista.add(r.toView(r));
		}
		
		vista.setRecetas(recetasvista);
		
		//sus recetas a intentar
		ListaRecetas listasrecetasreal=user.getRecetasAintentar();
	//las paso a vista
		ListaRecetasVista listasrecetasvista=new ListaRecetasVista();
		listasrecetasvista.setId(listasrecetasreal.getId());
		listasrecetasvista.setNombrelista(listasrecetasreal.getNombrelista());
		
		//las recetas reales
		List<Recetas> recetasposta=listasrecetasreal.getRecetas();
		for(Recetas r:recetasposta) {
			listasrecetasvista.ADDRECETAVISTA(r.toView(r));
		}
//ACA		
		//setRecetasAintentar(ListaRecetasVista recetasAintentar)
		vista.setRecetasAintentar(listasrecetasvista);
		/*
		for(ListaRecetas lr:listasrecetas) {
			listasrecetasvista.add(lr.toView(lr));
		}
		*/
		
		//vista.setListas(listasrecetasvista);
		//SON LAS RECETAS CREADAS POR EL USER!!!
		

		return vista;
	}
	
	
	

}

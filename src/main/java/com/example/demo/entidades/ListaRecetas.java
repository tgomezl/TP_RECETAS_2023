package com.example.demo.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.vistas.ListaRecetasVista;
import com.example.demo.vistas.RecetasVista;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
	
	private String nombrelista="recetas a intentar";
	
	//OneToOne   ESTA DEL OTRO LADO
	/*usuario-1------------n--listarecetas*/
	
	/*
	@OneToOne(mappedBy = "recetasAintentar")
	private Usuarios idUsuario=null;
	*/
	
	/*listarecetas-N-----------------N-recetas
	 * es unider*/
	@ManyToMany
	@JoinTable(name="listaRecetas_receta",
	//@JoinTable(name="idLista_idReceta",
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
/*
	public Usuarios getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Usuarios idUsuario) {
		this.idUsuario = idUsuario;
	}
*/
	public List<Recetas> getRecetas() {
		return recetas;
	}

	public void setRecetas(List<Recetas> recetas) {
		this.recetas = recetas;
	}

	public ListaRecetasVista toView(ListaRecetas lr) {
		// TODO Auto-generated method stub
		
		System.out.println("  dentro del toview de listade recetas");
		
		ListaRecetasVista lrvista= new ListaRecetasVista();
		lrvista.setId(lr.getId());
		//lrvista.setIdUsuario(lr.getIdUsuario().getIdUsuario());
		lrvista.setNombrelista(lr.getNombrelista());
		//tengo que convertir a view las recetas
		List<Recetas>recetas=lr.getRecetas();
		List<RecetasVista> vistas=new  ArrayList<RecetasVista>();
		for(Recetas r:recetas) {
			vistas.add(r.toView(r));
		}
		lrvista.setRecetas(vistas);
		
		return lrvista;
	}
	
	public void ADDrecetaAintentar(Recetas r) {
		this.recetas.add(r);
		
	}
	
	public void REMOVErecetaAintentar(Recetas r) {
		this.recetas.remove(r);
		
	}
	
	
	

}

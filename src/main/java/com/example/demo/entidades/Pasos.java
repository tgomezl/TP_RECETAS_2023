package com.example.demo.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.vistas.MultimediaVista;
import com.example.demo.vistas.PasosVista;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
//ESTA OK
@Entity
@Table(name="paso")
public class Pasos implements Serializable{
	
	/**paso--1---------N--multimedia
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPaso;
	
	/*paso--N---------1--receta es bider*/
	@ManyToOne
	@JoinColumn(name="idreceta")
	@JsonBackReference
	private Recetas idreceta;
	
	private Integer nroPaso;
	
	private String texto;
	
	@OneToMany(mappedBy="idPaso",cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
	private List<Multimedia> multimedia=new ArrayList<>();
	
	public Pasos() {
		
	}

	public Integer getIdPaso() {
		return idPaso;
	}

	public void setIdPaso(Integer idPaso) {
		this.idPaso = idPaso;
	}

	public Recetas getIdreceta() {
		return idreceta;
	}

	public void setIdreceta(Recetas idreceta) {
		this.idreceta = idreceta;
	}

	public Integer getNroPaso() {
		return nroPaso;
	}

	public void setNroPaso(Integer nroPaso) {
		this.nroPaso = nroPaso;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public List<Multimedia> getMultimedia() {
		return multimedia;
	}

	public void setMultimedia(List<Multimedia> multimedia) {
		this.multimedia = multimedia;
	}

	public void ADDmultimedia(Multimedia multimedia2) {
		// TODO Auto-generated method stub
		this.multimedia.add(multimedia2);
		multimedia2.setIdPaso(this);
	}

	public PasosVista toView(Pasos p) {
		
		System.out.println("dentro de pasos toview");
		// TODO Auto-generated method stub
		PasosVista nuevo =new PasosVista();
		nuevo.setIdPaso(p.getIdPaso());
		nuevo.setIdreceta(p.getIdreceta().getIdReceta());
		nuevo.setNroPaso(p.getNroPaso());
		List<Multimedia> multiemdias=p.getMultimedia();
		List<MultimediaVista> multiemdiavista=new ArrayList<>();
		//convierte el multiemdia a multimediavista
		for(Multimedia m:multiemdias) {
			multiemdiavista.add(m.toView(m));
		}
		nuevo.setTexto(p.getTexto());
		nuevo.setMultimedia(multiemdiavista);
		System.out.println(" fin del toview");
		return nuevo;
	}
	
	
	

}

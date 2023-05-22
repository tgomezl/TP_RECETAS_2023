package com.example.demo.entidades;

import java.io.Serializable;
import java.util.ArrayList;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private Recetas idreceta;
	
	private Integer nroPaso;
	
	private String texto;
	
	@OneToMany(mappedBy="idPaso")
	private ArrayList<Multimedia> multimedia=new ArrayList<>();
	
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

	public ArrayList<Multimedia> getMultimedia() {
		return multimedia;
	}

	public void setMultimedia(ArrayList<Multimedia> multimedia) {
		this.multimedia = multimedia;
	}
	
	
	

}

package com.example.demo.comparadores;

import java.util.Comparator;

import com.example.demo.entidades.Recetas;

public class CompararRecetasPorFecha implements Comparator<Recetas>{

	@Override
	public int compare(Recetas esta, Recetas otra) {
		// las mas nuevas primero
		return otra.getFechaCreacion().compareTo(esta.getFechaCreacion());
	}
	

}

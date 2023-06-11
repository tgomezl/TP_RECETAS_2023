package com.example.demo.comparadores;

import java.util.Comparator;

import com.example.demo.entidades.Recetas;

public class CompararRecetasPorNombreUsuario implements Comparator<Recetas>{

	@Override
	public int compare(Recetas esta, Recetas otra) {
		// de la A a la Z
		return esta.getUsuario().getNombre().compareTo(otra.getUsuario().getNombre());
	}

}

package com.example.demo.comparadores;

import java.util.Comparator;

import com.example.demo.entidades.Recetas;

public class CompararRecetasPorNombre  implements Comparator<Recetas>{

	@Override
	public int compare(Recetas esta, Recetas otra) {
		// de la A a la Z
		return esta.getNombre().compareTo(otra.getNombre());

		
	}

}

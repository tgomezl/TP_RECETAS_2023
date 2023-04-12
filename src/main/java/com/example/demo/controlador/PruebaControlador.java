package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.example.demo.entidades.Pruebas;
import com.example.demo.service.PruebaService;



@Component
public class PruebaControlador {
	//autowired al service
	//-------------------------------------------------------------------------------------------------
	@Autowired
	private PruebaService pruebaservice;

	public Pruebas CrearPruebas(Pruebas p) {
	//crear
		
		//si pasa las VALIDACIONES(		solo se pueden guardar mayores a 18)
		Pruebas creado=null;
		if(!p.getApellido().isBlank()) {
			//lo mando a guardar
			creado =pruebaservice.save(p);
				
		}
		return creado;
			
	}

	public List<Pruebas> TraerListaPruebas() {
		//te devuelve la lista
		 List<Pruebas> listaActores = pruebaservice.findAll();
	     return listaActores;
	}

}


	

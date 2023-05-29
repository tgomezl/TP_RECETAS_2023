package com.example.demo.controlador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entidades.Pasos;
import com.example.demo.entidades.Recetas;
import com.example.demo.service.PasoService;

@Component
public class PasoControlador {
	
	@Autowired
	private PasoService pasoservice;
	
	@Autowired
	private RecetasControlador recetacontrolador;

	public boolean agregarPaso(int idreceta, Pasos paso) {
		// TODO Auto-generated method stub
		
		boolean agregado=false;
		Recetas buscada=recetacontrolador.busacarUnaReceta(idreceta);
		if(buscada!=null) {
			buscada.ADDpasos(paso);
			//save de ambas???
		
			//aca iria el save de la receta
			recetacontrolador.pisarReceta(buscada);
			//save del paso
			pasoservice.save(paso);
			agregado=true;
		}
		return agregado;
	}

	public boolean borrarPaso(int idreceta, int idpaso) {
		// borra un paso de una receta existe
		//habria que validar que tenga permiso de borrarlo
		boolean borrado=false;
		//busca la receta
		Recetas recetabuscada=recetacontrolador.busacarUnaReceta(idreceta);
		if(recetabuscada!=null) {
			//busco el paso con el id
			Pasos pasobuscado=buscarPAso(idpaso);
			if(pasobuscado!=null) {
				//lo encontro
				
				recetabuscada.QUITARpasos(pasobuscado);
				//actualizo la receta
				recetacontrolador.pisarReceta(recetabuscada);
				//elimino el paso
				pasoservice.deleteById(idpaso);
				borrado=true;
			}
	
		}
		
		return borrado;
		
	}
	
	public Pasos buscarPAso(Integer idpaso) {
		Pasos buscado=null;
		Optional<Pasos> paso =pasoservice.findById(idpaso);
		if(paso.isPresent()) {
			buscado=paso.get();
		}
		return buscado;
	}
	
	
	
	
	

}

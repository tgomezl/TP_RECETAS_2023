package com.example.demo.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entidades.Recetas;
import com.example.demo.entidades.Usuarios;
import com.example.demo.service.RecetasService;
import com.example.demo.service.UsuarioService;

//toda la logica de las recetas
@Component
public class RecetasControlador {
	
	@Autowired
	private RecetasService recetasservice;
	
	/*
	@Autowired
	private UsuarioService userservice;
	*/
	
	@Autowired
	private UsuarioControlador usuariocontrolador;

	public Recetas crearReceta(Recetas receta) {
		// si pasa las validaciones TODO
		//todo
		if(!receta.getNombre().isEmpty()) {
			//paso las validaciones
			return recetasservice.save(receta);
		}
		return null;
		
	}

	public List<Recetas> traerRecetas() {
		// devuelve todas las recetas de la bbbdd
		return recetasservice.findAll();
	}

	public List<Recetas> traerRecetasdeunuser(int id) {
		// buscar todas las recetas de un user
		//se fija si el user existe
		Usuarios user=usuariocontrolador.BuscarUser(id);
		if(user!=null) {
			List<Recetas> lista= user.getRecetas();
			return lista;
		}
		return null;
		
	}

	public Recetas busacarUnaReceta(int id) {
		// TODO Auto-generated method stub
		Recetas encontrada=null;
		Optional<Recetas> buscada= recetasservice.findById(id);
		if(buscada.isPresent()) {
			encontrada=buscada.get();
		}
		return encontrada;
	}
	
	

	
	
	
	
	
	

}

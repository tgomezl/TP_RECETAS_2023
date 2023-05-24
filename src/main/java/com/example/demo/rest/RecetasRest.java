package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controlador.RecetasControlador;
import com.example.demo.entidades.Recetas;

@RestController
@RequestMapping("/recetas")
@CrossOrigin(origins = "*")
public class RecetasRest {
	
	
	@Autowired
	private RecetasControlador recetacontrolador;
	
	//CREAR una receta!!!!!!!!!!
	@PostMapping("")
	public ResponseEntity<?> crearReceta(@RequestBody Recetas receta){
		
		
		Recetas creada=recetacontrolador.crearReceta(receta);
		if(creada!=null) {
			
			return ResponseEntity.ok(creada);
		}
	
		else {
			return ResponseEntity.notFound().build();	
		
		}
	}
	
	//
	
	
	//traer TODAS las recetas de la bbdd
	@GetMapping
	public ResponseEntity<?> traerAllrecetas(){
		List<Recetas> lista= recetacontrolador.traerRecetas();
		return ResponseEntity.ok(lista);
	}
	
	
	//traer todas las recetas CREADAS por un usuario
	@GetMapping("/user/{iduser}")
	public ResponseEntity<?> traerrecetasDeUnUser(@PathVariable(value="iduser") String id){
		
		List<Recetas> lista= recetacontrolador.traerRecetasdeunuser(Integer.parseInt(id));
		if(lista==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
	
	
	
	//traer todas las recetas de UNA LISTA determinada de un user determinado
	//TO DO
	
	
	
	
	//buscar una receta por id
	@GetMapping("/{idreceta}")
	public ResponseEntity<?> traerRecetaPorId(@PathVariable(value="idreceta") String id){
		
		Recetas buscada= recetacontrolador.busacarUnaReceta(Integer.parseInt(id));
		if(buscada!=null) {
			return ResponseEntity.ok(buscada);
		}
		return ResponseEntity.notFound().build();
	}
	
	
	
	
	//eleiminar una receta por id solo si sos e creador
	
	
	
	//editar una receta solo si sos el creador
	
	
	
	
	
	
	
	//FILTRAR RECETAS POR ALGUN CAMPO!!!!!!!!!

}
	


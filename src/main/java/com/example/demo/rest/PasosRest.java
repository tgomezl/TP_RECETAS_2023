package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controlador.PasoControlador;
import com.example.demo.entidades.Pasos;
import com.example.demo.entidades.Recetas;

@RestController
@RequestMapping("/pasos")
@CrossOrigin(origins = "*")
public class PasosRest {
	
	
	@Autowired
	private PasoControlador pasocontrolador;
	
	//agregarle un paso a una receta existente
	@PostMapping("/{idreceta}")
	public ResponseEntity<?> agregarPaso(@PathVariable(value="idreceta") String idreceta , @RequestBody Pasos paso){
		//crea y agrega un paso a una receta existente
		
		boolean agregado=pasocontrolador.agregarPaso(Integer.parseInt(idreceta), paso);
		if(agregado) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
		
	}
	
	
	//eliminar un paso de receta existente
	//recibe el id del paso, se podria modificar para que reciba el nro del paso
	@DeleteMapping("/{idreceta}/{idpaso}")
	public ResponseEntity<?> agregarPaso(@PathVariable(value="idreceta") String idreceta ,@PathVariable(value="idpaso") String idpaso){
		//crea y agrega un paso a una receta existente
		
		boolean borrado=pasocontrolador.borrarPaso(Integer.parseInt(idreceta), Integer.parseInt(idpaso));
		if(borrado) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
		
	}
	
	
	
	//modificar un paso de receta existente
	
	
	//acceder a un paso con su id paso

}

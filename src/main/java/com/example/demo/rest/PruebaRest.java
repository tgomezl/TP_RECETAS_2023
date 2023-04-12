package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.controlador.PruebaControlador;

import com.example.demo.entidades.Pruebas;


@RestController
@RequestMapping("/pruebas")
@CrossOrigin(origins = "*")
public class PruebaRest {
	
	
	//son los endpoint
	@Autowired
	private PruebaControlador pruebacontrolador;
	
	
	//crear
		@PostMapping
	    public ResponseEntity<?> crearPrueba(@RequestBody Pruebas prueba){
			//le tengo que pasar un body {"nombre":"", "apellido":"","edad":22};
			Pruebas creado=pruebacontrolador.CrearPruebas(prueba);
			if(creado!=null) {
				return ResponseEntity.ok(creado);
			}
			else {
				return ResponseEntity.notFound().build();
			}
	        
	    }
	
	
	//    responde en http://localhost:8080/pruebas/saludo
	@GetMapping("/saludo")
	public String hola() {
		return "hola mundo";
	}
	
	//get para los datos dentro de la tabla pruebas
	// traer todos
		@GetMapping("/traerlista")
		public ResponseEntity<?> buscarTODASLASPRUEBAS() {
			// llamo al metodo del controlador.(busca usando Integer)
			List<Pruebas> lista = pruebacontrolador.TraerListaPruebas();
			//de ultima te devuelve una lista vacia
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println(" lista: "+ lista);
			System.out.println("");
			System.out.println("");
			
			return ResponseEntity.ok(lista);

		}
		
}

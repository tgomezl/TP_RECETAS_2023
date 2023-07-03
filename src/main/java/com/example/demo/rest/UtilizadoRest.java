package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controlador.GenericoControlador;
import com.example.demo.entidades.Recetas;
import com.example.demo.vistas.UtilizadoconingredienteexistenteDTO;

@RestController
@RequestMapping("/utilizados")
@CrossOrigin(origins = "*")
public class UtilizadoRest {
	
	@Autowired
	private GenericoControlador genericocontrolador;
	
	//agregar utilizado a una receta existente:
			//CON INGREDIENTE EXISTENTE recibe el idingrdiente
			//si no existe el ingrediente debe recibir un -1 como idingrdiente
	
	@PostMapping("/agregarutilizado")
	public ResponseEntity<?> agregarutilizadoarecetaexistente(@RequestBody 
			UtilizadoconingredienteexistenteDTO entidad){
			boolean creado=genericocontrolador.agregarutilizadoarecetaexistente(entidad);
			if(creado) {
				return ResponseEntity.ok("receta modificada");
			}
				return ResponseEntity.notFound().build();
		
		
	}
	
	//http://localhost:8080/utilizados/agregarutilizado
	@PostMapping("/agregarlistadeingredientesusados")
	public ResponseEntity<?> agregarlistadeingredientesusados(@RequestBody 
			List<UtilizadoconingredienteexistenteDTO> utilizados){
			boolean creado=genericocontrolador.agregarlistadeutilizadosexistentes(utilizados);
			if(creado) {
				return ResponseEntity.ok("receta modificada");
			}
				return ResponseEntity.notFound().build();
		
		
	}

}

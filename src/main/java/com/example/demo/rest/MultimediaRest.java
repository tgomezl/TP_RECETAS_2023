package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controlador.MultimediaControlador;
import com.example.demo.entidades.Multimedia;


@RestController
@RequestMapping("/multimedia")
@CrossOrigin(origins = "*")
public class MultimediaRest {

	// son los endpoint
	@Autowired
	private MultimediaControlador multimediacontrolador;

	// crear
	@PostMapping
	public ResponseEntity<?> crearMultimedia(@RequestBody Multimedia multimedia) {
		// le tengo que pasar un body {"nombre":"", "apellido":"","edad":22};
		Multimedia creado = multimediacontrolador.CrearMultimedia(multimedia);
		if (creado != null) {
			return ResponseEntity.ok(creado);
		} else {
			return ResponseEntity.notFound().build();
		}

	}
	
	//

}

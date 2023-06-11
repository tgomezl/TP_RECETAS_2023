package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.controlador.MultimediaControlador;
import com.example.demo.entidades.Multimedia;


@RestController
@RequestMapping("/multimedia")
@CrossOrigin(origins = "*")
public class MultimediaRest {

	// son los endpoint
	@Autowired
	private MultimediaControlador multimediacontrolador;

	// 	//lo crea pero no se lo agrega a ninguna paso
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
	
	//agregar un multimedia MOCK a un paso existente  y receta exisxtente
	@PostMapping("/add/{idreceta}/{nropaso}")
	public ResponseEntity<?> agregarMultimedia(@PathVariable(value="idreceta") String idreceta,
			@PathVariable(value="nropaso") String nropaso, 
			@RequestBody Multimedia multimedia) {
			boolean agregado=multimediacontrolador.agregarMultimedia(Integer.parseInt(idreceta),
					Integer.parseInt(nropaso), multimedia);
			if(agregado) {
				return ResponseEntity.ok(null);
			}
			return ResponseEntity.notFound().build();
			
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getall(){
		List<Multimedia> lista=multimediacontrolador.getallmultimedia();
		return ResponseEntity.ok(lista);
	}
	
	
	// agregar un multimedia REAL a un paso existente y receta exisxtente
	@PostMapping("/addREAL/{idreceta}/{nropaso}")
	public ResponseEntity<?> agregarMultimediaREAL(@PathVariable(value = "idreceta") String idreceta,
			@PathVariable(value = "nropaso") String nropaso, @RequestBody MultipartFile file) {
		
		try {
			boolean agregado = multimediacontrolador.agregarMultimediaREAL(Integer.parseInt(idreceta),
					Integer.parseInt(nropaso), file);
			if (agregado) {
				return ResponseEntity.ok(null);
			}
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarImagen(@PathVariable String id){
		boolean borrado=multimediacontrolador.deleteImagen(Integer.parseInt(id));
		if(borrado) {
			return ResponseEntity.ok().build();
		}
        return ResponseEntity.notFound().build();
        
    }
	
	
	public ResponseEntity<?> getIMagenREALporRuta (@PathVariable String ruta){
		byte[] bytes = multimediacontrolador.getIMagenREALporRuta(ruta);
		return ResponseEntity.ok().build();
	}

}

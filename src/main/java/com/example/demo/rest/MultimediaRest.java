package com.example.demo.rest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
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
import com.example.demo.vistas.MultimediaVista;


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
		//jackson problem cuando el multimedia tiene pasos!!!
		List<MultimediaVista> lista=multimediacontrolador.getallmultimedia();
		return ResponseEntity.ok(lista);
	}
	
	
	// agregar un multimedia REAL a un paso existente y receta exisxtente
	@PostMapping("/addREAL/{idreceta}/{nropaso}")
	public ResponseEntity<?> agregarMultimediaREAL(@PathVariable(value = "idreceta") String idreceta,
			@PathVariable(value = "nropaso") String nropaso, @RequestBody MultipartFile file) {
		System.out.println("cargar multimedia real");
		System.out.println("idreceta "+idreceta + " nropaso "+nropaso);
		try {
			System.out.println("  agrgando multimedia"); 
			String url = multimediacontrolador.agregarMultimediaREAL(Integer.parseInt(idreceta),Integer.parseInt(nropaso), file);
			if (!url.isEmpty()) {
				System.out.println("no esta vacia");
				return ResponseEntity.ok(url);
			}
			
			System.out.println("url vacia");
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			System.out.println("    CATCH");
			return ResponseEntity.notFound().build();
		}

	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarImagen(@PathVariable String id){
		boolean borrado=multimediacontrolador.deleteImagen(Integer.parseInt(id));
		//NO ESTA HECHO!!!
		if(borrado) {
			return ResponseEntity.ok().build();
		}
        return ResponseEntity.notFound().build();
        
    }
	
	//GET DE UN MULTIMEDIA REAL
	@GetMapping("/getreal/{ruta}")
	public ResponseEntity<?> getIMagenREALporRuta (@PathVariable String ruta){
		//me da la ruta total
		String rutatotal=multimediacontrolador.getRuta(ruta);
		
		try {
			Resource resource= multimediacontrolador.getIMagenREALporRuta(rutatotal);
			if(resource!=null) {
				Path path = Paths.get(rutatotal);
				
				//headers
				//org.springframework.http.HttpHeaders header=new org.springframework.http.HttpHeaders();
				
				return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(path))).body(resource);
			}
			
			return ResponseEntity.notFound().build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		
	}

	
	
}

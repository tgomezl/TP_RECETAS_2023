package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fotos")
@CrossOrigin(origins = "*")
public class FotoRest {
	
	//NO ESTA HECHO PERO ES CASI IGUAL AL ENDPOINT D  MULTIMEDIA
/*
	@Autowired
	private FotoService fotoservice;
	
	//agregar foto a una receta existente
	// agregar un multimedia REAL a un paso existente y receta exisxtente
		@PostMapping("/addREAL/{idreceta}")
		public ResponseEntity<?> agregarfotoREAL(@PathVariable(value = "idreceta") String idreceta,
				@RequestBody MultipartFile file) {
			System.out.println("cargar foto real");
			System.out.println("idreceta "+idreceta );
			try {
				System.out.println("  agrgando foto"); 
				String url = fotoservice.agregarFotoREAL(Integer.parseInt(idreceta),Integer.parseInt(nropaso), file);
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
		*/
}

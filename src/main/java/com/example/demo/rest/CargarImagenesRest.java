package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.UploadFileService;

@RestController
@RequestMapping("/cargarimagenes")
@CrossOrigin(origins = "*")
public class CargarImagenesRest {
	
	@Autowired
    private UploadFileService uploadFileService;
	
	@PostMapping("/cargar")
	public ResponseEntity<?> crearImagen(@RequestBody MultipartFile file) {
		System.out.println(" ");
		System.out.println(" imagen recibida");
		System.out.println(" ");
		System.out.println(file.getName());
		if (!file.isEmpty()) {
			try {
				//aca hace el save
				String url = uploadFileService.saveFile(file);
				/*
				if (!ImagenController.getInstancia().crearImagen(url, file.getContentType(), idReclamo))
					return ResponseEntity.notFound().build();
					
				*/
				return ResponseEntity.status(HttpStatus.CREATED).build();
			} catch (Exception e) {
				return ResponseEntity.notFound().build();
			}

		} else
			return ResponseEntity.notFound().build();

	}

	

}

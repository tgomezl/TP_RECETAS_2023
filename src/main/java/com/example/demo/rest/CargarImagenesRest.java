package com.example.demo.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.http.HttpHeaders;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.UploadFileService;
//ENDPOINT PARA PRUEBAS!!
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
		//System.out.println(file.getName());
		try {
			if (!file.isEmpty()) {

				//aca hace el save
				String url = uploadFileService.saveFile(file);
				/*
				if (!ImagenController.getInstancia().crearImagen(url, file.getContentType(), idReclamo))
					return ResponseEntity.notFound().build();
					
				*/
				return ResponseEntity.ok(url);
			}
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		

	}

	@GetMapping("/{ruta}")
	public ResponseEntity<?> getmultimedia(@PathVariable(value="ruta") String ruta ){
		
		System.out.println(" ");
		System.out.println(" ruta recibida");
		System.out.println(" ");
		String upload_folder = ".//src//main//resources//frontend//administracion//src//imagenes//";
		String rutatotal=upload_folder+ruta;
		
		try {
			Resource resource= uploadFileService.getFile(rutatotal);
			if(resource!=null) {
				Path path = Paths.get(rutatotal);
				
				//headers
				//org.springframework.http.HttpHeaders header=new org.springframework.http.HttpHeaders();
				
				return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(path))).body(resource);
			}
			
			
			return ResponseEntity.notFound().build();
			//return ResponseEntity.ok(resource);
					
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		
	}
	
	//otr metodo
	@PostMapping("/cargarusandostream")
	public String guardararchivo(MultipartFile file) throws IOException {
		System.out.println("cargando usando stream");
		String upload_folder = ".//src//main//resources//frontend//administracion//src//imagenes//";
		File path = new File(upload_folder + file.getOriginalFilename());
		path.createNewFile();
		FileOutputStream output = new FileOutputStream(path);
		output.write(file.getBytes());
		output.close();
		return upload_folder;
	}

}

package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;




@Service
public class UploadFileService {


    private String upload_folder = ".//src//main//resources//frontend//administracion//src//imagenes//";

    public String saveFile(MultipartFile file) throws IOException{
    	System.out.println("guardando");
    	
        byte[] bytes = file.getBytes();
        System.out.println("content tipe" +file.getContentType());
        System.out.println("original file name: "+ file.getOriginalFilename() );
        
        Path path = Paths.get(upload_folder +  file.getOriginalFilename());
        Files.write(path,bytes);

        return   file.getOriginalFilename();
    }
    
    //necesito un metodo para acceder a las imagenes
    
    public Resource getFile(String rutatotal)throws IOException{
    	System.out.println("la ruta es : ");
    	System.out.println(                     rutatotal);
    	Path path = Paths.get(rutatotal);
    	Resource resource=null;
    	
    //aca habria que chequear si el path existe 
    	if(Files.exists(path)) {
    		resource=new UrlResource(path.toUri());
    	}else {
    		System.out.println("********************************************");
        	System.out.println("el archivo "+rutatotal + " no existe");
    	}
    	
    	/*
    	Path path = Paths.get(upload_folder +  ruta);
    	byte[] bytes=Files.readAllBytes(path);
    	*/
    	return resource;
    }
    
    public String getRutaalfolder() {
    	return upload_folder;
    }
}

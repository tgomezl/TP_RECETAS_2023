package com.example.demo.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
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
    	System.out.println("       guardando en upload");
    	
        //byte[] bytes = file.getBytes();
        byte [] byteArr=file.getBytes();
     //ACA ESTA EL ERROR!!!!
        InputStream inputStream = new ByteArrayInputStream(byteArr);
        //inputstrem??
        System.out.println("content tipe" +file.getContentType());
        System.out.println("original file name: "+ file.getOriginalFilename() );
        
        Path path = Paths.get(upload_folder +  file.getOriginalFilename());
        Files.write(path,byteArr);
        System.out.println(" fin de guardado");
        
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
    /*
    public String guardararchivo(MultipartFile file) throws IOException {
		File path = new File(upload_folder + file.getOriginalFilename());
		path.createNewFile();
		FileOutputStream output = new FileOutputStream(path);
		output.write(file.getBytes());
		output.close();
		return upload_folder;
	}
	*/

	public String saveFOTO(MultipartFile file)throws IOException{
		System.out.println("  agregandfo foto a una receta");
		byte[] byteArr = file.getBytes();
		// ACA ESTA EL ERROR!!!!
		InputStream inputStream = new ByteArrayInputStream(byteArr);
		// inputstrem??
		System.out.println("content tipe" + file.getContentType());
		System.out.println("original file name: " + file.getOriginalFilename());
		String folderfotos=".//src//main//resources//frontend//administracion//src//fotos//";
		Path path = Paths.get(folderfotos + file.getOriginalFilename());
		Files.write(path, byteArr);
		System.out.println(" fin de guardado");

		return file.getOriginalFilename();

	}

	public Resource getFOTO(String rutaparcial) throws IOException{
		// TODO Auto-generated method stub
		System.out.println(" accediendo a la foto");
		String folderfotos=".//src//main//resources//frontend//administracion//src//fotos//";
		System.out.println("la ruta es : "+folderfotos+rutaparcial);
  
    	
    	Path path = Paths.get(folderfotos+rutaparcial);
    	Resource resource=null;
    	
    //aca habria que chequear si el path existe 
    	if(Files.exists(path)) {
    		resource=new UrlResource(path.toUri());
    	}else {
    		System.out.println("********************************************");
        	System.out.println("el archivo "+folderfotos+rutaparcial + " no existe");
    	}
    	
    	/*
    	Path path = Paths.get(upload_folder +  ruta);
    	byte[] bytes=Files.readAllBytes(path);
    	*/
    	return resource;
	}
 
}
	

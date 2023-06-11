package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;




@Service
public class UploadFileService {


    private String upload_folder = ".//src//main//resources//frontend//administracion//src//imagenes//";

    public String saveFile(MultipartFile file) throws IOException{

        byte[] bytes = file.getBytes();
        Path path = Paths.get(upload_folder +  file.getOriginalFilename());
        Files.write(path,bytes);

        return   file.getOriginalFilename();
    }
}

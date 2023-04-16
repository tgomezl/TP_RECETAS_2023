package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entidades.Multimedia;



public interface MultimediaRepo extends JpaRepository<Multimedia, Integer>{

}

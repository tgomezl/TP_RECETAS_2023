package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entidades.Foto;

public interface FotoRepo extends JpaRepository<Foto,Integer>{

}

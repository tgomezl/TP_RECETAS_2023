package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entidades.Recetas;



public interface RecetasRepo extends JpaRepository<Recetas, Integer>{

}

package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entidades.Recetas;



public interface RecetasRepo extends JpaRepository<Recetas, Integer>{

	List<Recetas> findByAprobada(Boolean aprobada);
}

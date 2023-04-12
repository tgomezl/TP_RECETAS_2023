package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entidades.Pruebas;

public interface PruebaRepo extends JpaRepository<Pruebas, Integer>{
	//no tiene metodos

}

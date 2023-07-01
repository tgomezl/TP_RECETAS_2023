package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entidades.Conversiones;
import com.example.demo.entidades.Ingrediente;

public interface ConversionesRepo extends JpaRepository<Conversiones,Integer>{

}

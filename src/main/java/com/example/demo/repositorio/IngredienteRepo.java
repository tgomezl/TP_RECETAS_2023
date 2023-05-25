package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entidades.Ingrediente;

public interface IngredienteRepo extends JpaRepository<Ingrediente, Integer> {

}

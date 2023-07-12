package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entidades.Ingrediente;

public interface IngredienteRepo extends JpaRepository<Ingrediente, Integer> {

	List<Ingrediente> findByNombre(String nombre);
}

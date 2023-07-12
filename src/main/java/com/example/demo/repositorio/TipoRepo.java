package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entidades.Tipo;

public interface TipoRepo extends JpaRepository<Tipo, Integer>{

	List<Tipo> findByDescripcion(String descripcion);
}

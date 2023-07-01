package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entidades.Utilizado;

public interface UtilizadoRepo extends JpaRepository<Utilizado, Integer> {

}

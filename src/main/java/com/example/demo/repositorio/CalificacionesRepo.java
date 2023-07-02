package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entidades.Calificaciones;
import com.example.demo.entidades.Conversiones;

public interface CalificacionesRepo extends JpaRepository<Calificaciones, Integer>{

}

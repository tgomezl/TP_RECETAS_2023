package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entidades.Usuarios;

public interface UsuarioRepo extends JpaRepository<Usuarios, Integer>{

}

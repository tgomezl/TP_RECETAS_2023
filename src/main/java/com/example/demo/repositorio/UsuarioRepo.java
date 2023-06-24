package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entidades.Usuarios;

public interface UsuarioRepo extends JpaRepository<Usuarios, Integer>{
	
	List<Usuarios> findByMail(String mail);
	
	List<Usuarios> findByNickname(String nickname);
	

}

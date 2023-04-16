package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entidades.Multimedia;
import com.example.demo.entidades.Usuarios;

public interface UsuarioServiceInt {
	
	// limita los metodos

	public Iterable<Usuarios> findAll();

	public Optional<Usuarios> findById(Integer id);

	public Usuarios save(Usuarios user);

	public void deleteById(Integer id);

}

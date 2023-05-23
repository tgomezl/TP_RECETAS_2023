package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entidades.Multimedia;

//NO ESTA EN USO
public interface MultimediaServiceInt {

	//limita los metodos
	
	public Iterable<Multimedia> findAll();
			
	public Optional<Multimedia> findById(Integer id);
			
	public Multimedia save(Multimedia multimedia);
			
	public void deleteById(Integer id);

}

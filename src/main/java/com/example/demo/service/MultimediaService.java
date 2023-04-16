package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Multimedia;
import com.example.demo.entidades.Pruebas;
import com.example.demo.repositorio.MultimediaRepo;
import com.example.demo.repositorio.PruebaRepo;

@Service
public class MultimediaService implements MultimediaServiceInt{
	
	//autowired al repo
	@Autowired
	private MultimediaRepo multimediarepo;

	@Override
	public Iterable<Multimedia> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Multimedia> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Multimedia save(Multimedia multimedia) {
		// TODO Auto-generated method stub
		return multimediarepo.save(multimedia);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

}

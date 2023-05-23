package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Multimedia;

import com.example.demo.repositorio.MultimediaRepo;


@Service
public class MultimediaService implements BaseInterface<Multimedia>{
	
	//autowired al repo
	@Autowired
	private MultimediaRepo multimediarepo;

	@Override
	public List<Multimedia> findAll() {
		// TODO Auto-generated method stub
		return multimediarepo.findAll();
	}

	@Override
	public Optional<Multimedia> findById(Integer id) {
		// TODO Auto-generated method stub
		return multimediarepo.findById(id);
	}

	@Override
	public Multimedia save(Multimedia entidad) {
		// TODO Auto-generated method stub
		return multimediarepo.save(entidad);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		multimediarepo.deleteById(id);
	}





}

package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Pasos;
import com.example.demo.repositorio.PasoRepo;

@Service
public class PasoService implements BaseInterface<Pasos>{
	
	@Autowired
	private PasoRepo pasorepo;

	@Override
	public List<Pasos> findAll() {
		// TODO Auto-generated method stub
		return pasorepo.findAll();
	}

	@Override
	public Optional<Pasos> findById(Integer id) {
		// TODO Auto-generated method stub
		return pasorepo.findById(id);
	}

	@Override
	public Pasos save(Pasos entidad) {
		// TODO Auto-generated method stub
		return pasorepo.save(entidad);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		pasorepo.deleteById(id);
	}

}

package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Recetas;
import com.example.demo.repositorio.RecetasRepo;

@Service
public class RecetasService implements BaseInterface<Recetas>{
	
	@Autowired
	private RecetasRepo recetasrepo;

	@Override
	public List<Recetas> findAll() {
		// TODO Auto-generated method stub
		return recetasrepo.findAll();
	}

	@Override
	public Optional<Recetas> findById(Integer id) {
		// TODO Auto-generated method stub
		return recetasrepo.findById(id);
	}

	@Override
	public Recetas save(Recetas entidad) {
		// TODO Auto-generated method stub
		return recetasrepo.save(entidad);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		recetasrepo.deleteById(id);
		
	}

}

package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Ingrediente;
import com.example.demo.repositorio.IngredienteRepo;

@Service
public class IngredienteService implements BaseInterface<Ingrediente>{
	
	
	@Autowired
	private IngredienteRepo ingredienterepo;

	@Override
	public List<Ingrediente> findAll() {
		// TODO Auto-generated method stub
		return ingredienterepo.findAll();
	}

	@Override
	public Optional<Ingrediente> findById(Integer id) {
		// TODO Auto-generated method stub
		return ingredienterepo.findById(id);
	}

	@Override
	public Ingrediente save(Ingrediente entidad) {
		// TODO Auto-generated method stub
		return ingredienterepo.save(entidad);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		ingredienterepo.deleteById(id);
	}

}

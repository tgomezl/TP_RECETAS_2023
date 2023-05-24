package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Tipo;
import com.example.demo.repositorio.TipoRepo;

@Service
public class TipoService implements BaseInterface<Tipo>{
	
	@Autowired
	private TipoRepo tiporepo;

	@Override
	public List<Tipo> findAll() {
		// TODO Auto-generated method stub
		return tiporepo.findAll();
	}

	@Override
	public Optional<Tipo> findById(Integer id) {
		// TODO Auto-generated method stub
		return tiporepo.findById(id);
	}

	@Override
	public Tipo save(Tipo entidad) {
		// TODO Auto-generated method stub
		return tiporepo.save(entidad);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		tiporepo.deleteById(id);
	}

}

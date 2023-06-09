package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Usuarios;
import com.example.demo.repositorio.UsuarioRepo;

@Service
public class UsuarioService implements BaseInterface<Usuarios> {
	
	//autowired al repo(INTERFACE)
	@Autowired
	private UsuarioRepo userrepo;

	@Override
	public List<Usuarios> findAll() {
		// TODO Auto-generated method stub
		return userrepo.findAll();
	}

	@Override
	public Optional<Usuarios> findById(Integer id) {
		// TODO Auto-generated method stub
		return userrepo.findById(id);
	}

	@Override
	public Usuarios save(Usuarios entidad) {
		// TODO Auto-generated method stub
	
		return userrepo.save(entidad);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		//lo busca y lo elimina
		userrepo.deleteById(id);
	}

	
	

	
	
	
	
	
	
	
	/*
	@Override
	public Iterable<Usuarios> findAll() {
		// TODO Auto-generated method stub
		return userrepo.findAll();
	}

	@Override
	public Optional<Usuarios> findById(Integer id) {
		// TODO Auto-generated method stub
		return userrepo.findById(id);
	}

	@Override
	public Usuarios save(Usuarios user) {
		// TODO Auto-generated method stub
		return userrepo.save(user);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		userrepo.deleteById(id);
	}
	
	
	*/

}

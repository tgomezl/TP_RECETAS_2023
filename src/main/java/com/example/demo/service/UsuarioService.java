package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

	
	
	//NO ESTA EN USO
	public List<Usuarios> buscarUsuarioConSorting(String field) {
		return userrepo.findAll(Sort.by(Sort.Direction.ASC,field));
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
	
	public Usuarios findByMail(String mail) {
		Usuarios buscado=null;
		List<Usuarios> lista= userrepo.findByMail(mail);
		if(!lista.isEmpty()) {
			buscado=lista.get(0);
		}
		System.out.println("    no encontre user con ese mail");
		return buscado;
	}
	
	public Usuarios findByNickName(String nick) {
		Usuarios buscado=null;
		List<Usuarios> lista= userrepo.findByNickname(nick);
		if(!lista.isEmpty()) {
			buscado=lista.get(0);
		}
		System.out.println("    no encontre user con ese nick");
		return buscado;
	}

}

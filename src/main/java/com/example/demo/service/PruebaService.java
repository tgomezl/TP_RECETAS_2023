package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Pruebas;
import com.example.demo.repositorio.PruebaRepo;



@Service
public class PruebaService implements PruebaServiceInt{
	
	//autowired al repo
	@Autowired
	private PruebaRepo pruebarepo;

	@Override
	public List<Pruebas> findAll() {
		// TODO Auto-generated method stub
		List<Pruebas> lista =pruebarepo.findAll();
		return lista;
	}

	@Override
	public Optional<Pruebas> findById(Integer id) {
		// TODO Auto-generated method stub
		
		//busca usando un Integer xq YO DEFINI QUE EL ID ERA DE TIPO INTEGER
		return pruebarepo.findById(id) ;
	}

	@Override
	public Pruebas save(Pruebas prueba) {
		// TODO Auto-generated method stub
		return pruebarepo.save(prueba);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

}


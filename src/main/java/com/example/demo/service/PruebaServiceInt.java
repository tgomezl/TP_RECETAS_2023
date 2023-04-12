package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entidades.Pruebas;



public interface PruebaServiceInt {
		//limita los metodos
	
		public Iterable<Pruebas> findAll();
		
		public Optional<Pruebas> findById(Integer id);
		
		public Pruebas save(Pruebas prueba);
		
		public void deleteById(Integer id);
}

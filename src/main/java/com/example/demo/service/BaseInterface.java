package com.example.demo.service;

import java.util.List;
import java.util.Optional;

public interface BaseInterface<E> {
	
	public List<E> findAll();
	public Optional<E> findById(Integer id);
	public E save(E entidad);
	public void deleteById(Integer id);
	
}

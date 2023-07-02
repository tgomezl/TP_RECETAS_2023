package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositorio.FotoRepo;

@Service
public class FotoService {
	
	@Autowired
	private FotoRepo fotorepo;

}

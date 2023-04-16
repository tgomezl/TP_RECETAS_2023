package com.example.demo.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.example.demo.entidades.Usuarios;
import com.example.demo.service.UsuarioService;

@Component
public class UsuarioControlador {
	
	//autowired al service
	//-------------------------------------------------------------------------------------------------
	@Autowired
	private UsuarioService userservice;

	public Usuarios crearUser(Usuarios user) {
		// TODO Auto-generated method stub
		Usuarios creado=null;
		System.out.println("                   ");
		System.out.println(user);
		

		System.out.println("                   ");
		if(!user.getNombre().isBlank()) {
			creado=userservice.save(user);
		}
		return creado;
	}

	public Usuarios BuscarUser(int id) {
		
		Optional<Usuarios> buscado= userservice.findById(id);
		if(buscado.isPresent()) {
			// te devuelve un actor
			System.out.println("");
			System.out.println("");
			System.out.println("-------------lo encontre");
			System.out.println("");
			Usuarios user = buscado.get();
			return user;
		}
		return null;
	
	}

	public List<Usuarios> TraerLista() {
		//te devuelve la lista
		 List<Usuarios> listaActores = (List<Usuarios>) userservice.findAll();
	     return listaActores;
	}
	
	
}

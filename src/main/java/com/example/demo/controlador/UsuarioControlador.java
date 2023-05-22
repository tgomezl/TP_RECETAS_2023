package com.example.demo.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.example.demo.entidades.Usuarios;
import com.example.demo.service.UsuarioService;
//aca tendria que estar la logica
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

	public void cargarData() {
		

		// crea usuarios y los almacena en la bbdd
		//quiero contar cuantos usuarios hay cargados en la bbdd
		List<Usuarios> listausers=null;
		listausers=TraerLista();
		if (listausers.size()<1) {
			System.out.println("cargando algunos users");
			Usuarios user1=new Usuarios();
			user1.setAvatar("avatar");
			user1.setHabilitado("Si");
			user1.setMail("mailfalso@gmail.com");
			user1.setNickname("nickname");
			user1.setNombre("mario santos");
			user1.setTipo_usuario("Alumno");
			user1.setRecetas(null);
			
			userservice.save(user1);
			
			
			Usuarios user2=new Usuarios();
			user2.setAvatar("avatar2");
			user2.setHabilitado("Si");
			user2.setMail("mailfalso222@gmail.com");
			user2.setNickname("nickname22");
			user2.setNombre("maximo cosetti");
			user2.setTipo_usuario("Alumno");
			user1.setRecetas(null);
			
			userservice.save(user2);
		}
		
		
		
	}
	
	
}

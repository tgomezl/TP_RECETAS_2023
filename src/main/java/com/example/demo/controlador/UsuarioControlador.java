package com.example.demo.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entidades.Recetas;
import com.example.demo.entidades.Usuarios;
import com.example.demo.service.UsuarioService;
//aca tendria que estar la logica
@Component
public class UsuarioControlador {
	
	//autowired al service
	//-------------------------------------------------------------------------------------------------
	@Autowired
	private UsuarioService userservice;
	

	//crear
	public Usuarios crearUser(Usuarios user) {
		// TODO Auto-generated method stub
		Usuarios creado=null;
		System.out.println("datos recibidos ");
		System.out.println(user);
		
		/*valido los datos recibidos*/
		System.out.println("                   ");
		if(!user.getNombre().isBlank()) {
			creado=userservice.save(user);
		}
		return creado;
	}

	//buscar
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

	//traer todos
	public List<Usuarios> TraerLista() {
		//te devuelve la lista
		System.out.println(" ");
		System.out.println("              buscando en traer lista");
		System.out.println("   ");
		 List<Usuarios> listaActores = (List<Usuarios>) userservice.findAll();
	     return listaActores;
	}


	//buscar uno + borrar
	public Boolean borrarUser(int id) {
		// TODO Auto-generated method stub
		//USA THIS
		Usuarios buscado = this.BuscarUser(id);
		if (buscado==null){
			//no lo encontro
			return false;
		}
		//lo encontro
		userservice.deleteById(id);
		return true;
	}

	//busca y modifica
	public Usuarios updateUser(int id, Usuarios user) {
		// lo busca y si lo encuentra lo modifica
		Usuarios userbuscado=this.BuscarUser(id);
		if (userbuscado==null){
			//no lo encontro
			return null;
		}else {
			//lo piso
			userbuscado=userservice.save(user);
			return userbuscado;
		}
	
	}
	
	


	
	
}

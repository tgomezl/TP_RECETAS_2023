package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entidades.Recetas;
import com.example.demo.entidades.Tipo;
import com.example.demo.entidades.Usuarios;
import com.example.demo.service.MultimediaService;
import com.example.demo.service.RecetasService;
import com.example.demo.service.TipoService;
import com.example.demo.service.UsuarioService;

@Component
public class GenericoControlador {
	
	//sirve solo para precargar data en la bbdd
	
	
	/*usuarios*/
	@Autowired
	private UsuarioService usuarioservice;
	
	@Autowired
	private UsuarioControlador usuariocontrolador;
	
	
	/*RECETA*/
	@Autowired
	private RecetasService recetasservice;
	
	@Autowired
	private RecetasControlador recetascontrolador;
	
	/*multimedia*/
	@Autowired 
	private MultimediaService multimediaservice;
	
	/*tipo de receta*/
	@Autowired
	private TipoService tiposervice;
	
	
	public GenericoControlador() {
		
	}
	
/*--------------------------------------------------------------------------------*/
	//data mock
	public void cargarUsuarios() {
		

		// crea usuarios y los almacena en la bbdd
		//quiero contar cuantos usuarios hay cargados en la bbdd
		List<Usuarios> listausers=null;
		listausers=usuariocontrolador.TraerLista();
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
			
			usuarioservice.save(user1);
			
			
			Usuarios user2=new Usuarios();
			user2.setAvatar("avatar2");
			user2.setHabilitado("Si");
			user2.setMail("mailfalso222@gmail.com");
			user2.setNickname("nickname22");
			user2.setNombre("maximo cosetti");
			user2.setTipo_usuario("Alumno");
			user1.setRecetas(null);
			
			usuarioservice.save(user2);
		}
		
		
		
	}
	
	//data mock
	public void crearAlgunaReceta() {
		List<Usuarios> listausers=null;
		listausers=usuariocontrolador.TraerLista();
		if (!listausers.isEmpty()) {
			Usuarios user=listausers.get(0);
			//busco algun tipo ya cargado
			List<Tipo> listatipos=tiposervice.findAll();
			if (!listatipos.isEmpty()) {
				Tipo tipo=listatipos.get(0);
				//creo la receta
				Recetas nuevaReceta=new Recetas();
				nuevaReceta.setearParametrosMock(user, tipo);
				
				//le agrego al receta al user
				user.addReceta(nuevaReceta);   
				//la agrego al user a la receta
				nuevaReceta.setUsuario(user);
				
//tengo que hace save de ambos???????
				usuarioservice.save(user);//hasta aca estoy haciendo solo el save del user
				
				//aca hago ambos save
				recetasservice.save(nuevaReceta);
				//hago save del user
				
				//hago save de la receta
				
				
			}
			

			
			
		}
		
	}

	public void crearTipos() {
		// TODO Auto-generated method stub
		Tipo nuevotipo =null;
		nuevotipo=new Tipo();
		nuevotipo.setDescripcion("pasta");
		tiposervice.save(nuevotipo);
		
		nuevotipo=new Tipo();
		nuevotipo.setDescripcion("carne");
		tiposervice.save(nuevotipo);
		
	}


}

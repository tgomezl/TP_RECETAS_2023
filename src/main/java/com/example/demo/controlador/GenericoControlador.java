package com.example.demo.controlador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;


import java.util.logging.Level;
import java.util.logging.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entidades.Ingrediente;
import com.example.demo.entidades.Multimedia;
import com.example.demo.entidades.Pasos;
import com.example.demo.entidades.Recetas;
import com.example.demo.entidades.Tipo;
import com.example.demo.entidades.Usuarios;
import com.example.demo.service.EmailSenderService;
import com.example.demo.service.IngredienteService;
import com.example.demo.service.MultimediaService;
import com.example.demo.service.PasoService;
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
	
	/*ingrediente*/
	@Autowired
	private IngredienteService ingredienteservice;
	
	
	/*pasos*/
	@Autowired
	private PasoService pasoservice;
	
	@Autowired
	private EmailSenderService emailsender;
	
	public GenericoControlador() {
		
	}
	
/*--------------------------------------------------------------------------------*/
	//data mock
	public void cargarUsuarios() {
		

		// crea usuarios y los almacena en la bbdd
		//quiero contar cuantos usuarios hay cargados en la bbdd
		List<Usuarios> listausers=null;
		listausers=usuariocontrolador.TraerListaUsers();
		Integer clave;
		if (listausers.size()<2) {
			System.out.println("cargando algunos users");
			Usuarios user1=new Usuarios();
			user1.setAvatar("avatar1");
			user1.setHabilitado("Si");
			user1.setMail("hsimpson@gmail.com");
			user1.setNickname("homero01");
			user1.setNombre("homero simpson");
			user1.setTipo_usuario("Alumno");
			user1.setRecetas(null);
			user1.setContrasenia("admin");
			clave=usuariocontrolador.generarClaveAleatoria();
			user1.setClaveDeRecu(clave);
	//homero es admin
			user1.setEsadmin(true);
			
			usuarioservice.save(user1);
			
			
			Usuarios user2=new Usuarios();
			user2.setAvatar("avatar2");
			user2.setHabilitado("Si");
			user2.setMail("bart@gmail.com");
			user2.setNickname("el barto");
			user2.setNombre("bart simpson");
			user2.setTipo_usuario("Alumno");
			user2.setRecetas(null);
			user2.setContrasenia("contra");
			clave=usuariocontrolador.generarClaveAleatoria();
			user2.setClaveDeRecu(clave);
			
			usuarioservice.save(user2);
			
			
			Usuarios user3=new Usuarios();
			user3.setAvatar("avatar3");
			user3.setHabilitado("Si");
			user3.setMail("lisa@gmail.com");
			user3.setNickname("lisa_l");
			user3.setNombre("lisa simpson");
			user3.setTipo_usuario("Visitante");
			user3.setContrasenia("contra");
			user3.setRecetas(null);
			clave=usuariocontrolador.generarClaveAleatoria();
			user3.setClaveDeRecu(clave);
			
			usuarioservice.save(user3);
			
			Usuarios user4=new Usuarios();
			user4.setAvatar("avatar4");
			user4.setHabilitado("Si");
			user4.setMail("marge@gmail.com");
			user4.setNickname("marge22");
			user4.setNombre("marge simpson");
			user4.setTipo_usuario("Visitante");
			user4.setContrasenia("contra");
			user4.setRecetas(null);
			clave=usuariocontrolador.generarClaveAleatoria();
			user4.setClaveDeRecu(clave);
			
			usuarioservice.save(user4);
			
			
		}
		
		
		
	}
	
	//data mock
	public void crearRecetasConUser() {
		List<String> titulos=new ArrayList<>();
		titulos.add("tarta de atun");
		titulos.add("pollo a la portuguesa");
		titulos.add( "tallarines al pesto");
		titulos.add( "tarta de manzana");
		titulos.add( "ravioles de pollo");
		
		List<Integer> porciones=new ArrayList<>();
		porciones.add(2);
		porciones.add(4);
		porciones.add(6);
		porciones.add(8);
		
		List<Usuarios> listausers=null;
		Usuarios user=null;
		
		//busco algun tipo ya cargado
		List<Tipo> listatipos=tiposervice.findAll();
		for(int i=0;i<4;i++) {
			
			listausers=usuariocontrolador.TraerListaUsers();
			if (!listausers.isEmpty()) {
				Collections.shuffle(listausers);
				user=listausers.get(0);
				
				
				if (!listatipos.isEmpty()) {
					Collections.shuffle(titulos);
					Collections.shuffle(listatipos);
					Collections.shuffle(porciones);
					Tipo tipo=listatipos.get(0);
					//creo la receta
					Recetas nuevaReceta=new Recetas();
					nuevaReceta.setearParametrosMock(user, tipo);
					nuevaReceta.setNombre(titulos.get(0));
					nuevaReceta.setPorciones(porciones.get(0));
					
					//le agrego al receta al user y el user a la receta
					user.addReceta(nuevaReceta);   
					
					
	//tengo que hace save de ambos???????RTA:siiiii
					//usuarioservice.save(user);
					
					//aca hago ambos save
					recetasservice.save(nuevaReceta);	
					
				}
				
				
			}
			
		}
		
		
	}
	
	//data mock
		public void cargarUnicaREcetaConUSer() {
			List<Usuarios> listausers=usuariocontrolador.TraerListaUsers();
			if (!listausers.isEmpty()) {
				Collections.shuffle(listausers);
				Usuarios user=listausers.get(0);
				List<Tipo> listatipos=tiposervice.findAll();
				Tipo tipo=listatipos.get(0);
				Recetas nuevaReceta=new Recetas();
				nuevaReceta.setAprobada(false);
				nuevaReceta.setCantidadPersonas(22);
				nuevaReceta.setDescripcion("receta de prueba");
				nuevaReceta.setFotounica("url de foto unica");
				nuevaReceta.setNombre("receta false");
				
				
				//le agrego al receta al user y el user a la receta
				user.addReceta(nuevaReceta);   
				
				System.out.println("     ");
				System.out.println("     ");
				System.out.println("     imprimo al user");
				System.out.println("user "+user);
				System.out.println("su receta " +user.getRecetas().get(0));
//tengo que hace save de ambos???????RTA:siiiii
				
				//usuarioservice.save(user);
				
				recetasservice.save(nuevaReceta);
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
		
		nuevotipo=new Tipo();
		nuevotipo.setDescripcion("pescado");
		tiposervice.save(nuevotipo);
		
		nuevotipo=new Tipo();
		nuevotipo.setDescripcion("vegetariano");
		tiposervice.save(nuevotipo);
		
	}

	public void crearAlgunaRecetaSINUser() {
		// carga recetas sin user y sin tipo
		
		Recetas receta=null;
		
		
		receta=new Recetas();
		receta.setCantidadPersonas(2);
		receta.setDescripcion("lleve su pato pequines");
		receta.setFotounica("foto unica");
		receta.setNombre("pato pequines");
		receta.setPorciones(5);
		
		recetasservice.save(receta);
		
		
		receta=new Recetas();
		receta.setCantidadPersonas(2);
		receta.setDescripcion("asado al horno");
		receta.setFotounica("foto unica");
		receta.setNombre("asado");
		receta.setPorciones(5);
		
		recetasservice.save(receta);
		
	}

	public void crearIngredientes() {
		// TODO Auto-generated method stub
		Ingrediente ing = null;
		ing=new Ingrediente();
		ing.setNombre("cebolla");
		ingredienteservice.save(ing);
		
		ing=new Ingrediente();
		ing.setNombre("papas");
		ingredienteservice.save(ing);
		
		ing=new Ingrediente();
		ing.setNombre("tomate");
		ingredienteservice.save(ing);
		
		ing=new Ingrediente();
		ing.setNombre("zanahorias");
		ingredienteservice.save(ing);
		
		ing=new Ingrediente();
		ing.setNombre("albaca");
		ingredienteservice.save(ing);
		
		ing=new Ingrediente();
		ing.setNombre("ajo");
		ingredienteservice.save(ing);
		
		
		
	}

	public void agregarPasos() {
		// le agrega pasos a alguna receta ya cargada
		List<Recetas> recetas=recetasservice.findAll();
		if(!recetas.isEmpty()) {
			//creo el paso y lo agrego
			Pasos paso =new Pasos();
			paso.setNroPaso(1);
			paso.setTexto("texto del paso uno");
			Recetas estareceta=recetas.get(0);
			estareceta.ADDpasos(paso);
			pasoservice.save(paso);
			
			
			paso =new Pasos();
			paso.setNroPaso(2);
			paso.setTexto("texto del paso dos");
			estareceta.ADDpasos(paso);
			pasoservice.save(paso);

			//hago el save de ambas
	
			//recetasservice.save(estareceta);
			
			if(recetas.size()>1) {
				estareceta=recetas.get(1);
				paso =new Pasos();
				paso.setNroPaso(1);
				paso.setTexto("texto del paso uno");
				estareceta.ADDpasos(paso);
				
				pasoservice.save(paso);
				
				paso =new Pasos();
				paso.setNroPaso(2);
				paso.setTexto("texto del paso dos");
				estareceta.ADDpasos(paso);
				pasoservice.save(paso);
				//recetasservice.save(estareceta);
			}
			
		}
		
		
	}
	
	//email sender
	public void enviarEmail() {
		emailsender.sendEmail();
	}

	public void agregarMultimedia() {
		// TODO Auto-generated method stub
		Multimedia multi=new Multimedia();
		multi.setExtension("JPG");
		multi.setTipo_contenido("imagen");
		multi.setUrlContenido("url de prueba");
		multimediaservice.save(multi);
	}

}

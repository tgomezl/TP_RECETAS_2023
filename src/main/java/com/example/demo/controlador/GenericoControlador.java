package com.example.demo.controlador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entidades.Ingrediente;
import com.example.demo.entidades.Pasos;
import com.example.demo.entidades.Recetas;
import com.example.demo.entidades.Tipo;
import com.example.demo.entidades.Usuarios;
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
	
	public GenericoControlador() {
		
	}
	
/*--------------------------------------------------------------------------------*/
	//data mock
	public void cargarUsuarios() {
		

		// crea usuarios y los almacena en la bbdd
		//quiero contar cuantos usuarios hay cargados en la bbdd
		List<Usuarios> listausers=null;
		listausers=usuariocontrolador.TraerLista();
		if (listausers.size()<2) {
			System.out.println("cargando algunos users");
			Usuarios user1=new Usuarios();
			user1.setAvatar("avatar");
			user1.setHabilitado("Si");
			user1.setMail("mailfalso@gmail.com");
			user1.setNickname("nickname");
			user1.setNombre("homero simpson");
			user1.setTipo_usuario("Alumno");
			user1.setRecetas(null);
			user1.setContrasenia("admin");
	//homero es admin
			user1.setEsadmin(true);
			
			usuarioservice.save(user1);
			
			
			Usuarios user2=new Usuarios();
			user2.setAvatar("avatar2");
			user2.setHabilitado("Si");
			user2.setMail("mailfalso222@gmail.com");
			user2.setNickname("nickname22");
			user2.setNombre("bart simpson");
			user2.setTipo_usuario("Alumno");
			user2.setRecetas(null);
			user2.setContrasenia("contra");
			
			usuarioservice.save(user2);
			
			
			Usuarios user3=new Usuarios();
			user3.setAvatar("avatar2");
			user3.setHabilitado("Si");
			user3.setMail("mailfalso222@gmail.com");
			user3.setNickname("nickname22");
			user3.setNombre("lisa simpson");
			user3.setTipo_usuario("Visitante");
			user3.setContrasenia("contra");
			user3.setRecetas(null);
			
			usuarioservice.save(user3);
			
			
		}
		
		
		
	}
	
	//data mock
	public void crearAlgunaRecetaConUser() {
		List<String> titulos=new ArrayList<>();
		titulos.add("tarta de atun");
		titulos.add("pollo a la portuguesa");
		titulos.add( "tallarines al pesto");
		
		List<Integer> porciones=new ArrayList<>();
		porciones.add(2);
		porciones.add(4);
		porciones.add(6);
		porciones.add(8);
		for(int i=0;i<2;i++) {
			List<Usuarios> listausers=null;
			listausers=usuariocontrolador.TraerLista();
			if (!listausers.isEmpty()) {
				Collections.shuffle(listausers);
				Usuarios user=listausers.get(0);
				//busco algun tipo ya cargado
				List<Tipo> listatipos=tiposervice.findAll();
				
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
					usuarioservice.save(user);
					
					//aca hago ambos save
					recetasservice.save(nuevaReceta);	
					
				}
				
				
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
		
		nuevotipo=new Tipo();
		nuevotipo.setDescripcion("pescado");
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
	
			recetasservice.save(estareceta);
			
		}
		
		
	}


}

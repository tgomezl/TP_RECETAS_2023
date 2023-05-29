package com.example.demo.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entidades.Pasos;
import com.example.demo.entidades.Recetas;
import com.example.demo.entidades.Usuarios;
import com.example.demo.service.RecetasService;
import com.example.demo.service.UsuarioService;

//toda la logica de las recetas
@Component
public class RecetasControlador {
	
	@Autowired
	private RecetasService recetasservice;
	
	@Autowired
	private UsuarioService usuarioservice;
	
	/*
	@Autowired
	private UsuarioService userservice;
	*/
	
	@Autowired
	private UsuarioControlador usuariocontrolador;

	public Recetas crearReceta(Recetas receta) {
		// si pasa las validaciones TODO
		//todo
		if(!receta.getNombre().isEmpty()) {
			//paso las validaciones
			return recetasservice.save(receta);
		}
		return null;
		
	}

	public List<Recetas> traerRecetas() {
		// devuelve todas las recetas de la bbbdd
		return recetasservice.findAll();
	}

	public List<Recetas> traerRecetasdeunuser(int id) {
		// buscar todas las recetas de un user
		//se fija si el user existe
		Usuarios user=usuariocontrolador.BuscarUser(id);
		if(user!=null) {
			List<Recetas> lista= user.getRecetas();
			return lista;
		}
		return null;
		
	}

	public Recetas busacarUnaReceta(int id) {
		// TODO Auto-generated method stub
		Recetas encontrada=null;
		Optional<Recetas> buscada= recetasservice.findById(id);
		if(buscada.isPresent()) {
			encontrada=buscada.get();
		}
		return encontrada;
	}

	public List<Recetas> traerRecetasAprobadas() {
		// devuelve todas las recetas APROBADAS de la bbbdd
		List<Recetas> lista= recetasservice.findAll();
		List<Recetas> listadeaprobadas=new ArrayList<>();
		for(Recetas r:lista) {
			if(r.getAprobada()) {
				listadeaprobadas.add(r);
			}
		}
		
		return listadeaprobadas;
	}

	public Recetas busacarUnaRecetaPorNombre(String nombre, Integer iduser) {
		// la receta DEBE pertenecer al usuario
		Recetas buscada=null;
		Usuarios userbuscado=usuariocontrolador.BuscarUser(iduser);
		if (userbuscado!=null) {
			List<Recetas> susrecetas= userbuscado.getRecetas();
			
			if(!susrecetas.isEmpty()) {
				//recorre y busca la receta por nombre
				for(Recetas r:susrecetas ) {
					if(r.getNombre().equalsIgnoreCase(nombre)) {
						buscada=r;
						break;
					}
				}
			}
		}
		
		return buscada;
	}

	public boolean eliminarReceta(int idreceta, int iduser) {
		// solo elimina si tiene permiso de hacerlo
		boolean eliminado=false;
		
		Usuarios user = usuariocontrolador.BuscarUser(iduser);
		if(user!=null) {
			//me fijo si existe la receta
			Recetas recetabuscada=busacarUnaReceta(idreceta);
			if(recetabuscada!=null) {
				//existe
				//me fijo si ese user puede ver la receta
				boolean permiso=checkPermisoVerReceta(user, recetabuscada);
				if(permiso) {
					//tiene permiso
					//receta esta atada a los pasos y al usuario
					this.desatarUseryReceta(recetabuscada);
					recetasservice.deleteById(idreceta);//deberia eliminar tambien los pasos
	//OJO ACAAAA
	//OJO xq la receta esta referenciadad de muchos lugares
					
					eliminado=true;
				}
			}
		}
		return eliminado;
	}


	
	public void desatarUseryReceta(Recetas receta) {
// la receta esta anclada al user y a los pasos
		Usuarios usuario=receta.getUsuario();
		if(usuario!=null) {
			usuario.quitarReceta(receta);
		}
		
		
	}
	 

	public Recetas editarReceta(Integer idreceta, Integer iduser,Recetas receta) {
		//se fija si tiene permiso
		//me fijo si existe el user
		Usuarios user = usuariocontrolador.BuscarUser(iduser);
		if(user!=null) {
			//me fijo si existe la receta
			Recetas recetabuscada=busacarUnaReceta(idreceta);
			if(recetabuscada!=null) {
				//existe
				//me fijo si ese user puede ver la receta
				boolean permiso=checkPermisoVerReceta(user, recetabuscada);
				if(permiso) {
					//la edito
					receta.setAprobada(false); //es otro endpoint distinto
					Integer esteid=recetabuscada.getIdReceta();
					receta.setIdReceta(esteid);  //respeto el mismo id
					recetabuscada=recetasservice.save(receta);
					return recetabuscada;
					//necesito recibir el id en el body
				}
			}
			
		}
		
		return null;
		
	}

	private boolean checkPermisoVerReceta(Usuarios user, Recetas receta) {
		// TODO Auto-generated method stub
		boolean permiso=false;
		if(user.getEsadmin()) {
			//es admin
			permiso=true;
		}else {
			//es su receta
			if(user.getRecetas().contains(receta)) {
				permiso=true;
			}
		}
		return permiso;
	}

	public Recetas aprobarReceta(Integer idreceta,Integer idadmin ) {
		//solo el admin puede
		Recetas aprobada=null;
		
		//busco al admin
		Usuarios user = usuariocontrolador.BuscarUser(idadmin);
		if(user!=null) {
			if(user.getEsadmin()) {
				//busco la receta
				Recetas recetabuscada=busacarUnaReceta(idreceta);
				if(recetabuscada!=null) {
					recetabuscada.setAprobada(true);
					//tengo que hacer el save??
					aprobada= recetasservice.save(recetabuscada);
				}
			}
		}
		
		
		
		return aprobada;
	}
//metodo solo para testear
	public Recetas metododetesteo(int idreceta) {
		// HACE FALTA HACER EL SAVE??
		Recetas modificada=null;
		Recetas recetabuscada= busacarUnaReceta(idreceta);
		if(recetabuscada!=null) {
			//alguna modificacion
			recetabuscada.setPorciones(45);
			//hago el save
			recetasservice.save(recetabuscada);
			//la retorna
			modificada=recetabuscada;
		}
		return modificada;
	}
	
	
	public void pisarReceta(Recetas receta) {
		recetasservice.save(receta);
	}
	
	

}

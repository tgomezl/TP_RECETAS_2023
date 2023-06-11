package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controlador.RecetasControlador;
import com.example.demo.entidades.Recetas;
import com.example.demo.entidades.Usuarios;
import com.example.demo.vistas.RecetasVista;

@RestController
@RequestMapping("/recetas")
@CrossOrigin(origins = "*")
public class RecetasRest {
	
	
	@Autowired
	private RecetasControlador recetacontrolador;
	
	//CREAR una receta!!!!!!!!!!
	//deberia pasarle el id del user creador de la receta!!!
	//por ahora la crea sin user
	@PostMapping("")
	public ResponseEntity<?> crearReceta(@RequestBody Recetas receta){
		
		
		Recetas creada=recetacontrolador.crearReceta(receta);
		if(creada!=null) {
			
			return ResponseEntity.ok(creada);
		}
	
		else {
			return ResponseEntity.notFound().build();	
		
		}
	}
	
	//crear una receta CON usuario
	@PostMapping("/{iduser}")
	public ResponseEntity<?> crearRecetaConUsuario(@PathVariable(value="iduser") String iduser,@RequestBody Recetas receta){
		
		
		Recetas creada=recetacontrolador.crearRecetaConUser(Integer.parseInt(iduser),receta);
		if(creada!=null) {
			
			return ResponseEntity.ok(creada);
		}
	
		else {
			return ResponseEntity.notFound().build();	
		
		}
	}
	
	
	//traer TODAS las recetas APROBADAS!!! de la bbdd
	@GetMapping
	public ResponseEntity<?> traerAllrecetasAprobadas(){
		List<Recetas> lista= recetacontrolador.traerRecetasAprobadas();
		return ResponseEntity.ok(lista);
	}
	
	
	// traer TODAS las recetas (aprobadas o no)!!!
	@GetMapping("/todas")
	public ResponseEntity<?> traerTodasrecetas() {
		//quilombo con jackson
		List<Recetas> lista = recetacontrolador.traerRecetas();
		return ResponseEntity.ok(lista);
	}
	
	
	//traer todas las recetas CREADAS por un usuario
	@GetMapping("/user/{iduser}")
	public ResponseEntity<?> traerrecetasDeUnUser(@PathVariable(value="iduser") String id){
		
		List<Recetas> lista= recetacontrolador.traerRecetasdeunuser(Integer.parseInt(id));
		if(lista==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
	
	
	
	//traer todas las recetas de UNA LISTA determinada de un user determinado
	//TO DO
	
	
	
	
	
	//buscar una receta por id
	//ojo: no chequea si tenes permiso o no
	@GetMapping("/{idreceta}")
	public ResponseEntity<?> traerRecetaPorId(@PathVariable(value="idreceta") String id){
		
		Recetas buscada= recetacontrolador.busacarUnaReceta(Integer.parseInt(id));
		if(buscada!=null) {
			return ResponseEntity.ok(buscada);
		}
		return ResponseEntity.notFound().build();
	}
	
	
	
//OJO CON ESTTE:
	//eleiminar una receta por id solo si sos e creador o sos admin
	@DeleteMapping("/eliminar/{idreceta}/{iduser}")
	public ResponseEntity eliminarMiReceta(@PathVariable(value="idreceta") String idreceta, 
			@PathVariable(value="iduser") String iduser){
		//para eliminar, la receta debe ser tuya o debes ser admin
		
		boolean eliminado= recetacontrolador.eliminarReceta(Integer.parseInt(idreceta), Integer.parseInt(iduser));
		if(eliminado) {
			//lo elimino
			return new ResponseEntity(HttpStatus.ACCEPTED);
		}
		//no se eleimino
		return ResponseEntity.notFound().build();
		
		
	}
	
	
	//editar una receta solo si sos el creador o el admin
	//se puede modificar todos los campos pero no se puede aprobar
	@PutMapping("/editar/{idreceta}/{iduser}")
	public ResponseEntity<?> editarReceta(@PathVariable(value="idreceta") String idreceta, 
			@PathVariable(value="iduser") String iduser,@RequestBody Recetas receta){
		
		//se fija si tenes permiso de editar
		Recetas modificada=recetacontrolador.editarReceta(Integer.parseInt(idreceta), Integer.parseInt(iduser),receta);
		if(modificada!=null) {
			return ResponseEntity.ok(modificada);
		}
		return ResponseEntity.notFound().build();
		
	}
	
	//aprobar receta
	//solo el admin
	@PatchMapping("/aprobar/{idreceta}/{idadmin}")
	public ResponseEntity<?> aprobarReceta(@PathVariable(value="idreceta") String idreceta, 
			@PathVariable(value="idadmin") String idadmin){
		
		Recetas aprobada=recetacontrolador.aprobarReceta(Integer.parseInt(idreceta),Integer.parseInt(idadmin) );
		
		if(aprobada!=null) {
			return ResponseEntity.ok(aprobada);
		}
			return ResponseEntity.notFound().build();
		
	}
	
	
	
	
	//FILTRAR RECETAS POR ALGUN CAMPO!!!!!!!!!
	
	
	
	//buscar receta por nombre. la receta debe PERTENECER al usuario
	@GetMapping("/{iduser}/{nombre}")
	public ResponseEntity<?> traerRecetaPorNombre(@PathVariable(value="nombre") String nombre, @PathVariable(value="iduser") String id){
		System.out.println("traer receta por nombre");
		Recetas buscada= recetacontrolador.busacarUnaRecetaPorNombre(nombre, Integer.parseInt(id));
		if(buscada!=null) {
			return ResponseEntity.ok(buscada);
		}
		return ResponseEntity.notFound().build();
	}
	
	/*metodo de testeo!!!!!!*/
	//@GetMapping("/testing/{idreceta}")
	@PutMapping("/testing/{idreceta}")
	public ResponseEntity<?> metododetesteo(@PathVariable(value="idreceta") String id){
		
		Recetas buscada= recetacontrolador.metododetesteo(Integer.parseInt(id));
		if(buscada!=null) {
			return ResponseEntity.ok(buscada);
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@GetMapping("/recetasordenalfabetico")
	public ResponseEntity<?> ordenarPorNombreAsc(){
		System.out.println("compararndo");
		List<Recetas> listarecetas= recetacontrolador.ordenarPorNombreAsc();
		return ResponseEntity.ok(listarecetas);
	}
	

	@GetMapping("/recetaspornombreusuario")
	public ResponseEntity<?> ordenarPorNombreUsuarioAsc(){
		System.out.println("compararndo");
		List<RecetasVista> listarecetas= recetacontrolador.ordenarRVPorNombreUsuarioAsc();
		return ResponseEntity.ok(listarecetas);
	}
	
	@GetMapping("/recetasporfechacreacion")
	public ResponseEntity<?> ordenarPorFechaCreacion(){
		System.out.println("compararndo");
		List<RecetasVista> listarecetasvista= recetacontrolador.ordenarRVPorFechaCreacion();
		return ResponseEntity.ok(listarecetasvista);
	}
	

}
	


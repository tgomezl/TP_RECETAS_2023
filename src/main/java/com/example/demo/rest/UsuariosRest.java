package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controlador.UsuarioControlador;

import com.example.demo.entidades.Usuarios;


/*     http://localhost:8080/usuarios*/
@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuariosRest {
	
	@Autowired
	private UsuarioControlador usercontrolador;
	
	
	@GetMapping("/probar")
	public String probar() {
		return "el endpoint funciona";
	}
	
	
	
	@PostMapping
    public ResponseEntity<?> crearUser(@RequestBody Usuarios user){ 
		Usuarios creado = usercontrolador.crearUser(user);
		if(creado!=null) {
			return ResponseEntity.ok(creado);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarUsuario(@PathVariable(value="id") String id) {
		// llamo al metodo del controlador.(busca usando Integer)
		System.out.println("el id es    "+id);
		Usuarios buscado = usercontrolador.BuscarUser(Integer.parseInt(id));
		System.out.println("     ");
		System.out.println("     ");
		System.out.println("     ");
		System.out.println("el di es    "+buscado);
		if (buscado == null) {
			// no estaba
			return ResponseEntity.notFound().build();
		}
		System.out.println(" ");
		System.out.println("-----------------------------actorbuscado: " + buscado);
		System.out.println(" ");
		return ResponseEntity.ok(buscado);
	}
	
	@GetMapping
	public ResponseEntity<?> buscarUsers() {
		// llamo al metodo del controlador.(busca usando Integer)
		List<Usuarios> lista = usercontrolador.TraerLista();
		//de ultima te devuelve una lista vacia
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println(" lista: "+ lista);
		System.out.println("");
		System.out.println("");
		
		return ResponseEntity.ok(lista);

	}
	
	//borrar usuario
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarUser(@PathVariable(value="id") String id){
//que pasa con las recetas de un user cuando ese user se elimina???
		Boolean borrado= usercontrolador.borrarUser(Integer.parseInt(id));
		if(borrado) {
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	//update de usuario
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable(value="id") String id, @RequestBody Usuarios user){
		//necesito pasarle el id actual dentro del body
		Usuarios modificado=usercontrolador.updateUser(Integer.parseInt(id), user);
		if(modificado!=null) {
			return ResponseEntity.ok(modificado); 
		}
		 return ResponseEntity.notFound().build();
		
	}
	

}

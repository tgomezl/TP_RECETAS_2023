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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controlador.UsuarioControlador;

import com.example.demo.entidades.Usuarios;
import com.example.demo.vistas.CalificacionesVista;
import com.example.demo.vistas.UserConReceta;
import com.example.demo.vistas.UserLogin;
import com.example.demo.vistas.UsuarioConClaveDeRecu;
import com.example.demo.vistas.UsuarioVista;



/*     http://localhost:8080/usuarios*/
@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuariosRest {
	
	@Autowired
	private UsuarioControlador usercontrolador;
	
	//end point de prueba
	@GetMapping("/probar")
	public String probar() {
		return "el endpoint funciona";
	}
	
	
	//full body(NO USAR ESTE METODO!!!!)
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
	
	//POST con solo mail y alias
	@PostMapping("/nuevousuario")
	public ResponseEntity<?> crearUsersolomailyalias(@RequestBody Usuarios user) {
		System.out.println("llego");
		Usuarios creado = usercontrolador.crearUsersolomailyalias(user);
		if (creado != null) {
			return ResponseEntity.ok(creado);
		} else {
			
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Email ya registrado");
		}
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarUsuario(@PathVariable(value="id") String id) {
		// llamo al metodo del controlador.(busca usando Integer)
		System.out.println("el id es    "+id);
		//UserConReceta buscado = usercontrolador.BuscarUserConReceta(Integer.parseInt(id));
		UsuarioVista buscado = usercontrolador.BuscarUserVista(Integer.parseInt(id));
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
		List<UsuarioVista> lista = usercontrolador.TraerListaUsersVista();
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
	public ResponseEntity<?> updateUser(@PathVariable(value="id") String id, @RequestBody UsuarioConClaveDeRecu user){
		//necesito pasarle el id actual dentro del body
		UsuarioVista modificado=usercontrolador.updateUser(Integer.parseInt(id), user);
		if(modificado!=null) {
			return ResponseEntity.ok(modificado); 
		}
		 return ResponseEntity.notFound().build();
		
	}
	
	//este metodo se usa para aprobar al user
	@PatchMapping("/aprobar/{iduser}")
	public ResponseEntity<?> aprobarUser(@PathVariable(value="iduser") String id){
		boolean aprobado = usercontrolador.aprobaruser(Integer.parseInt(id));
		if(aprobado) {
			return ResponseEntity.ok(null);
		}
		return ResponseEntity.notFound().build();
		
	}
	
	//una vez que recibe el codgio por mail
	@PatchMapping("/terminaralta")
	public ResponseEntity<?> terminaraltaUser(@RequestBody UsuarioConClaveDeRecu cuerpo){
		UsuarioVista modificado = usercontrolador.terminaralta( cuerpo);
		if(modificado!=null) {
			return ResponseEntity.ok(modificado); 
		}
		return ResponseEntity.notFound().build();
		
	}
	
	//login
	@PostMapping("/login")
	public ResponseEntity<?> hacerlogin(@RequestBody UserLogin user){
		
		UsuarioVista logueado =usercontrolador.login(user);
		if(logueado!=null) {
			return ResponseEntity.ok(logueado);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
		
	}
	
	//OJO te devuelve una vista del user. USUARIOS!=USUARIOSVISTA
		@GetMapping("/vista/{id}")
		public ResponseEntity<?> buscarUsuarioVista(@PathVariable(value="id") String id) {
			// llamo al metodo del controlador.(busca usando Integer)
			System.out.println("el id es    "+id);
			UserConReceta buscado = usercontrolador.BuscarUserConReceta(Integer.parseInt(id));
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
		
		//un metodo para recuperar la contraseña!!!
		@GetMapping("/enviarcodigopormail")
		public ResponseEntity<?> recuperarcontraseña(@RequestBody UserConReceta user){
			//solo uso el mail de ese json
			boolean mailenviado =usercontrolador.recuperarcontra(user.getMail());
			if(mailenviado) {
				return ResponseEntity.ok("mail enviado");
			}
			return ResponseEntity.notFound().build();
			
		}
		
		@GetMapping("/traersuscalificaciones/{iduser}")
		public ResponseEntity<?> traersuscalificaciones(@PathVariable Integer iduser){
			//solo uso el mail de ese json
			List<CalificacionesVista> califica =usercontrolador.traersuscalificaciones(iduser);
			
			return ResponseEntity.ok(califica);
		
		
			
		}

}

package com.example.demo.rest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.controlador.RecetasControlador;
import com.example.demo.entidades.Calificaciones;
import com.example.demo.entidades.Ingrediente;
import com.example.demo.entidades.Recetas;
import com.example.demo.entidades.Usuarios;
import com.example.demo.repositorio.IngredienteRepo;
import com.example.demo.vistas.CalificacionesVista;
import com.example.demo.vistas.CrearReceta;
import com.example.demo.vistas.ListaRecetasVista;
import com.example.demo.vistas.RecetaMultiplicadaVista;
import com.example.demo.vistas.RecetasVista;

@RestController
@RequestMapping("/recetas")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS })
public class RecetasRest {
	
	
	@Autowired
	private RecetasControlador recetacontrolador;
	
	
	@RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    public void handleOptions() {
        // No es necesario implementar ningún código aquí, solo se necesita el mapeo
    }
	
	//CREAR una receta!!!!!!!!!!
	//deberia pasarle el id del user creador de la receta!!!
	//por ahora la crea sin user
	@PostMapping("/")
	public ResponseEntity<?> postReceta(@RequestBody Recetas receta){
		RecetasVista creada=recetacontrolador.crearRecetaVista(receta);
		if(creada!=null) {
			return ResponseEntity.ok(creada);
		}
		else {
			return ResponseEntity.notFound().build();	
		}
	}
	
	//crear una receta CON usuario
	@PostMapping("/{iduser}")
	public ResponseEntity<?> postRecetaConUsuario(@PathVariable(value="iduser") String iduser,@RequestBody CrearReceta receta){
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
	public ResponseEntity<?> getAllrecetasAprobadas(){
		List<RecetasVista> lista= recetacontrolador.traerRecetasAprobadas();
		return ResponseEntity.ok(lista);
	}
	

	
	@PostMapping("/addfotounica/{idreceta}")
	public ResponseEntity<?> agregarfotoUNICA(@PathVariable(value = "idreceta") String idreceta,
			@RequestBody MultipartFile file) {
		System.out.println("cargar foto UNICA a receta");
		System.out.println("idreceta " + idreceta);
		try {
			System.out.println("  agregando foto unica");
			String url = recetacontrolador.agregarFotoUNICA(Integer.parseInt(idreceta), file);
			if (!url.isEmpty()) {
				System.out.println("no esta vacia");
				return ResponseEntity.ok(url);
			}

			System.out.println("url vacia");
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			System.out.println("    CATCH");
			return ResponseEntity.notFound().build();
		}
		
	}
	
	// traer TODAS las recetas (aprobadas o no)!!!
	@GetMapping("/todas")
	public ResponseEntity<?> getAllrecetas() {
		//quilombo con jackson
		List<RecetasVista> lista = recetacontrolador.traerRecetas();
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
	public ResponseEntity<?> getRecetaById(@PathVariable(value="idreceta") String id){
		//te devuelve una recetavistacompleta!!!
		RecetasVista buscada= recetacontrolador.busacarUnaRecetaVista(Integer.parseInt(id));
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
		RecetasVista buscada= recetacontrolador.busacarUnaRecetaPorNombre(nombre, Integer.parseInt(id));
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
	
	/*
	@GetMapping("/recetasordenalfabetico")
	public ResponseEntity<?> ordenarPorNombreAsc(){
		System.out.println("compararndo");
		List<Recetas> listarecetas= recetacontrolador.ordenarPorNombreAsc();
		return ResponseEntity.ok(listarecetas);
	}
	*/

	/*
	@GetMapping("/recetaspornombreusuario")
	public ResponseEntity<?> ordenarPorNombreUsuarioAsc(){
		System.out.println("compararndo");
		List<RecetasVista> listarecetas= recetacontrolador.ordenarRVPorNombreUsuarioAsc();
		return ResponseEntity.ok(listarecetas);
	}
	
	*/
	//funciona, las devuelve en orden descendente.osea de fechas mas reciente a mas antigua
	@GetMapping("/recetasporfechacreacion")
	public ResponseEntity<?> ordenarPorFechaCreacion(){
		System.out.println("ordenadno recetas por fechacreacion");
		List<RecetasVista> listarecetasvista= recetacontrolador.ordenarRVPorFechaCreacion();
		return ResponseEntity.ok(listarecetasvista);
	}
	
	//metodo para buscar las ultimas tres recetas mas nuevas de la bbdd
	
	@PostMapping("/calificar")
	public ResponseEntity<?> calificarreceta(@RequestBody CalificacionesVista entidad){
		System.out.println("     antes de calificar");
		try {
			System.out.println("antes de calificar");
			CalificacionesVista encontrada =recetacontrolador.calificarreceta(entidad);
			if(encontrada!=null) {
				return ResponseEntity.ok(encontrada);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(" CATCH");
			System.out.println(e.getMessage());
		}
		
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/mostrarsuscalificaciones/{idreceta}")
	public ResponseEntity<?> buscarcalificacionesdeunareceta(@PathVariable Integer idreceta){
		List<CalificacionesVista> califica = recetacontrolador.devolversuscalificaiones(idreceta);

		return ResponseEntity.ok(califica);
	}
	
	/*
	@PostMapping("addfoto/{idreceta}")
	public ResponseEntity<?> addfotorecetaexistente(@PathVariable Integer idreceta){
		recetacontrolador.addFoto();
		/*
		 * 
		 * HACER ESTO
	}
*/
	/*FILTRAR RECETAS POR TIPO */
	@GetMapping("filtrarportipo/CONTIENEN/{idtipo}")
	public ResponseEntity<?> filtrarrecetasportipo(@PathVariable Integer idtipo){
		List<RecetasVista>lista=recetacontrolador.contienenestetipo(idtipo);
		return ResponseEntity.ok(lista);
		
	}
	
	@GetMapping("filtrarportipo/NO-CONTIENEN/{idtipo}")
	public ResponseEntity<?> filtrarrecetasportipoNO(@PathVariable Integer idtipo){
		//recetas que NO tengan este tipo
		List<RecetasVista>lista=recetacontrolador.NOcontienenestetipo(idtipo);
		return ResponseEntity.ok(lista);
		
	}

	// AGREGARUNAFOTO A UNA RECETA
	// agregar foto a una receta existente
	// agregar un multimedia REAL a un paso existente y receta exisxtente
	@PostMapping("/addFOTOREAL/{idreceta}")
	public ResponseEntity<?> agregarfotoREAL(@PathVariable(value = "idreceta") String idreceta,
			@RequestBody MultipartFile file) {
		System.out.println("cargar foto real");
		System.out.println("idreceta " + idreceta);
		try {
			System.out.println("  agrgando foto");
			String url = recetacontrolador.agregarFotoREAL(Integer.parseInt(idreceta), file);
			if (!url.isEmpty()) {
				System.out.println("no esta vacia");
				return ResponseEntity.ok(url);
			}

			System.out.println("url vacia");
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			System.out.println("    CATCH");
			return ResponseEntity.notFound().build();
		}

	}
	
	//GET DE UN MULTIMEDIA REAL
	@GetMapping("/getFOTOreal/{rutaparcial}")
	public ResponseEntity<?> getIMagenREALporRuta(@PathVariable String rutaparcial) {
		// me da la ruta total
		String folderfotos=".//src//main//resources//frontend//administracion//src//fotos//";
		try {
			Resource resource = recetacontrolador.getFOTOREALporRuta(rutaparcial);
			if (resource != null) {
				Path path = Paths.get(folderfotos+rutaparcial);

				// headers
				// org.springframework.http.HttpHeaders header=new
				// org.springframework.http.HttpHeaders();

				return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(path)))
						.body(resource);
			}

			return ResponseEntity.notFound().build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}

	}
	
	//agregar UNA RECETA a LALISTA de un usuario
		//a la lista de recetas a intentar
	@PostMapping("/agregarrecetaaintentar/{iduser}/{idreceta}")
	public ResponseEntity<?> agregarrecetaaintentar(@PathVariable Integer iduser,
			@PathVariable Integer idreceta){
		System.out.println("iduser "+iduser + "idreceta "+idreceta);
		boolean agregada=recetacontrolador.agregarrecetaaintentar(iduser,idreceta);
		if(agregada) {
			return ResponseEntity.ok("receta agregada a la lista del usuario");
		}
		return ResponseEntity.notFound().build();
		
	}
	
	//GETlistaRecetasAINTENTARdeunuser
	@GetMapping("/getrecetastaaintentar/{iduser}")
	public ResponseEntity<?> agregarrecetaaintentar(@PathVariable Integer iduser){
		System.out.println("iduser "+iduser);
		ListaRecetasVista agregada=recetacontrolador.getrecetaaintentar(iduser);
		if(agregada!=null) {
			return ResponseEntity.ok(agregada);
		}
		return ResponseEntity.notFound().build();
		
	}
	
	//multiplicarunareceta
	@GetMapping("/multiplicar/{idreceta}/{factor}")
	public ResponseEntity<?> multiplicarReceta(@PathVariable Integer idreceta,
			@PathVariable Integer factor){
		//multiplica la receta por el factor*cantidadpersonas
		
		RecetaMultiplicadaVista agregada=recetacontrolador.multiplicarReceta(idreceta,factor);
		
		if(agregada!=null) {
			return ResponseEntity.ok(agregada);
		}
		return ResponseEntity.notFound().build();
		
	}
	
	//GETRecetaQUEnoContengaTAlIngrdiente
	@GetMapping("/buscarCONingrediente/{idingrediente}")
	public ResponseEntity<?> buscarCONingrediente(@PathVariable Integer idingrediente){
		List<RecetasVista> buscada=recetacontrolador.buscarCONingrediente(idingrediente);
		if(buscada==null) {
			ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(buscada);
		
	}
	
	//GETRecetaQUEnoContengaTAlIngrdiente
	@GetMapping("/buscarSINingrediente/{idingrediente}")
	public ResponseEntity<?> buscarSINingrediente(@PathVariable Integer idingrediente){
		List<RecetasVista> buscada=recetacontrolador.buscarSINingrediente(idingrediente);
		if(buscada==null) {
			ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(buscada);
		
	}
	
	@GetMapping("/getallingredientes")
	public ResponseEntity<?> getallingredientes(){
		List<Ingrediente> ingredientes=recetacontrolador.getallingredientes();
		return ResponseEntity.ok(ingredientes);
	}
	
	//ver si el alias esta en uso y ademas sugerir alias
	
}
	


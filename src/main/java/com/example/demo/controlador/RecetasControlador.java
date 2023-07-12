package com.example.demo.controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.comparadores.CompararRecetasPorFecha;
import com.example.demo.comparadores.CompararRecetasPorNombre;
import com.example.demo.comparadores.CompararRecetasPorNombreUsuario;
import com.example.demo.entidades.Calificaciones;
import com.example.demo.entidades.Foto;
import com.example.demo.entidades.Ingrediente;
import com.example.demo.entidades.ListaRecetas;
import com.example.demo.entidades.Multimedia;
import com.example.demo.entidades.Pasos;
import com.example.demo.entidades.Recetas;
import com.example.demo.entidades.Tipo;
import com.example.demo.entidades.Usuarios;
import com.example.demo.entidades.Utilizado;
import com.example.demo.repositorio.CalificacionesRepo;
import com.example.demo.repositorio.FotoRepo;
import com.example.demo.repositorio.IngredienteRepo;
import com.example.demo.repositorio.TipoRepo;
import com.example.demo.service.RecetasService;
import com.example.demo.service.UploadFileService;
import com.example.demo.service.UsuarioService;
import com.example.demo.vistas.CalificacionesVista;
import com.example.demo.vistas.CrearReceta;
import com.example.demo.vistas.ListaRecetasVista;
import com.example.demo.vistas.RecetaMultiplicadaVista;
import com.example.demo.vistas.RecetasVista;
import com.example.demo.vistas.UtilizadoVista;

//toda la logica de las recetas
@Component
public class RecetasControlador {
	
	@Autowired
	private RecetasService recetasservice;
	
	@Autowired
	private UsuarioService usuarioservice;
	
	@Autowired
	private CalificacionesRepo calirepo;
	
	@Autowired
	private UploadFileService uploadService;
	
	/*
	@Autowired
	private UsuarioService userservice;
	*/
	
	@Autowired
	private FotoRepo fotorepo;
	
	@Autowired
	private IngredienteRepo ingrepo;
	
	@Autowired
	private TipoRepo tiporepo;
	
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
	
	
	public RecetasVista crearRecetaVista(Recetas receta) {
		// si pasa las validaciones TODO
		//todo
		if(!receta.getNombre().isEmpty()) {
			//paso las validaciones
			Recetas esta=recetasservice.save(receta);
			return esta.toView(esta);
		}
		return null;
		
	}

	public List<RecetasVista> traerRecetas() {
		// devuelve todas las recetas de la bbbdd
		List<Recetas> recetas=recetasservice.findAll();
		List<RecetasVista> vistas= new ArrayList<RecetasVista>();
		for(Recetas r:recetas) {
			vistas.add(r.toView(r));
		}
		
		return vistas;
		
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
	
	public List<RecetasVista> traerRecetasvistadeunuser(int id) {
		// buscar todas las recetas de un user
		List<RecetasVista> adevolver=new ArrayList<RecetasVista>();
		
		//se fija si el user existe
		Usuarios user=usuariocontrolador.BuscarUser(id);
		if(user!=null) {
			List<Recetas> lista= user.getRecetas();
			for(Recetas r:lista) {
				adevolver.add(r.toView(r));
			}
			return adevolver;
		}
		return null;
		
	}

	public RecetasVista busacarUnaRecetaVista(int id) {
		// TODO Auto-generated method stub
		RecetasVista encontrada=null;
		Optional<Recetas> buscada= recetasservice.findById(id);
		if(buscada.isPresent()) {
			Recetas receta=buscada.get();
			encontrada=receta.toView(receta);
		}
		
		
		return encontrada;
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

	public List<RecetasVista> traerRecetasAprobadas() {
		// devuelve todas las recetas APROBADAS de la bbbdd
		List<Recetas> lista= recetasservice.findAll();
		List<RecetasVista> listadeaprobadas=new ArrayList<>();
		for(Recetas r:lista) {
			if(r.getAprobada()) {
				listadeaprobadas.add(r.toView(r));
			}
		}
		
		return listadeaprobadas;
	}

	public RecetasVista busacarUnaRecetaPorNombredereceta(String nombre, Integer iduser) {
		// la receta DEBE pertenecer al usuario
		RecetasVista buscada=null;
		Usuarios userbuscado=usuariocontrolador.BuscarUser(iduser);
		System.out.println("el user es "+userbuscado.getMail());
		
		if (userbuscado!=null) {
			List<Recetas> susrecetas= userbuscado.getRecetas();
			//System.out.println(susrecetas.get(0).getNombre());
			if(!susrecetas.isEmpty()) {
				//recorre y busca la receta por nombre
				for(Recetas r:susrecetas ) {
					System.out.println("comparando");
					System.out.println(r.getNombre()+ "y ");
					System.out.println(                   nombre);
					if(r.getNombre().equalsIgnoreCase(nombre)) {
						
						
						buscada=r.toView(r);
						break;
					}
				}
			}
		}
		else {
			System.out.println("no tiene recetas");
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
		//solo el admin puede!!!
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

	public Recetas crearRecetaConUser(int iduser, Recetas receta) {
		// TODO Auto-generated method stub
		Recetas creada=null;
		//el user debe existir
		Usuarios user = usuariocontrolador.BuscarUser(iduser);
		if(user!=null) {
			user.addReceta(receta);//va y viene
			//usuarioservice.save(user);
			//que pasa si hago doble save??
			creada= recetasservice.save(receta);
			
			
		}
		return creada;
	}
	
	public Recetas crearRecetaConUser(int iduser, CrearReceta crearreceta) {
		// TODO Auto-generated method stub
		Integer idtipo=crearreceta.getIdtipo();
		Optional<Tipo> tipo=tiporepo.findById(idtipo);
		if(!tipo.isPresent()) {
			System.out.println(" no existe ese tipo en la bbdd");
			return null;
		}
		Tipo tipoencontrado=tipo.get();
		//el user debe existir
		Usuarios user = usuariocontrolador.BuscarUser(iduser);
		if(user!=null) {
			Recetas receta=new Recetas();
			receta.setNombre(	crearreceta.getNombre());
			receta.setDescripcion(crearreceta.getDescripcion());
		    receta.setPorciones(crearreceta.getPorciones());
			receta.setCantidadPersonas(crearreceta.getCantidadPersonas());
			receta.setFotounica(crearreceta.getFotounica());
			receta.setIdTipo(tipoencontrado);
			user.addReceta(receta);//va y viene
			//usuarioservice.save(user);
			//que pasa si hago doble save??
			Recetas nueva= recetasservice.save(receta);
			return nueva;
			
		}
		System.out.println(" no existe el user");
		return null;
	}

	public List<RecetasVista> ordenarRVPorFechaCreacion() {
		//RVP:RECETAVISTA
		List<RecetasVista> lista= recetasservice.ordenarRVPorFechaCreacion();
		return lista;
	}


	public CalificacionesVista calificarreceta(CalificacionesVista entidad) {
		// TODO Auto-generated method stub
		System.out.println(entidad.getComentarios());
		Integer idreceta=entidad.getIdreceta();
		Integer calificacion=entidad.getCalificacion();
		String comentarios=entidad.getComentarios();
		Integer iduser=entidad.getIdusuario();
		System.out.println("busacnado receta ");
		//busco a la receta y al user
		Optional<Recetas> receta=recetasservice.findById(idreceta);
		if(receta.isPresent()) {
			Recetas encontrada=receta.get();
			System.out.println(" econtre la receta");
			Optional<Usuarios> user=usuarioservice.findById(iduser);
			if(user.isPresent()) {
				System.out.println(" encontre al usuario");
				Usuarios userencontrado=user.get();
				//creo la calificaion
				Calificaciones calificaion=new Calificaciones();
				calificaion.setCalificacion(calificacion);
				calificaion.setComentarios(comentarios);
				//va y viene
				calificaion.setReceta(encontrada);
				//solo va
				calificaion.setUsuario(userencontrado);
				
				//hago el save
				calirepo.save(calificaion);
				return calificaion.toView(calificaion);
				
			}	else {
				System.out.println(  "no existe el user");
			}
		}
		else {
			System.out.println(" no existe la receta");
		}
		
		
		
		return null;
	}


	public List<CalificacionesVista> devolversuscalificaiones(Integer idreceta) {
		// devuelve todas las calificaiones de una receta
		List<CalificacionesVista> adevolver=new ArrayList<CalificacionesVista>();
		Optional<Recetas> receta=recetasservice.findById(idreceta);
		if(receta.isPresent()) {
			List<Calificaciones> calificaciones=receta.get().getCalificaciones();
			for(Calificaciones c:calificaciones) {
				adevolver.add(c.toView(c));
			}
		}
		return adevolver;
	}


	public List<RecetasVista> contienenestetipo(String descripcion) {
		// TODO Auto-generated method stub
		List<RecetasVista> adevolver=new ArrayList<RecetasVista>();
		//busco el tipo
		List<Tipo> tipos=tiporepo.findByDescripcion(descripcion);
		if(tipos.isEmpty()) {
			System.out.println(" no exsite ese tipo");
			return adevolver;
		}
		
		List<Recetas> recetas=recetasservice.findAll();
		for(Recetas r:recetas) {
			Tipo tiporeceta=r.getIdTipo();
			if(tiporeceta.getDescripcion().equals(descripcion) && r.getAprobada()) {
				adevolver.add(r.toView(r));
			}
		}
		return adevolver;
	}


	public List<RecetasVista> NOcontienenestetipo(String descripcion) {
		List<RecetasVista> adevolver=new ArrayList<RecetasVista>();
		List<Recetas> recetas=recetasservice.findrecetasvistaaprobadas();
		
		for(Recetas r:recetas) {
			Tipo tiporeceta=r.getIdTipo();
			if(!tiporeceta.getDescripcion().equals(descripcion)) {
				adevolver.add(r.toView(r));
			}
		}
		return adevolver;
	}


	public String agregarFotoREAL(int idreceta, MultipartFile file) {
		// se fija si existe la receta
		Optional<Recetas> buscada=recetasservice.findById(idreceta);
		if(buscada.isPresent()) {
			System.out.println("la receta existe");
			//le quiero agregar la foto
			System.out.println("cargar FOTO real");
			try {
				System.out.println("  agrgando multimedia"); 
				String url =this.ADDFOTOREAL(buscada.get(), file);
				return url;
			}catch (Exception e) {
				System.out.println(" CATCH");
				System.out.println(e.getMessage());
			}
		}
		return "";
	}


	public String ADDFOTOREAL(Recetas receta, MultipartFile file) throws IOException {
			// TODO Auto-generated method stub
			//boolean agregado=false;
			String url="";
			
			System.out.println("busco la receta");
		
			if(receta!=null) {
				System.out.println("guardadno");
				url = uploadService.saveFOTO(file);
				System.out.println("la url es "+url);
				
				Foto foto =new Foto();
				foto.setExtension(file.getContentType());
				foto.setIdReceta(receta); //va y viene
				foto.setUrlFoto(url);
				
				//hago el save de la foto
				fotorepo.save(foto);
				
				
			
				//y hago el save del paso
				//pasoservice.save(buscado);
				return "foto agregada";
			}else {
				System.out.println("no encontre la receta");
			}
			return "";
					
	
	}


	public Resource getFOTOREALporRuta(String rutaparcial) throws IOException {
		Resource resource= uploadService.getFOTO(rutaparcial);
		return resource;
		
	
	}


	public boolean agregarrecetaaintentar(Integer iduser, Integer idreceta) {
		// se fija que existan la receta y el usuario
		
		Optional<Recetas> buscada=recetasservice.findById(idreceta);
		if(buscada.isPresent()) {
			Recetas encontrada=buscada.get();
			Optional<Usuarios> user=usuarioservice.findById(iduser);
			if(user.isPresent()) {
				Usuarios encontrado=user.get();
				
				boolean yalatiene=comprobarsiyalatiene(encontrado, encontrada);
				if(yalatiene) {
					System.out.println("ya la tiene en su lista");
					return true;
				}
				
				encontrado.getRecetasAintentar().ADDrecetaAintentar(encontrada);
				System.out.println("agrega la receta a la lista de ese usuario");
				//hago el save
				usuarioservice.save(encontrado);
				return true;
			}else {
				System.out.println(" no existe ese user");
			}
		}
		else {
			System.out.println(" no existe la receta");
		}
		return false;
	}


	private boolean comprobarsiyalatiene(Usuarios encontrado, Recetas encontrada) {
		ListaRecetas lista=encontrado.getRecetasAintentar();
		boolean yalatiene=false;
		List<Recetas> recetas=lista.getRecetas();
		for(Recetas r:recetas) {
			if(r.getIdReceta().equals(encontrada.getIdReceta())) {
				System.out.println("                 YA LA TIENE" );
				yalatiene=true;
			}
		}
		return yalatiene;
	}


	public ListaRecetasVista getrecetaaintentar(Integer iduser) {
		//
		Optional<Usuarios> user=usuarioservice.findById(iduser);
		if(user.isPresent()) {
			Usuarios encontrado=user.get();
			ListaRecetas listarecetas= encontrado.getRecetasAintentar();
			ListaRecetasVista recetasvista=listarecetas.toView(listarecetas);
			return recetasvista;
		}
		return null;
	}


	public RecetaMultiplicadaVista multiplicarReceta(Integer idreceta, Integer factor) {
		// TODO Auto-generated method stub
		Optional<Recetas> rece=recetasservice.findById(idreceta);
		if(rece.isPresent()) {
			Recetas esta=rece.get();
			Recetas multiplicada=esta.multiplicar(esta,factor);
			//no la gaurada, solo la devielve multiplicada
			System.out.println("la receta ya fue multiplicada");
			//la convierto en recetavista
			return multiplicada.toRecetaMultiplicadaVista(multiplicada);
		}
		return null;
	}


	public List<RecetasVista> buscarCONingrediente(Integer idingrediente) {

		List<RecetasVista> adevolver=new ArrayList<>();
		
		//se fija si el ingrediente existe
		Optional<Ingrediente> ing=ingrepo.findById(idingrediente);
		if(ing.isPresent()) {
			List<Recetas> recetasaprobadas=getRecetasAprobadas();
			//List<RecetasVista> aprobadas=traerRecetasAprobadas();
			List<Recetas> aprobadasCONeseingrediente=new ArrayList<>();
			for(Recetas r:recetasaprobadas) {
				List<Utilizado> utilizados=r.getUtilizados();
				for(Utilizado u:utilizados) {
					if(u.getIdIngrediente().getIdIngrediente().equals(idingrediente)) {
						//esta receta tiene ese ingrediente
						aprobadasCONeseingrediente.add(r);
					}
					
				}
			}
			//las paso a recetavista
			for(Recetas rc:aprobadasCONeseingrediente) {
				adevolver.add(rc.toView(rc));
			}
		}else {
			System.out.println("no existe ese ingrediente");
			return null;
		}
		return adevolver;
		
		
	}

	public List<Recetas> getRecetasAprobadas() {
		// devuelve todas las recetas APROBADAS de la bbbdd
		List<Recetas> lista= recetasservice.findAll();
		List<Recetas> aprobadas=new ArrayList<>();
		for(Recetas r:lista) {
			if(r.getAprobada()) {
				aprobadas.add(r);
			}
		}
		
		return aprobadas;
	}


	public List<RecetasVista> buscarSINingrediente(Integer idingrediente) {
		// TODO Auto-generated method stub
		List<RecetasVista> adevolver=new ArrayList<>();
		
		//se fija si el ingrediente existe
		Optional<Ingrediente> ing=ingrepo.findById(idingrediente);
		if(ing.isPresent()) {
			List<Recetas> recetasaprobadas=getRecetasAprobadas();
			//List<RecetasVista> aprobadas=traerRecetasAprobadas();
			List<Recetas> aprobadasSINeseingrediente=new ArrayList<>();
			for(Recetas r:recetasaprobadas) {
				List<Utilizado> utili=r.getUtilizados();
				boolean locotiene=false;
				for(Utilizado u:utili) {
					if(u.getIdIngrediente().getIdIngrediente().equals(idingrediente)) {
						//esta receta contiene ese ing
						locotiene=true;
					}
				}
				if(locotiene==false) {
					adevolver.add(r.toView(r));
				}
			}
			
		}else {
			System.out.println(" no existe ese ingrediente");
			return null;
		}
		return adevolver;
	}


	public List<Ingrediente> getallingredientes() {
		// trae todos los ingredientes de la bbdd

		return ingrepo.findAll();
	}


	public String agregarFotoUNICA(int idreceta, MultipartFile file) {
		// TODO Auto-generated method stub
		Optional<Recetas> buscada=recetasservice.findById(idreceta);
		if(buscada.isPresent()) {
			System.out.println("la receta existe");
			//le quiero agregar la foto
			System.out.println("cargar FOTO unica");
			try {
				System.out.println("  agrgando multimedia"); 
				//AQUI!!!
				
				//String url =this.ADDFOTOREAL(buscada.get(), file);
				String url =this.ADDFOTOUNICA(buscada.get(), file);
				return url;
			}catch (Exception e) {
				System.out.println(" CATCH");
				System.out.println(e.getMessage());
			}
		}
		return "";
	}


	private String ADDFOTOUNICA(Recetas receta, MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		String url="";
		
		System.out.println("busco la receta");
	
		if(receta!=null) {
			System.out.println("guardadno");
			url = uploadService.saveFOTO(file);
			System.out.println("la url es "+url);
			
			receta.setFotounica(url);
			
			recetasservice.save(receta);
			return "foto agregada";
		}else {
			System.out.println("no encontre la receta");
		}
		return "";
	}


	public boolean quitarrecetaaintentar(Integer iduser, Integer idreceta) {
		// se fija que existan la receta y el usuario
		
		Optional<Recetas> buscada=recetasservice.findById(idreceta);
		if(buscada.isPresent()) {
			Recetas encontrada=buscada.get();
			Optional<Usuarios> user=usuarioservice.findById(iduser);
			if(user.isPresent()) {
				Usuarios encontrado=user.get();
				System.out.println("QUITAR la receta a la lista de ese usuario");
				removerrecetadelista(encontrado,encontrada);
				//encontrado.getRecetasAintentar().REMOVErecetaAintentar(encontrada);
				
				//hago el save
				System.out.println("save del user xq removi receta");
				usuarioservice.save(encontrado);
				System.out.println("fin");
				return true;
			}else {
				System.out.println(" no existe ese user");
			}
		}
		else {
			System.out.println(" no existe la receta");
		}
		return false;
	}


	private void removerrecetadelista(Usuarios encontrado, Recetas encontrada) {
		// TODO Auto-generated method stub
		ListaRecetas listarecetas= encontrado.getRecetasAintentar();
		listarecetas.REMOVErecetaAintentar(encontrada);
		encontrado.setRecetasAintentar(listarecetas);
		System.out.println("  removida");
		
	}


	public List<RecetasVista> recetaspornickusuario(String nombreuser) {
		// buscar todas las recetas de un user
		List<RecetasVista> adevolver=new ArrayList<RecetasVista>();
		//se fija si el user existe
		Usuarios user=usuarioservice.findByNickName(nombreuser);
		if(user!=null) {
			List<Recetas> lista= user.getRecetas();
			//devuelvo solo las aprobadas
			for(Recetas r: lista) {
				if(r.getAprobada()) {
					adevolver.add(r.toView(r));
				}
			}
			System.out.println("     antes de devolver");
			return adevolver;
		}
		return null;
		
	}


	public List<RecetasVista> buscarCONingrediente(String nombreingrediente) {
		// TODO Auto-generated method stub
		List<Ingrediente> lista=ingrepo.findByNombre(nombreingrediente);
		if(lista.isEmpty()) {
			//el ingrediente no existe
			return new ArrayList<RecetasVista>();
		}
		return buscarCONingrediente(lista.get(0).getIdIngrediente());
	}


	public List<RecetasVista> buscarSINingrediente(String nombreingrediente) {
		// TODO Auto-generated method stub
		List<Ingrediente> lista=ingrepo.findByNombre(nombreingrediente);
		if(lista.isEmpty()) {
			//el ingrediente no existe
			//devuelvo todas las recetas aprobadas
			return traerRecetasAprobadas();
		}
		//el ingrediente existe
		return buscarSINingrediente(lista.get(0).getIdIngrediente());
	}
}


	/*
	public List<Recetas> ordenarPorNombreAsc() {
		// TODO Auto-generated method stub
		List<Recetas> recetas =traerRecetas();
		// devuelve todas las recetas de la bbbdd
		if(!recetas.isEmpty()) {
			//comparar y ordenar
			
			Collections.sort(recetas, new CompararRecetasPorNombre());
			
		}
		return recetas;
	}
	
	public List<Recetas> ordenarPorNombreUsuarioAsc() {
		// TODO Auto-generated method stub
		List<Recetas> recetas =traerRecetas();
		// devuelve todas las recetas de la bbbdd
		if(!recetas.isEmpty()) {
			//comparar y ordenar
			
			Collections.sort(recetas, new CompararRecetasPorNombreUsuario());
			
		}
		return recetas;
	}

	public List<Recetas> ordenarPorFechaCreacion() {
		// TODO Auto-generated method stub
		List<Recetas> recetas =traerRecetas();
		// devuelve todas las recetas de la bbbdd
		if(!recetas.isEmpty()) {
			//comparar y ordenar
			
			Collections.sort(recetas, new CompararRecetasPorFecha());
			
		}
		return recetas;
	}
	
	/*
	//RV:recetasVista
	public List<RecetasVista> ordenarRVPorFechaCreacion() {
		// TODO Auto-generated method stub
		List<RecetasVista>recetasvista=new ArrayList<RecetasVista>();
		List<Recetas> recetas =traerRecetas();
		// devuelve todas las recetas de la bbbdd
		if(!recetas.isEmpty()) {
			//comparar y ordenar
			
			Collections.sort(recetas, new CompararRecetasPorFecha());
			for(Recetas r:recetas) {
				recetasvista.add(r.crearRecetaVista(r));
			}
			
		}
		return recetasvista;
	}
	//RV:recetasVista
	public List<RecetasVista> ordenarRVPorNombreUsuarioAsc() {
		List<RecetasVista>recetasvista=new ArrayList<RecetasVista>();
		List<Recetas> recetas =traerRecetas();
		// devuelve todas las recetas de la bbbdd
		if(!recetas.isEmpty()) {
			//comparar y ordenar
			
			Collections.sort(recetas, new CompararRecetasPorNombreUsuario());
			for(Recetas r:recetas) {
				recetasvista.add(r.crearRecetaVista(r));
			}
			
		}
		return recetasvista;
	}

	*/

	



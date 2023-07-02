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
import com.example.demo.entidades.ListaRecetas;
import com.example.demo.entidades.Multimedia;
import com.example.demo.entidades.Pasos;
import com.example.demo.entidades.Recetas;
import com.example.demo.entidades.Tipo;
import com.example.demo.entidades.Usuarios;
import com.example.demo.repositorio.CalificacionesRepo;
import com.example.demo.repositorio.FotoRepo;
import com.example.demo.service.RecetasService;
import com.example.demo.service.UploadFileService;
import com.example.demo.service.UsuarioService;
import com.example.demo.vistas.CalificacionesVista;
import com.example.demo.vistas.ListaRecetasVista;
import com.example.demo.vistas.RecetasVista;

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

	public RecetasVista busacarUnaRecetaPorNombre(String nombre, Integer iduser) {
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


	public List<RecetasVista> contienenestetipo(Integer idtipo) {
		// TODO Auto-generated method stub
		
		List<RecetasVista> adevolver=new ArrayList<RecetasVista>();
		List<Recetas> recetas=recetasservice.findAll();
		for(Recetas r:recetas) {
			Tipo tiporeceta=r.getIdTipo();
			if(tiporeceta.getIdTipo().equals(idtipo)) {
				adevolver.add(r.toView(r));
			}
		}
		return adevolver;
	}


	public List<RecetasVista> NOcontienenestetipo(Integer idtipo) {
		List<RecetasVista> adevolver=new ArrayList<RecetasVista>();
		List<Recetas> recetas=recetasservice.findAll();
		for(Recetas r:recetas) {
			Tipo tiporeceta=r.getIdTipo();
			Integer idtiporeceta=tiporeceta.getIdTipo();
			if(!tiporeceta.getIdTipo().equals(idtipo)) {
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
}
	



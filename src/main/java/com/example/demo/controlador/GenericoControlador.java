package com.example.demo.controlador;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Properties;


import java.util.logging.Level;
import java.util.logging.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entidades.Conversiones;
import com.example.demo.entidades.Ingrediente;
import com.example.demo.entidades.Multimedia;
import com.example.demo.entidades.Pasos;
import com.example.demo.entidades.Recetas;
import com.example.demo.entidades.Tipo;
import com.example.demo.entidades.Unidades;
import com.example.demo.entidades.Usuarios;
import com.example.demo.entidades.Utilizado;
import com.example.demo.repositorio.ConversionesRepo;
import com.example.demo.repositorio.UnidadRepo;
import com.example.demo.repositorio.UtilizadoRepo;
import com.example.demo.service.EmailSenderService;
import com.example.demo.service.IngredienteService;
import com.example.demo.service.MultimediaService;
import com.example.demo.service.PasoService;
import com.example.demo.service.RecetasService;
import com.example.demo.service.TipoService;
import com.example.demo.service.UsuarioService;
import com.example.demo.vistas.UtilizadoconingredienteexistenteDTO;

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
	
	@Autowired
	private UtilizadoRepo utilizadorepo;
	
	
	/*pasos*/
	@Autowired
	private PasoService pasoservice;
	
	@Autowired
	private EmailSenderService emailsender;
	
	@Autowired
	private UnidadRepo unidadrepo;
	
	@Autowired
	private ConversionesRepo conversionrepo;
	
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
					nuevaReceta.setAprobada(true);
					//le agrego al receta al user y el user a la receta
					user.addReceta(nuevaReceta);   
					
					
	//tengo que hace save de ambos???????RTA:siiiii
					//usuarioservice.save(user);
					Integer numerorandom = (int) (Math.random() * 50 + 1);
					if(numerorandom<12) {
						nuevaReceta.setFechaCreacion(LocalDate.of(1972, Month.MAY, 23));
					}else if(numerorandom<24) {
						nuevaReceta.setFechaCreacion(LocalDate.of(1985, Month.APRIL, 18));
					}else {
						nuevaReceta.setFechaCreacion(LocalDate.of(1992, Month.JANUARY, 05));
					}
					
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
		
		ing=new Ingrediente();
		ing.setNombre("leche");
		ingredienteservice.save(ing);
		
		ing=new Ingrediente();
		ing.setNombre("arroz");
		ingredienteservice.save(ing);
		
		ing=new Ingrediente();
		ing.setNombre("harina");
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

	public void crearUnidadesYconversiones() {
		//gramos
		 
		
		Unidades gramos=new Unidades();
		gramos.setIdUnidad(1);
		gramos.setDescripcion("gramos");
		unidadrepo.save(gramos);
		
		Unidades kilos=new Unidades();
		kilos.setIdUnidad(2);
		kilos.setDescripcion("kilos");
		unidadrepo.save(kilos);
		
		Unidades litros=new Unidades();
		litros.setIdUnidad(3);
		litros.setDescripcion("litros");
		unidadrepo.save(litros);
		
		Unidades mllitros=new Unidades();
		mllitros.setIdUnidad(4);
		mllitros.setDescripcion("mililitros");
		unidadrepo.save(mllitros);
		
		Unidades pieza=new Unidades();
		pieza.setIdUnidad(5);
		pieza.setDescripcion("pieza/unidad");
		unidadrepo.save(pieza);
		
		//creo conversiones
		
	//de gramos a kilos
		Conversiones gramosakilos=new Conversiones();
		gramosakilos.setIdconversion(1);
		gramosakilos.setUnidadorigen(gramos);
		gramosakilos.setUnidaddestino(kilos);
		gramosakilos.setFactorConversiones(1000.0);
		conversionrepo.save(gramosakilos);
		
		
		Conversiones kilosagramos=new Conversiones();
		kilosagramos.setIdconversion(2);
		kilosagramos.setUnidadorigen(kilos);
		kilosagramos.setUnidaddestino(gramos);
		kilosagramos.setFactorConversiones(0.001);
		conversionrepo.save(kilosagramos);
		
		Conversiones litrosaml=new Conversiones();
		litrosaml.setIdconversion(3);
		litrosaml.setUnidadorigen(litros);
		litrosaml.setUnidaddestino(mllitros);
		litrosaml.setFactorConversiones(1000.0);
		conversionrepo.save(litrosaml);
		
		Conversiones mlalitros=new Conversiones();
		mlalitros.setIdconversion(4);
		mlalitros.setUnidadorigen(mllitros);
		mlalitros.setUnidaddestino(litros);
		mlalitros.setFactorConversiones(0.001);
		conversionrepo.save(mlalitros);
		
		
		
	}

	public boolean agregarutilizadoarecetaexistente(UtilizadoconingredienteexistenteDTO entidad) {
		 Recetas modificada=null;
		 Integer idreceta=null;
		//si no existe debe recibir un -1 como idingrdiente
		 Integer idingrediente=null;
		 Integer cantidad=0;
		 Integer idunidad=null;
		 String observacion="no recibi este patametro";
		 
		 idreceta=entidad.getIdreceta();
		 //puede ser -1
		 idingrediente=entidad.getIdingrediente();
		 cantidad=entidad.getCantidad();
		 idunidad=entidad.getIdunidad();
		 observacion=entidad.getObservacion();
		 
		 //se fija que la receta existe,
		 //se fija que la unidad existe
		 //si ambos exsiten
		 //si el iddelingrdiente. si es -1 lo debe crear
		 		//si no es -1, lo debe traer
		 Recetas recetaencontrada;
		 Optional<Recetas> guardada=recetasservice.findById(idreceta);
		 if(guardada.isPresent()) {
			 System.out.println(" la receta existe");
			 recetaencontrada=guardada.get();
			 	//ya tengo la receta
			 Unidades unidadencontrada;
			 Optional<Unidades> unidadguardad=unidadrepo.findById(idunidad);
			 if(unidadguardad.isPresent()) {
				 System.out.println(" la unidad existe");
				 unidadencontrada=unidadguardad.get();
				 	//ya tengo la unidad
				 
				//creo el utilizado
					Utilizado utilizado=new Utilizado();
					utilizado.setCantidad(cantidad);
					utilizado.setObservaciones(observacion);
					utilizado.setIdReceta(recetaencontrada);
					utilizado.setIdUnidad(unidadencontrada);
					//falta setear el ingrediente!!!
					//me fijo si existe
				 if(!idingrediente.equals(-1)) {
					System.out.println(" el ingrdiente debe existir"); 
					
					Ingrediente ingencontrado;
					Optional<Ingrediente> ingredienteguardado=ingredienteservice.findById(idingrediente);
					if(ingredienteguardado.isPresent()) {
						System.out.println(" encontre el ingrediente");
						ingencontrado=ingredienteguardado.get();
						utilizado.setIdIngrediente(ingencontrado);
					}else {
						System.out.println(" mme dijeron que el ing existia pero no es verdad");
					}
				 }else {
					 System.out.println(" debo crear el ingrediente");
					 Ingrediente nuevoing=new Ingrediente();
					 nuevoing.setNombre(entidad.getNombreingrediente());
					
					 //hago el save del ingrediente??
					 		//NOO
					 nuevoing=ingredienteservice.save(nuevoing);
					 utilizado.setIdIngrediente(nuevoing);
					 
				 }
				 //hago el save y retorno
				 	//se lo seteo a la receta
				 recetaencontrada.ADDutilizado(utilizado);
				 System.out.println("guardando el utilizado");
				 
		//hago el save sobre el utilizado
				 utilizadorepo.save(utilizado);
				 
				 return true;
				 
			 }else {
				 System.out.println(" no existe la unidad");
			 }
		 }else{
			 System.out.println(" no existe la receta");
		 }

		
		return false;
	}

	public void agregarutilizados() {
		// TODO Auto-generated method stub
		Integer contador=0;
		List<Recetas> recetas=recetasservice.findAll();
		List<Unidades> unidades=unidadrepo.findAll();
		List<Ingrediente> ingredientes=ingredienteservice.findAll();
		while(contador<3) {
			
			Collections.shuffle(recetas);
			Recetas receta=recetas.get(0);
			
			Collections.shuffle(unidades);
			Unidades unidad=unidades.get(0);
			
			Collections.shuffle(ingredientes);
			Ingrediente ing=ingredientes.get(0);
			System.out.println(ing.getNombre()+ " nombre ing");
			
			Utilizado utilizado=new Utilizado();
			utilizado.setCantidad(3);
			utilizado.setObservaciones("esto es una observacio");
			utilizado.setIdReceta(receta);
			utilizado.setIdUnidad(unidad);
			utilizado.setIdIngrediente(ing);
			receta.ADDutilizado(utilizado);
			
			utilizadorepo.save(utilizado);
			
			contador=contador+1;
			
		}
		System.out.println(" termine de agregar algunos utlizados");
		
	}

	public boolean agregarlistadeutilizadosexistentes(List<UtilizadoconingredienteexistenteDTO> utilizados) {
		Recetas modificada=null;
		 Integer idreceta=null;
		//si no existe debe recibir un -1 como idingrdiente
		 Integer idingrediente=null;
		 Integer cantidad=0;
		 Integer idunidad=5;
		 String observacion="no recibi este parametro";
		 boolean sepuedecrear=true;//!!!!
		 
		 for(UtilizadoconingredienteexistenteDTO entidad: utilizados) {
			 if(sepuedecrear==false) {
				 break;
			 }
			 idreceta=entidad.getIdreceta();
			 //puede ser -1
			 idingrediente=entidad.getIdingrediente();
			 cantidad=entidad.getCantidad();
			 idunidad=entidad.getIdunidad();
			 observacion=entidad.getObservacion();
			 Recetas recetaencontrada;
			 Optional<Recetas> guardada=recetasservice.findById(idreceta);
			 if(guardada.isPresent()) {
				 System.out.println(" la receta existe");
				 recetaencontrada=guardada.get();
				 	//ya tengo la receta
				 Unidades unidadencontrada;
				 Optional<Unidades> unidadguardad=unidadrepo.findById(idunidad);
				 if(unidadguardad.isPresent()) {
					 System.out.println(" la unidad existe");
					 unidadencontrada=unidadguardad.get();
					 	//ya tengo la unidad
					 
					
						Ingrediente ingencontrado;
						Optional<Ingrediente> ingredienteguardado=ingredienteservice.findById(idingrediente);
						if(ingredienteguardado.isPresent()) {
							System.out.println(" encontre el ingrediente");
							ingencontrado=ingredienteguardado.get();
							
						}else {
							System.out.println("el ingrediente no existe");
							 sepuedecrear=false;
						}
						 
				 }//if
				 else {
					 System.out.println(" no existe la unidad");
					 sepuedecrear=false;
				 }
				 
				 
			 }//fin if
			 else {
				 System.out.println(" noexsite la receta");
				 sepuedecrear=false;
			 }
			 
		 }//fin for
		if( sepuedecrear) {
			System.out.println(" pase las validaciones, creo los utilizados uno por uno");
			for(UtilizadoconingredienteexistenteDTO entidad: utilizados) {
				idreceta=entidad.getIdreceta();
				 //puede ser -1
				 idingrediente=entidad.getIdingrediente();
				 cantidad=entidad.getCantidad();
				 idunidad=entidad.getIdunidad();
				 observacion=entidad.getObservacion();
				 Recetas recetaencontrada;
				 Optional<Recetas> guardada=recetasservice.findById(idreceta);
				 if(guardada.isPresent()) {
					 System.out.println(" la receta existe");
					 recetaencontrada=guardada.get();
					 	//ya tengo la receta
					 Unidades unidadencontrada;
					 Optional<Unidades> unidadguardad=unidadrepo.findById(idunidad);
					 if(unidadguardad.isPresent()) {
						 unidadencontrada=unidadguardad.get();
						 	//ya tengo la unidad
						 
						//creo el utilizado
							Utilizado utilizado=new Utilizado();
							utilizado.setCantidad(cantidad);
							utilizado.setObservaciones(observacion);
							utilizado.setIdReceta(recetaencontrada);
							utilizado.setIdUnidad(unidadencontrada);
							Ingrediente ingencontrado;
							Optional<Ingrediente> ingredienteguardado=ingredienteservice.findById(idingrediente);
							if(ingredienteguardado.isPresent()) {
								System.out.println(" encontre el ingrediente");
								ingencontrado=ingredienteguardado.get();
								utilizado.setIdIngrediente(ingencontrado);
								recetaencontrada.ADDutilizado(utilizado);
								 System.out.println("guardando el utilizado");
								 
								 //hago el save sobre el utilizado
								 utilizadorepo.save(utilizado);
							}
					 }
				 }
			}//fin for
			System.out.println(" se crearon los utilizados para esta receta");
			return true;
			
		}else {
			System.out.println(" no pase las validaciones");
			System.out.println(" error en los datos cargados");
			return false;
		}
		
		
		
	}

	public void agregarFotosARecetasMock() {
		// TODO Auto-generated method stub
		List<Recetas> recetas=recetasservice.findAll();
		List<String> url=new ArrayList<String>();
		url.add("pebete.jpg");
		url.add("pollo.jpg");
		url.add("ensalada.jpg");
		url.add("panqueque.jpg");
		int i=0;
		for(Recetas r: recetas) {
			System.out.println(" agregando foto a receta mock");
			r.setFotounica(url.get(i));
			i=i+1;
			recetasservice.save(r);
		}
		
		
	}	
	
	
}//fin

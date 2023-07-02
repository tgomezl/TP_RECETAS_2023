package com.example.demo.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entidades.Calificaciones;
import com.example.demo.entidades.Recetas;
import com.example.demo.entidades.Usuarios;
import com.example.demo.repositorio.CalificacionesRepo;
import com.example.demo.repositorio.UsuarioRepo;
import com.example.demo.service.EmailSenderService;
import com.example.demo.service.RecetasService;
import com.example.demo.service.UsuarioService;
import com.example.demo.vistas.CalificacionesVista;
import com.example.demo.vistas.MailYnickname;
import com.example.demo.vistas.UserConReceta;
import com.example.demo.vistas.UserLogin;
import com.example.demo.vistas.UsuarioConClaveDeRecu;
import com.example.demo.vistas.UsuarioVista;



//aca tendria que estar la logica
@Component
public class UsuarioControlador {
	
	//autowired al service
	//-------------------------------------------------------------------------------------------------
	@Autowired
	private UsuarioService userservice;
	
	@Autowired
	private EmailSenderService emailsender;
	
	@Autowired
	private RecetasService recetaservice;
	
	@Autowired
	private CalificacionesRepo calirepo;
	

	//crear
	public Usuarios crearUser(Usuarios user) {
		// TODO Auto-generated method stub
		Usuarios creado=null;
		System.out.println("datos recibidos ");
		System.out.println(user);
		
		/*valido los datos recibidos*/
		System.out.println("                   ");
		if(!user.getNombre().isBlank()) {
			creado=userservice.save(user);
		}
		return creado;
	}
	
	public UsuarioVista crearUserVista(Usuarios user) {
		// TODO Auto-generated method stub
		UsuarioVista creado=null;
		System.out.println("datos recibidos ");
		System.out.println(user);
		
		/*valido los datos recibidos*/
		System.out.println("                   ");
		if(!user.getNombre().isBlank()) {
			Usuarios encontrado=userservice.save(user);
			creado=encontrado.toView(encontrado);
		}
		return creado;
	}

	//buscar
	public Usuarios BuscarUser(int id) {
		
		Optional<Usuarios> buscado= userservice.findById(id);
		if(buscado.isPresent()) {
			// te devuelve un actor
			System.out.println("");
			System.out.println("");
			System.out.println("-------------lo encontre");
			System.out.println("");
			Usuarios user = buscado.get();
			return user;
		}
		return null;
	
	}
	
	public UsuarioVista BuscarUserVista(int id) {
		
		Optional<Usuarios> buscado= userservice.findById(id);
		if(buscado.isPresent()) {
			// te devuelve un actor
			System.out.println("");
			System.out.println("");
			System.out.println("-------------lo encontre");
			System.out.println("");
			Usuarios user = buscado.get();
			UsuarioVista uservista=user.toView(user);
			return uservista;
		}
		return null;
	
	}
	

	//traer todos
	public List<UserConReceta> TraerLista() {
		//te devuelve la USERCONRECETA
		System.out.println(" ");
		System.out.println("              buscando en traer lista");
		System.out.println("   ");
		List<Usuarios> lista=userservice.findAll();
		List<UserConReceta> listavistas=new  ArrayList<>();
		for(Usuarios u:lista) {
			listavistas.add(u.crearUserConReceta(u));
		}
		return listavistas;
	}
	
	
	public List<Usuarios> TraerListaUsers(){
		//te devulve usuarios
		return userservice.findAll();
	
	}
	
	public List<UsuarioVista> TraerListaUsersVista(){
		//te devulve usuarios
		List<Usuarios> ussers=userservice.findAll();
		List <UsuarioVista> vistas=new ArrayList<>();
		for(Usuarios u:ussers) {
			vistas.add(u.toView(u));
		}
		return vistas;
	
	}
	


	//buscar uno + borrar
	public Boolean borrarUser(int id) {
		// TODO Auto-generated method stub
		//USA THIS
		Usuarios buscado = this.BuscarUser(id);
		if (buscado==null){
			//no lo encontro
			return false;
		}
		//lo encontro
		
		List<Recetas> susrecetas=buscado.getRecetas();
		for(Recetas r:susrecetas) {
			r.setUsuario(null);
			System.out.println("hago el update");
			recetaservice.save(r);
		}
		buscado.setRecetas(null);
		userservice.deleteById(id);
		return true;
	}

	//busca y modifica
	public UsuarioVista updateUser(int id, UsuarioConClaveDeRecu user) {
		// lo busca y si lo encuentra lo modifica
		Usuarios userbuscado=this.BuscarUser(id);
		if (userbuscado==null){
			//no lo encontro
			System.out.println(" no existe ese usuario");
			return null;
		}else {
			//lo piso
			if(user.getClavederecu().equals(userbuscado.getClaveDeRecu())) {
				//si las claves coninciden, tiene permiso de modificar al usuario
				
				userbuscado.setNombre(user.getNombre());
				userbuscado.setAvatar(user.getAvatar());
				userbuscado.setContrasenia(user.getContrasenia());
		 
				
				userbuscado=userservice.save(userbuscado);
				return userbuscado.toView(userbuscado);
			}else {
				System.out.println("     la clavederecu recibida no coincide");
			}
			
			return null;
		}
	
	}

	public Usuarios crearUsersolomailyalias(Usuarios user) {
		Usuarios creado=null;
		System.out.println("datos recibidos ");
		System.out.println(user);
		String mail=user.getMail();
		String nickname=user.getNickname();
		//es una bandera
		boolean habilitado=true;
		if(mail.isBlank()) {
			return creado;
		}
		if(nickname.isBlank()) {
			return creado;
		}
		/*valido los datos recibidos (mail y nickname)*/
		List<Usuarios> listausers=TraerListaUsers();
		/*recorro y valido mail y nickname*/
		for(Usuarios u: listausers) {
			if(u.getMail().equalsIgnoreCase(mail) || u.getNickname().equalsIgnoreCase(nickname)) {
				//esos datos ya existen
				habilitado=false;
			}
		}
		System.out.println("                   ");
		if(habilitado) {
			//solo setea mail y nickname
			user.borrarDatosingreso();
			
			
			creado=userservice.save(user);
		}
		return creado;
	}
	
	public Usuarios crearUsersolomailyalias(MailYnickname user) {
		Usuarios creado=null;
		System.out.println("datos recibidos ");
		System.out.println(user);
		String mail=user.getMail();
		String nickname=user.getNickname();
		//es una bandera
		boolean habilitado=true;
		if(mail.isBlank()) {
			return creado;
		}
		if(nickname.isBlank()) {
			return creado;
		}
		/*valido los datos recibidos (mail y nickname)*/
		List<Usuarios> listausers=TraerListaUsers();
		/*recorro y valido mail y nickname*/
		for(Usuarios u: listausers) {
			if(u.getMail().equalsIgnoreCase(mail) || u.getNickname().equalsIgnoreCase(nickname)) {
				System.out.println(" estos datos ya existen en la bbdd");
				habilitado=false;
			}
		}
		
		if(habilitado) {
			//solo setea mail y nickname
			System.out.println("  creando user!!!");
			Usuarios usernuevo=new Usuarios();
			usernuevo.setMail(mail);
			usernuevo.setNickname(nickname);
			usernuevo.borrarDatosingreso();
			creado=userservice.save(usernuevo);
			

		}
		return creado;
	}
	
	
	public Integer generarClaveAleatoria() {
		//genera un int entre 
		Integer num_max=9999;
		Integer num_min=1000;
		Integer num = (Integer)(int) (Math.random()* (num_max + 1 - num_min)) + num_min;
		return num;
	}

	public boolean aprobaruser(int id) {
		boolean aprobado=false;
		Usuarios user=BuscarUser(id);
		if(user!=null) {
			user.setHabilitado("Si");
			//envio el mail
			Integer numero=generarClaveAleatoria();
			user.setClaveDeRecu(numero);
			String mail=user.getMail();
			//destinatario, String subject, String cuerpo
			emailsender.sendEmail(mail, "codigo para alta", "el codigo para continuar el alta es "+numero);
			//este numero LUEGO se envia por mail
			System.out.println("             ");
			System.out.println("enviado mail con codigo de alta");
			System.out.println("          la clave de alta es "+numero);
			
			//hago el save
			
			userservice.save(user);
			aprobado=true;
		}
		return aprobado;
	}

	public Usuarios terminaralta(int iduser, int codigo, Usuarios cuerpo) {
		// le carga al user los campos faltantes
		Integer codigorecibido=(Integer)codigo;
		Usuarios userbuscado=BuscarUser(iduser);
		if(userbuscado!=null) {
			//chquea el codigo recibido con el codigo de lla bbdd
			Integer clavedelabbdd=userbuscado.getClaveDeRecu();
			if(clavedelabbdd==null) {
				//todavia no la tiene seteada
				return null;
			}
			System.out.println("calvedelabbd "+clavedelabbdd);
			System.out.println("codigo recibido" +codigo);
			if(clavedelabbdd.equals(codigorecibido)) {
				//
				
				cuerpo.setMail(userbuscado.getMail());
				cuerpo.setNickname(cuerpo.getNickname());
				cuerpo.setEsadmin(false);
				cuerpo.setHabilitado("Si");
				cuerpo.setIdUsuario(iduser);
				cuerpo.setClaveDeRecu(clavedelabbdd);
				userbuscado=userservice.save(cuerpo);
				return userbuscado;
			}
		}
		return null;
	}

	public UsuarioVista login(UserLogin dto) {
		// recibe nickname y contrasenia
		
		String nickname =dto.getNickname();
		String contrarecibida=dto.getContrasenia();
		Integer id=null;
		Usuarios user =userservice.findByNickName(nickname);
		if(user!=null) {
			//chequeo que la contrasenia coincida
			if(user.getContrasenia().equals(contrarecibida)) {
				return user.toView(user);
			}else {
				System.out.println(   "las contrasenia no coinciden");
			}
			
		}
		System.out.println(" no existe ese user ");
		return null;
	}
	
	public UserConReceta BuscarUserConReceta(int id) {
		// TODO Auto-generated method stub
		
		UserConReceta usuariovista=null;
		
		Usuarios user=BuscarUser(id);
		
		if(user!=null) {
			usuariovista=user.crearUserConReceta(user);
		}
		
		return usuariovista;
		
	}

	public UsuarioVista terminaralta(UsuarioConClaveDeRecu cuerpo) {
		// le carga al user los campos faltantes
		Integer codigorecibido = (Integer) cuerpo.getClavederecu();
		
		//aca tendria que usar el mail para decirme que usuario es
		
		Usuarios userbuscado = userservice.findByMail(cuerpo.getMail());
		if (userbuscado != null) {
			// chquea el codigo recibido con el codigo de lla bbdd
			Integer clavedelabbdd = userbuscado.getClaveDeRecu();
			if (clavedelabbdd == null) {
				// todavia no la tiene seteada
				return null;
			}
			System.out.println("clavedelabbd " + clavedelabbdd);
			System.out.println("clave recibida" + cuerpo.getClavederecu());
			if (clavedelabbdd.equals(codigorecibido)) {
				//tiene permise de modificar los datos del usuario
				
				System.out.println("   pisando los datos del user");
				userbuscado.setNombre(cuerpo.getNombre());
				userbuscado.setAvatar(cuerpo.getAvatar());
				userbuscado.setTipo_usuario(cuerpo.getTipo_usuario());
				userbuscado.setContrasenia(cuerpo.getContrasenia());
				userbuscado.setHabilitado("Si");
				userbuscado = userservice.save(userbuscado);
				return userbuscado.toView(userbuscado);
			}
		}
		return null;
	}

	public boolean recuperarcontra(String mail) {
		//te envia tu calve de recu, con ella podes usar el metodo updateuser
		//busca al user usando el mail
		boolean mailenviado=false;
		Usuarios user=userservice.findByMail(mail);
		if(user!=null) {
			//envia por mail la clave de recuperacion
			Integer numero=user.getClaveDeRecu();
			emailsender.sendEmail(mail, "codigo para recuperar contrasenia", "el codigo es "+numero);
			mailenviado=true;
		}
		System.out.println("no existe un user con ese mail");
		return mailenviado;
	}

	public List<CalificacionesVista> traersuscalificaciones(Integer iduser) {
		Usuarios user=BuscarUser(iduser);
		List<CalificacionesVista> adevolver=new ArrayList<CalificacionesVista>();
		if(user!=null) {
			//recorro todas las calificaciones de la bbdd
			List<Calificaciones> lista=calirepo.findAll();
			adevolver=new ArrayList<CalificacionesVista>();
			for(Calificaciones c:lista) {
				if(c.getUsuario().equals(user)) {
					adevolver.add(c.toView(c));
				}
			}
		}
		return adevolver;
	}

	public List<String> comprobarnickname(String nick) {
		//crea una lista de alias sugeridos
		List<String> adevolver=new ArrayList<String>();
		Usuarios usuario=userservice.findByNickName(nick);
		if(usuario==null) {
			//esta disponible para usar
			adevolver.add("OK");
			adevolver.add("ok");
		}else {
			//esta en uso, sugerrir otro nick
			adevolver=sugerirnick(nick);
		}
		return adevolver;
	}

	public List<String> sugerirnick(String nick) {
		// TODO Auto-generated method stub
		List<String> posiblesnickname=new ArrayList<String>();

		posiblesnickname.add(nick + "02");
		posiblesnickname.add(nick + "03");
		posiblesnickname.add(nick + "04");
		posiblesnickname.add(nick + "05");
		posiblesnickname.add(nick.toUpperCase());
		posiblesnickname.add(nick.toUpperCase() + "02");
		posiblesnickname.add(nick.toUpperCase() + "03");
		posiblesnickname.add(nick.toUpperCase() + "04");
		
		List<String> nickchequeados=new ArrayList<String>();
		//comparar esa lista contra la bbdd
		for(String s:posiblesnickname) {
			Usuarios usuario=userservice.findByNickName(s);
			if(usuario==null) {
				//ese nick NO esta en uso
				nickchequeados.add(s);
			}
		}
		return nickchequeados;
	}

	
	
}

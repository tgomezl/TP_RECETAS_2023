package com.example.demo.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entidades.Recetas;
import com.example.demo.entidades.Usuarios;
import com.example.demo.service.EmailSenderService;
import com.example.demo.service.RecetasService;
import com.example.demo.service.UsuarioService;
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

	//traer todos
	public List<Usuarios> TraerLista() {
		//te devuelve la lista
		System.out.println(" ");
		System.out.println("              buscando en traer lista");
		System.out.println("   ");
		 List<Usuarios> listausuarios = (List<Usuarios>) userservice.findAll();
	     return listausuarios;
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
	public Usuarios updateUser(int id, Usuarios user) {
		// lo busca y si lo encuentra lo modifica
		Usuarios userbuscado=this.BuscarUser(id);
		if (userbuscado==null){
			//no lo encontro
			return null;
		}else {
			//lo piso
			
			Integer idbuscado =userbuscado.getIdUsuario();
			//ya no necesito recibirlo en el body
			user.setIdUsuario(idbuscado);
			
			userbuscado=userservice.save(user);
			return userbuscado;
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
		List<Usuarios> listausers=TraerLista();
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

	public Usuarios login(String nickname, String contrasenia) {
		// TODO 
		Integer id=null;
		List<Usuarios> usuarios=userservice.findAll();
		for(Usuarios u:usuarios) {
			if(u.getNickname().equals(nickname)) {
				id=u.getIdUsuario();
				break;
			}
		}
		if(id!=null) {
			Usuarios user=BuscarUser(id);
			if(user!=null) {
				user.setClaveDeRecu(null);
				return user;
			}
			
		}
		//recorro y busco el user con ese nick
		return null;
	}

	
	
}

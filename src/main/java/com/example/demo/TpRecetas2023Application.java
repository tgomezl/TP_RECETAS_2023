package com.example.demo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.controlador.GenericoControlador;
import com.example.demo.controlador.UsuarioControlador;
import com.example.demo.entidades.Usuarios;

/*
import com.example.demo.controlador.ActorControlador;
import com.example.demo.entidades.Pelicula;
import com.example.demo.repositorio.ActorRepo;
import com.example.demo.repositorio.MascotaRepo;
import com.example.demo.repositorio.PeliculaRepo;
*/


@SpringBootApplication
public class TpRecetas2023Application {

	public static void main(String[] args) {
		SpringApplication.run(TpRecetas2023Application.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(UsuarioControlador usuariocontrolador, GenericoControlador generico) {
			return args-> {
				
	
				
				
				System.out.println("");
				System.out.println("");
				System.out.println("             corriendo");
				System.out.println("");
				System.out.println("");
			
	/*cargo algunos usuarios pero solo si la bbdd esta vacia*/
				generico.cargarUsuarios();
				
				System.out.println("  **************");
				System.out.println("buscar todos los usuarios");
				/*recupero todos los usuarios*/
				List<Usuarios> milista = usuariocontrolador.TraerLista();
				for(Usuarios u:milista) {
					System.out.println(u);
				}
				
				
					
	/*busco un user por id*/
				System.out.println("  **************");
				System.out.println("buscando user por id");
				Usuarios userbuscado = usuariocontrolador.BuscarUser(6);
				System.out.println(userbuscado);
				
				
				/*cargo algunos ingredientes*/
				System.out.println("  ******TODO********");
				
				
	/*cargo algunos tipos de receta*/
				generico.crearTipos();
				
		/*cargo algunas recetas*/
				/*tiene que ser despues de haber creado algun user*/
				generico.crearAlgunaReceta();
			};
	}
	
	
	
}

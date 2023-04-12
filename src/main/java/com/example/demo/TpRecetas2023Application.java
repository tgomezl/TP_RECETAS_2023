package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
	CommandLineRunner commandLineRunner() {
			return args-> {
				
	
				
				
				System.out.println("");
				System.out.println("");
				System.out.println("             corriendo");
				System.out.println("");
				System.out.println("");
			};
	}
	/*
	@Bean
	CommandLineRunner commandLineRunner(ActorRepo actorrepo, ActorControlador actorcontrolador,MascotaRepo mascotarepo,PeliculaRepo pelicularepo) {
			return args-> {
				
				System.out.println("             corriendo");
				preCargarAlgunasPeliculas(pelicularepo);//crea peliculas sin actor
				preCargarAlgunosActores(actorrepo,actorcontrolador); //solo afecta a tabla actores
				
				
				Integer idactor=1;
				agrregarMascota(actorrepo,idactor,actorcontrolador);  //si no tiene--->le agrega una mascota
				idactor=1;
				agrregarMascota(actorrepo,idactor,actorcontrolador);//si no tiene--->le agrega una mascota
				System.out.println("tengo que hacer .save()----->SI???");
				agrregarMascota(actorrepo,idactor,actorcontrolador);//si no tiene--->le agrega una mascota
				
				
				Integer idpeli=1;
				
				agrregarPelicula(actorrepo,idactor,idpeli,pelicularepo);//si no tiene--->le agrega esa pelicula
				agrregarPelicula(actorrepo,idactor,idpeli,pelicularepo);//si no tiene--->le agrega esa pelicula
				
				//CAMBIO EL ACTOR
				idactor=2;
			
				agrregarPelicula(actorrepo,idactor,idpeli,pelicularepo); //si no tiene--->le agrega A LA MISMA pelicula
				agregarMascota(mascotarepo); //crea mascotas sin dueño
				
				System.out.println("");
				System.out.println("");
				System.out.println("             corriendo");
				System.out.println("");
				System.out.println("");
			};
	}
	
	*/
	/*
	private void preCargarAlgunasPeliculas(PeliculaRepo pelicularepo) {
		// TODO Auto-generated method stub
		Pelicula peliculasinactor=new Pelicula();
		peliculasinactor.setTitulo("la venganza de adrian");
		peliculasinactor.setCalificacion("sangriento");
		pelicularepo.save(peliculasinactor);
		
		
		peliculasinactor=new Pelicula();
		peliculasinactor.setTitulo("la naranja mecanica");
		peliculasinactor.setCalificacion("PG13");
		pelicularepo.save(peliculasinactor);
		
		peliculasinactor=new Pelicula();
		peliculasinactor.setTitulo("atrapame si peudes");
		peliculasinactor.setCalificacion("ATP");
		pelicularepo.save(peliculasinactor);
		
		peliculasinactor=new Pelicula();
		peliculasinactor.setTitulo("vacaiones en NY");
		peliculasinactor.setCalificacion("ATP");
		pelicularepo.save(peliculasinactor);
	}
	
	*/
}

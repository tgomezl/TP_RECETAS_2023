package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entidades.Multimedia;
import com.example.demo.entidades.Pruebas;
import com.example.demo.service.MultimediaService;
import com.example.demo.service.PruebaService;

@Component
public class MultimediaControlador {
	
	//autowired al service
		//-------------------------------------------------------------------------------------------------
		@Autowired
		private MultimediaService multimediaservice;

		public Multimedia CrearMultimedia(Multimedia multi) {
			//crear
				
				//si pasa las VALIDACIONES(		solo se pueden guardar mayores a 18)
				Multimedia creado=null;
				System.out.println(multi);
				if(!multi.getUrl().isBlank()) {
					//lo mando a guardar
					creado =multimediaservice.save(multi);
						
				}
				return creado;
					
			}

}

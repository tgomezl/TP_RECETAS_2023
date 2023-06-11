package com.example.demo.controlador;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entidades.Multimedia;
import com.example.demo.entidades.Pasos;
import com.example.demo.entidades.Recetas;
import com.example.demo.service.MultimediaService;
import com.example.demo.service.PasoService;
import com.example.demo.service.UploadFileService;


@Component
public class MultimediaControlador {

	// autowired al service
	// -------------------------------------------------------------------------------------------------
	@Autowired
	private MultimediaService multimediaservice;
	
	@Autowired
	private RecetasControlador recetacontrolador;
	
	@Autowired
	private PasoService pasoservice;
	
	@Autowired
	private UploadFileService uploadFileService;


	public Multimedia CrearMultimedia(Multimedia multi) {
		// crear

		// si pasa las VALIDACIONES
		Multimedia creado = null;
		System.out.println(multi);
		if (!multi.getUrlContenido().isBlank()) {
			// lo mando a guardar
			creado = multimediaservice.save(multi);

		}
		return creado;

	}


	public boolean agregarMultimedia(int idreceta, int nropaso, Multimedia multimedia) {
		boolean agregado=false;
		//me fijo siexiste la receta
		Recetas receta = recetacontrolador.busacarUnaReceta(idreceta);
		Integer nrop=(Integer)nropaso;
		Pasos buscado=null;
		if(receta!=null) {
			List<Pasos> pasos=receta.getPasos();
			//me fijo si existe el paso
			for(Pasos p:pasos) {
				if (p.getNroPaso().equals(nrop)) {
					//es este
					buscado=p;
					break;
				}
			}
			if(buscado!=null) {
				buscado.ADDmultimedia(multimedia); //va y viene
				multimediaservice.save(multimedia); //paso-1-----N-multimedia
				//y hago el save del paso
				//pasoservice.save(buscado);
				agregado=true;
			}
		}
		return agregado;
	}


	public List<Multimedia> getallmultimedia() {
		// TODO Auto-generated method stub
		return multimediaservice.findAll();
	}


	public boolean agregarMultimediaREAL(int idreceta, int nropaso, MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		boolean agregado=false;
		//me fijo siexiste la receta
		Recetas receta = recetacontrolador.busacarUnaReceta(idreceta);
		Integer nrop=(Integer)nropaso;
		Pasos buscado=null;
		if(receta!=null) {
			List<Pasos> pasos=receta.getPasos();
			//me fijo si existe el paso
			for(Pasos p:pasos) {
				if (p.getNroPaso().equals(nrop)) {
					//es este
					buscado=p;
					break;
				}
			}
			if(buscado!=null) {
		//aca hago el save!!!
				String url = uploadFileService.saveFile(file);
				
				//creo el multimedia usando el file
				Multimedia multimedia= new Multimedia();
				
				multimedia.setExtension(file.getContentType());
				multimedia.setUrlContenido(url);
				multimedia.setTipo_contenido("imagen");
				
				
				buscado.ADDmultimedia(multimedia); //va y viene
				multimediaservice.save(multimedia); //paso-1-----N-multimedia
				//y hago el save del paso
				//pasoservice.save(buscado);
				agregado=true;
			}
		}
		return agregado;
	}


	public boolean deleteImagen(int parseInt) {
		// TODO Auto-generated method stub
		return false;
	}


	public byte[] getIMagenREALporRuta(String ruta) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}

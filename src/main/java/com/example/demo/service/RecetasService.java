package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Recetas;
import com.example.demo.repositorio.RecetasRepo;
import com.example.demo.vistas.RecetasVista;

@Service
public class RecetasService implements BaseInterface<Recetas>{
	
	@Autowired
	private RecetasRepo recetasrepo;

	@Override
	public List<Recetas> findAll() {
		// TODO Auto-generated method stub
		return recetasrepo.findAll();
	}

	@Override
	public Optional<Recetas> findById(Integer id) {
		// TODO Auto-generated method stub
		return recetasrepo.findById(id);
	}

	@Override
	public Recetas save(Recetas entidad) {
		// TODO Auto-generated method stub
		return recetasrepo.save(entidad);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		recetasrepo.deleteById(id);
		
	}

	public List<RecetasVista> ordenarRVPorFechaCreacion() {
		// TODO Auto-generated method stub
		String campo="fechaCreacion";
		System.out.println("ordenando por fechA ");
		//traera primero las recetas mas nuevas (fecha de creacion ams reciente)
		List<Recetas> recetas=recetasrepo.findAll(Sort.by(Sort.Direction.DESC,campo));
		List<RecetasVista> vistas= new ArrayList<RecetasVista>();
		for(Recetas r:recetas) {
			vistas.add(r.toView(r));
		}
	
		return vistas;
	}
	
	public List<Recetas> findrecetasvistaaprobadas(){
		return recetasrepo.findByAprobada(true);
		
	}
	


}

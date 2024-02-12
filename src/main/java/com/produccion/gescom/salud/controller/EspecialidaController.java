package com.produccion.gescom.salud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.produccion.gescom.entity.EVigencia;
import com.produccion.gescom.salud.dto.ConsultorioDtoR;
import com.produccion.gescom.salud.dto.EspecialidaDtoR;
import com.produccion.gescom.salud.entity.Consultorio;
import com.produccion.gescom.salud.entity.Especialida;
import com.produccion.gescom.salud.services.EspecialidaService;

import jakarta.validation.Valid;

@Controller
@CrossOrigin
@RequestMapping ("/especialida")
public class EspecialidaController {
	@Autowired
	private EspecialidaService especialidaservice;
	
	@PostMapping("/lista")
	public ResponseEntity<?> ListaEspecialida(@Valid @RequestBody EspecialidaDtoR especialidaDtoR, BindingResult result) throws Exception {
		List<Especialida> especialidalista = especialidaservice.listaespecialida(especialidaDtoR.getIdsocieda());
		return ResponseEntity.ok(especialidalista);
	}
	
	@PostMapping("/nuevo")
	public ResponseEntity<?> NuevaEspecialida(@Valid @RequestBody EspecialidaDtoR especialidaDtoR, BindingResult result) throws Exception {
		Map<String, Object> response = new HashMap<>();
		
		Especialida especialidanew = new Especialida();
		especialidanew.setDescripcion(especialidaDtoR.getDescripcion());
		especialidanew.setIdsocieda(especialidaDtoR.getIdsocieda());
		especialidanew.setVigencia(especialidaDtoR.getVigencia().equals("A") ? EVigencia.A : EVigencia.I );
		especialidanew.setIdusuario(especialidaDtoR.getIdusuario());
		especialidanew.setAbrevia(especialidaDtoR.getAbrevia());
		especialidanew.setIdusuariom(0L);
		especialidanew.prePersist();
		
		try {
			especialidaservice.save(especialidanew);
		    response.put("mensaje", "Especialidad grabada con exito");
		} catch (Exception e) {
		      response.put("error", "Error al Grabar la Especialida : " + e.getMessage());
		      return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}    
		return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);
	}
	
	@PostMapping("/actualiza")
	public ResponseEntity<?> ActualizaEspecialida(@Valid @RequestBody EspecialidaDtoR especialidaDtoR, BindingResult result) throws Exception {
		Map<String, Object> response = new HashMap<>();
		
		Especialida especialida = especialidaservice.edita(especialidaDtoR.getIdespecialida());
		if (especialida == null){
			response.put("error", "No existe la Especialida");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}
		
		especialida.setDescripcion(especialidaDtoR.getDescripcion());
		especialida.setAbrevia(especialidaDtoR.getAbrevia());
		especialida.setVigencia(especialidaDtoR.getVigencia().equals("A") ? EVigencia.A : EVigencia.I );
		especialida.setIdusuariom(especialidaDtoR.getIdusuario());
		
		try {
			especialidaservice.save(especialida);
		    response.put("mensaje", "Especialidad grabada con exito");
		} catch (Exception e) {
		      response.put("Error", "Error al Grabar la Especialida : " + e.getMessage());
		      return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}    
		
		return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);
	}	
	
	
}

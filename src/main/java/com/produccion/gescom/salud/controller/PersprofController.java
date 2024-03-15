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

import com.produccion.gescom.entity.Persona;
import com.produccion.gescom.entity.Profesion;
import com.produccion.gescom.entity.Socieda;
import com.produccion.gescom.salud.dto.PersprofDto;
import com.produccion.gescom.salud.dto.PersprofDtoR;
import com.produccion.gescom.salud.dto.PersprofdatosDto;
import com.produccion.gescom.salud.entity.Persprof;
import com.produccion.gescom.salud.services.PersprofService;

import jakarta.validation.Valid;

@Controller
@CrossOrigin
@RequestMapping ("/persona-prof")
public class PersprofController {
	
	@Autowired
	private PersprofService persprofservice;
	
	@PostMapping("/lista")
	public ResponseEntity<?> ListaPersprof(@Valid @RequestBody PersprofDtoR persprofDtoR, BindingResult result) throws Exception {
		List<PersprofdatosDto> persproflista =  persprofservice.ListaPersprof( persprofDtoR.getIdsocieda() );
		return ResponseEntity.ok( persproflista ); 
	}

	@PostMapping("/nuevo")
	public ResponseEntity<?> NuevaPersona( @Valid @RequestBody PersprofDtoR persprofDtoR, BindingResult result) throws Exception {
		Map<String, Object> response = new HashMap<>();
		
		Persprof persprof 	= new Persprof(); 		
		
		Persona persona 	= new Persona();
		persona.setId( persprofDtoR.getIdpersona() );
		persprof.setIdpersona( persona );
		
		Profesion profesion = new Profesion();
		profesion.setId( persprofDtoR.getIdprofesion() );
		persprof.setIdprofesion( profesion );
		
		Socieda socieda = new Socieda();
		socieda.setId( persprofDtoR.getIdsocieda() );		
		persprof.setIdsocieda( socieda );
		
		persprof.setNrocolegio( persprofDtoR.getNrocolegio() );
		persprof.setRne( persprofDtoR.getRne() );
		persprof.setVigencia("A");		
		persprof.setIdusuario( persprofDtoR.getIdusuario() );
		persprof.setIdusuariom(0L);
		persprof.prePersist();
		
		
		try {
			persprofservice.save( persprof );
			response.put("mensaje", "Datos de historia grabada con exito");
			
		}catch (Exception e) {
		      response.put("error", "Error al Grabar Datos de la profesion de persona : " + e.getMessage());
		      return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}    
		return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);
	}
	
	@PostMapping("/consulta")
	public ResponseEntity<?> ConsultaxPersona( @RequestBody PersprofDtoR persprofDtoR )throws Exception {
		Map<String, Object> response = new HashMap<>();
		
		PersprofDto persprofcon = persprofservice.buscarxpersona(persprofDtoR.getIdpersona());
	
		if( persprofcon == null ) {
			response.put("error", "No existe la Profesión de la Persona");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(persprofcon);		
	}
	
	@PostMapping("/buscaproxpersona")
	public ResponseEntity<?> Consulta( @RequestBody PersprofDtoR persprofDtoR )throws Exception {
		Map<String, Object> response = new HashMap<>();
		
		PersprofDto persprofcon = persprofservice.consulta( persprofDtoR.getId() );
		
		if( persprofcon == null ) {
			response.put("error", "No existe la Profesión de la Persona");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(persprofcon);		
	}
	
	@PostMapping("/actualiza")
	public ResponseEntity<?> ActualizaPersprof(@Valid @RequestBody PersprofDtoR persprofDtoR, BindingResult result) throws Exception {
		
		Map<String, Object> response = new HashMap<>();
		
		Persprof persprof = persprofservice.edit( persprofDtoR.getId() );

		if ( persprof == null ){
			response.put("error", "No existe la Profesion de persona");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
			
		}
		
		Profesion profesion = new Profesion();
		profesion.setId( persprofDtoR.getIdprofesion() );
		persprof.setIdprofesion( profesion );
		persprof.setNrocolegio( persprofDtoR.getNrocolegio() );
		persprof.setRne( persprofDtoR.getRne() );
		persprof.setVigencia( persprofDtoR.getVigencia() );
		persprof.setIdusuariom( persprofDtoR.getIdusuario() );

		try {
			persprofservice.save( persprof );
		    response.put("mensaje", "Profesion persona actualizada con exito");
		} catch (Exception e) {
		      response.put("Error", "Error al Grabar la Profesion persona : " + e.getMessage());
		      return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}    
		
		return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);		
		
	}	
}
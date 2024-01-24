package com.produccion.gescom.controller;

import java.util.HashMap;
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
import com.produccion.gescom.dto.PersonaDtoR;
import com.produccion.gescom.entity.ETipoPersona;
import com.produccion.gescom.entity.Pais;
import com.produccion.gescom.entity.Persona;
import com.produccion.gescom.entity.Socieda;
import com.produccion.gescom.entity.TipoDocumento;
import com.produccion.gescom.services.PersonaService;

import jakarta.validation.Valid;

@Controller
@CrossOrigin
@RequestMapping ("/persona")
public class PersonaController {
	
	@Autowired
	private PersonaService personaservice;
	
	@PostMapping("/nuevapersona")
	public ResponseEntity<?> NuevaPersona( @Valid @RequestBody PersonaDtoR personaDtor, BindingResult result) throws Exception {
		Map<String, Object> response = new HashMap<>();
		
		Persona persona = new Persona();
		TipoDocumento tipodocumento = new TipoDocumento();
		Socieda socieda = new Socieda();
		Pais pais = new Pais();
		
		tipodocumento.setId( personaDtor.getTipoDocumento() );
		persona.setTipoDocumento( tipodocumento );
		socieda.setId( personaDtor.getIdsocieda() );		
		persona.setIdsocieda( socieda );
		pais.setId( personaDtor.getIdpais() );		
		persona.setIdpais( pais );
		persona.setTipopersona( personaDtor.getTipopersona().equals("N") ? ETipoPersona.N : ETipoPersona.J  );
		persona.setNumerodocumento( personaDtor.getNumerodocumento() );
		persona.setApellidomaterno( personaDtor.getApellidomaterno() );
		persona.setPrimernombre( personaDtor.getPrimernombre() );
		persona.setSegundonombre( personaDtor.getSegundonombre() );
		persona.setNombrelargo( personaDtor.getNombrelargo() );
		persona.setRazonsocial( personaDtor.getRazonsocial() );
		persona.setNomabreviado( personaDtor.getNomabreviado() );
		persona.setFecnacimiento( personaDtor.getFecnacimiento() );
		persona.setSexo( personaDtor.getSexo() );
		persona.setIdusuario( personaDtor.getIdusuario() );
		persona.setIdusuariom(0L);
		persona.prePersist();
		
		try {
			personaservice.save( persona );
		    response.put("mensaje", "Datos de persona grabada con exito");
		} catch (Exception e) {
		      response.put("error", "Error al Grabar Datos de Persona : " + e.getMessage());
		      return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}    
		return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);		
		
	}
	
}


package com.produccion.gescom.controller;

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
import com.produccion.gescom.dto.PersonaDtoR;
import com.produccion.gescom.dto.PersonaMultipleDto;
import com.produccion.gescom.dto.PersonaDto;
import com.produccion.gescom.entity.EEstadoCivil;
import com.produccion.gescom.entity.ETipoPersona;
import com.produccion.gescom.entity.EVigencia;
import com.produccion.gescom.entity.Pais;
import com.produccion.gescom.entity.Persona;
import com.produccion.gescom.entity.Socieda;
import com.produccion.gescom.entity.TipoDocumento;
import com.produccion.gescom.services.PersonaService;

import jakarta.validation.Valid;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
@Controller
@CrossOrigin
@RequestMapping ("/persona")
public class PersonaController {
//	private static final Log logger = LogFactory.getLog(PersonaController.class);
	
	@Autowired
	private PersonaService personaservice;
	
	@PostMapping("/lista")
	public ResponseEntity<?> ListaPersona(@Valid @RequestBody PersonaDtoR personaDtoR, BindingResult result) throws Exception {
		
		String buscarpor = personaDtoR.getBuscarPor();
		String buscartext = personaDtoR.getBuscarText();
		
		List<PersonaDto> personalista = personaservice.ListaPersona( buscarpor, buscartext );
		return ResponseEntity.ok( personalista );
	}
	
	@PostMapping("/listamultiple")
	public ResponseEntity<?> ListaPersonaMultiple(@Valid @RequestBody PersonaDtoR personaDtoR, BindingResult result) throws Exception {
		
		List<PersonaMultipleDto> personalistamultiple = personaservice.ListaPersonaMultiple(personaDtoR.getBuscarText(), personaDtoR.getBuscarPor(), personaDtoR.getIdsocieda());

		return ResponseEntity.ok( personalistamultiple );
	}

	@PostMapping("/nuevo")
	public ResponseEntity<?> NuevaPersona( @Valid @RequestBody PersonaDtoR personaDtor, BindingResult result) throws Exception {
		Map<String, Object> response = new HashMap<>();
		
		PersonaDto personabusxdoc = personaservice.consultanuevoxdoc( personaDtor.getTipoDocumento(), personaDtor.getNumerodocumento(), personaDtor.getIdsocieda() );
		if (personabusxdoc != null){
			response.put("error", "La Persona ya se encuentra registrada");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}	
		
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
		persona.setApellidopaterno( personaDtor.getApellidopaterno() );
		persona.setApellidomaterno( personaDtor.getApellidomaterno() );
		persona.setPrimernombre( personaDtor.getPrimernombre() );
		persona.setSegundonombre( personaDtor.getSegundonombre() );
		String segundoNombre = persona.getSegundonombre() != null ? persona.getSegundonombre() : "";
		persona.setNombrelargo( persona.getApellidopaterno() + " " + persona.getApellidomaterno()+ " " +persona.getPrimernombre()+ " "+ segundoNombre);
		persona.setRazonsocial( personaDtor.getRazonsocial() );
		persona.setNomabreviado( personaDtor.getNomabreviado() );
		persona.setFecnacimi( personaDtor.getFecnacimiento() );
		persona.setSexo( personaDtor.getSexo() );
		persona.setVigencia(personaDtor.getVigencia().equals("A") ? EVigencia.A : EVigencia.I);
		persona.setEstadocivil(personaDtor.getEstadocivil().equals("S") ? EEstadoCivil.S : personaDtor.getEstadocivil().equals("C") ? EEstadoCivil.C : personaDtor.getEstadocivil().equals("V") ? EEstadoCivil.V : EEstadoCivil.D);
		persona.setLugarnacimi(personaDtor.getLugarnacimi());
		persona.setTelefono(personaDtor.getTelefono());
		persona.setEmail(personaDtor.getEmail());
		persona.setDomicilio(personaDtor.getDomicilio());
		persona.setCodubigeo(personaDtor.getCodubigeo());
		persona.setTutor(personaDtor.getTutor());
		persona.setObservacion(personaDtor.getObservacion());
		
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
		//return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);		
		return ResponseEntity.ok( persona );
	}
	
	@PostMapping("/consulta")
	public ResponseEntity<?> ConsultaPersona( @RequestBody PersonaDtoR personaDtoR )throws Exception {		
		Map<String, Object> response = new HashMap<>();		
		PersonaDto personacon = personaservice.consulta( personaDtoR.getId() ); 
		if (personacon == null){
			response.put("error", "No existe la Persona");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(personacon);
	}
	
	@PostMapping("/actualiza")
	public ResponseEntity<?> ActualizaPersona(@Valid @RequestBody PersonaDtoR personaDtoR, BindingResult result) throws Exception {
		
		Map<String, Object> response = new HashMap<>();
		
		Persona persona = personaservice.edit( personaDtoR.getId() );
		if (persona == null){
			response.put("error", "No existe la Persona");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.NO_CONTENT);
			
		}
		
		PersonaDto personabusxdoc = personaservice.consultaeditaxdoc( personaDtoR.getTipoDocumento(), personaDtoR.getNumerodocumento(), personaDtoR.getIdsocieda(), personaDtoR.getId());
		if (personabusxdoc != null){
			response.put("error", "El Tipo y Número de Documento ya se encuentra Registrado en otra Persona");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.NO_CONTENT);
		}	
		
		TipoDocumento tipoDocumento = new TipoDocumento();
	    tipoDocumento.setId( personaDtoR.getTipoDocumento() );
	    persona.setTipoDocumento( tipoDocumento );
	    Pais pais = new Pais();
	    pais.setId( personaDtoR.getIdpais() );
	    persona.setIdpais( pais );
	    
	    persona.setTipopersona( personaDtoR.getTipopersona().equals("N") ? ETipoPersona.N : ETipoPersona.J  );	    
	    persona.setNumerodocumento( personaDtoR.getNumerodocumento() );
	    persona.setApellidopaterno( personaDtoR.getApellidopaterno() );
	    persona.setApellidopaterno( personaDtoR.getApellidopaterno() );
	    persona.setPrimernombre( personaDtoR.getPrimernombre() );
	    persona.setSegundonombre( personaDtoR.getSegundonombre() );
		String segundoNombre = persona.getSegundonombre() != null ? persona.getSegundonombre() : "";
		persona.setNombrelargo( persona.getApellidopaterno() + " " + persona.getApellidomaterno()+ " " +persona.getPrimernombre()+ " "+ segundoNombre);
	    persona.setRazonsocial( personaDtoR.getRazonsocial() );
	    persona.setNomabreviado( personaDtoR.getNomabreviado() );	    
	    persona.setFecnacimi( personaDtoR.getFecnacimiento() );
		persona.setEstadocivil(personaDtoR.getEstadocivil().equals("S") ? EEstadoCivil.S : personaDtoR.getEstadocivil().equals("C") ? EEstadoCivil.C : personaDtoR.getEstadocivil().equals("V") ? EEstadoCivil.V : EEstadoCivil.D);
		persona.setLugarnacimi(personaDtoR.getLugarnacimi());
		persona.setTelefono(personaDtoR.getTelefono());
		persona.setEmail(personaDtoR.getEmail());
		persona.setDomicilio(personaDtoR.getDomicilio());
		persona.setCodubigeo(personaDtoR.getCodubigeo());
		persona.setTutor(personaDtoR.getTutor());
	    persona.setSexo( personaDtoR.getSexo() );
	    persona.setObservacion(personaDtoR.getObservacion());
	    persona.setVigencia(personaDtoR.getVigencia().equals("A") ? EVigencia.A : EVigencia.I);
	    persona.setIdusuariom(personaDtoR.getIdusuario());
	    
		try {
			personaservice.save( persona );
		    response.put("mensaje", "Persona actualizada con exito");
		} catch (Exception e) {
		      response.put("Error", "Error al Grabar la Persona : " + e.getMessage());
		      return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}    
		
		return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);
	    
	}
}
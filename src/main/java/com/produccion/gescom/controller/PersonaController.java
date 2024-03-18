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
import com.produccion.gescom.dto.PersonamDto;
import com.produccion.gescom.dto.PersonaDto;
import com.produccion.gescom.entity.EEstadoCivil;
import com.produccion.gescom.entity.ETipoPersona;
import com.produccion.gescom.entity.EVigencia;
import com.produccion.gescom.entity.Pais;
import com.produccion.gescom.entity.Persona;
import com.produccion.gescom.entity.Profesion;
import com.produccion.gescom.entity.Socieda;
import com.produccion.gescom.entity.TipoDocumento;
import com.produccion.gescom.salud.entity.Persprof;
import com.produccion.gescom.salud.services.PersprofService;
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
	
	@Autowired
	private PersprofService persprofservice;
	
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
			//response.put("resultado", "La Persona ya se encuentra registrada");
			
			response.put("resultado", 0);
			response.put("mensaje", "El Tipo y Número de Documento ya se encuentra registrado");
			response.put("dato","");
			
			return ResponseEntity.ok(response);
			//return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
			//return new ResponseEntity<Map<String,Object>>(response , );
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
			personaservice.save(persona);
			
			if (personaDtor.getTipoper().equals("ME"))
			{
				Persprof persprof = new Persprof();
				persprof.setIdpersona( persona );
				
				Profesion profesion = new Profesion();
				profesion.setId( 1L );
				persprof.setIdprofesion(profesion);
				
				Socieda sociedapro = new Socieda();
				sociedapro.setId( personaDtor.getIdsocieda() );		
				persprof.setIdsocieda( sociedapro );
				
				persprof.setNrocolegio( personaDtor.getNrocolegio() );
				persprof.setRne( personaDtor.getRne() );
				persprof.setVigencia("A");		
				persprof.setIdusuario( personaDtor.getIdusuario() );
				persprof.setIdusuariom(0L);
				persprof.prePersist();
				
				persprofservice.save( persprof );
			}
			
		    response.put("mensaje",personaDtor.getTipoper().equals("PE") ? "Datos de Persona grabada con exito" : "Datos de Médico grabada con exito");
		} catch (Exception e) {
		      //response.put("error",personaDtor.getTipoper().equals("PE") ? "Error al Grabar Datos de Persona : " + e.getMessage() : "Error al Grabar Datos del Médico : " + e.getMessage());
			  response.put("resultado", 0);
			  response.put("mensaje", "personaDtor.getTipoper().equals(\"PE\") ? \"Error al Grabar Datos de Persona : \" + e.getMessage() : \"Error al Grabar Datos del Médico : \" + e.getMessage()");
			  response.put("dato","");
		      //return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		      return ResponseEntity.ok(response);
		} 
		
		response.put("resultado", 1);
		response.put("mensaje", "Datos de Persona grabada con exito");
		response.put("dato",persona);
		
		//return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);		
		//return ResponseEntity.ok( persona );
		return ResponseEntity.ok(response);
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
	
	@PostMapping("/consultam")
	public ResponseEntity<?> ConsultaPersonam( @RequestBody PersonaDtoR personaDtoR )throws Exception {		
		Map<String, Object> response = new HashMap<>();		
		PersonamDto personacon = personaservice.consultam( personaDtoR.getId() ); 
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
			//response.put("error", "No existe la Persona");
			response.put("resultado", 0);
			response.put("mensaje", "No existe la Persona");
			response.put("dato","");
			
			return ResponseEntity.ok(response);
			//return new ResponseEntity<Map<String,Object>>(response , HttpStatus.NO_CONTENT);
			
		}
		
		PersonaDto personabusxdoc = personaservice.consultaeditaxdoc( personaDtoR.getTipoDocumento(), personaDtoR.getNumerodocumento(), personaDtoR.getIdsocieda(), personaDtoR.getId());
		if (personabusxdoc != null){
			//response.put("error", "El Tipo y Número de Documento ya se encuentra Registrado en otra Persona");

			response.put("resultado", 0);
			response.put("mensaje", "El Tipo y Número de Documento ya se encuentra registrado");
			response.put("dato","");
			
			return ResponseEntity.ok(response);
			//return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
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
	    persona.setApellidomaterno(personaDtoR.getApellidomaterno());	    
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
			
			if (personaDtoR.getTipoper().equals("ME"))
			{
				
				Persprof persprof = persprofservice.edit(personaDtoR.getIdpersprof());

				if (persprof == null){
					Persprof persprofnew = new Persprof();
					
					persprofnew.setIdpersona( persona );
					Profesion profesion = new Profesion();
					persprofnew.setId( 1L );
					persprofnew.setIdprofesion(profesion);
					
					Socieda sociedapro = new Socieda();
					sociedapro.setId( personaDtoR.getIdsocieda() );		
					persprofnew.setIdsocieda( sociedapro );
					
					persprofnew.setNrocolegio( personaDtoR.getNrocolegio() );
					persprofnew.setRne( personaDtoR.getRne() );
					persprofnew.setVigencia("A");		
					persprofnew.setIdusuario( personaDtoR.getIdusuario() );
					persprofnew.setIdusuariom(0L);
					persprofnew.prePersist();
					
					persprofservice.save( persprofnew );
				}
				else {
					persprof.setNrocolegio( personaDtoR.getNrocolegio() );
					persprof.setRne( personaDtoR.getRne() );
					persprof.setIdusuariom( personaDtoR.getIdusuario() );
					
					persprofservice.save( persprof );					
				}
			}
		    
			//response.put("mensaje", personaDtoR.getTipoper().equals("PE") ? "Persona actualizada con exito" : "Médico actualizado con exito");
		    
		    response.put("resultado", 1);
			response.put("mensaje", personaDtoR.getTipoper().equals("PE") ? "Persona actualizada con exito" : "Médico actualizado con exito");
			response.put("dato",persona);
		    
		    
		} catch (Exception e) {
		      //response.put("Error", "Error al Grabar la Persona : " + e.getMessage());
		      
		      response.put("resultado", 0);
			  response.put("mensaje", "Error al Grabar la Persona : " + e.getMessage());
			  response.put("dato","");
			  
			  return ResponseEntity.ok(response);
		      //return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		}    
		
		//return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);
		return ResponseEntity.ok(response);
	    
	}
}
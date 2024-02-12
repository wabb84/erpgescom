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

import com.produccion.gescom.commons.DatosFecha;
import com.produccion.gescom.commons.DatosVarios;
import com.produccion.gescom.entity.Socieda;
import com.produccion.gescom.salud.services.AgendaService;
import com.produccion.gescom.salud.dto.AgendaDto;
import com.produccion.gescom.salud.dto.AgendaDtoR;
import com.produccion.gescom.salud.dto.AgendaGeneraDtoR;
import com.produccion.gescom.salud.dto.AgendaObtDto;
import com.produccion.gescom.salud.entity.Agenda;
import com.produccion.gescom.salud.entity.Consultorio;
import com.produccion.gescom.salud.entity.Turnos;
import com.produccion.gescom.salud.entity.Persprof;

import jakarta.validation.Valid;


@Controller
@CrossOrigin
@RequestMapping ("/agenda")
public class AgendaController {
	
	@Autowired
	private AgendaService agendaservice;
	
	@Autowired
	private DatosFecha datosfecha;
	
	@Autowired
	private DatosVarios datosvarios;
	

	private String ANIOACTUAL; 
	private String MESACTUAL; 	
	
	@PostMapping("/nuevo")
	public ResponseEntity<?> NuevaAgenda( @Valid @RequestBody  AgendaDtoR agendaDtoR, BindingResult result) throws Exception {
		
		Map<String, Object> response = new HashMap<>();
		
		ANIOACTUAL = datosfecha.Obdatosfecha("A"); 
		MESACTUAL = datosvarios.seriadoNumero(Long.parseLong(datosfecha.Obdatosfecha("M")),2L);
				
		Agenda agenda = new Agenda();
		Turnos turnos = new Turnos();
		Socieda socieda = new Socieda();
		Consultorio consultorio = new Consultorio();
		Persprof persprof = new Persprof();

		turnos.setId( agendaDtoR.getIdturnos() );
		agenda.setTurnos( turnos );		
		socieda.setId( agendaDtoR.getIdsocieda() );
		agenda.setSocieda( socieda );
		consultorio.setId( agendaDtoR.getIdconsultorio() );
		agenda.setConsultorio( consultorio );		
		persprof.setId( agendaDtoR.getIdpersprof() );
		agenda.setPersprof( persprof );
		
		agenda.setAnio(ANIOACTUAL);
		agenda.setMes(MESACTUAL);
		
		agenda.setDia( agendaDtoR.getDia() );
		agenda.setHora( agendaDtoR.getHora() );
		agenda.setObservacion( agendaDtoR.getObservacion() );
		agenda.setFechaagenda( agendaDtoR.getFechaagenda() );
		agenda.setEstado( "A" );
		agenda.setIdusuario( agendaDtoR.getIdusuario() );
		agenda.setIdusuariom(0L);

		try {
			agendaservice.save( agenda );
		    response.put("mensaje", "Datos de Agenda grabada con exito");
		} catch (Exception e) {
		      response.put("error", "Error al Grabar Datos de Agenda : " + e.getMessage());
		      return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}    
		return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);		
		
	}
	
	
	@PostMapping("/consulta")
	public ResponseEntity<?> ConsultaAgenda( @RequestBody AgendaDtoR agendaDtoR )throws Exception {
		
		Map<String, Object> response = new HashMap<>();
		
		AgendaDto agendacon = agendaservice.consulta( agendaDtoR.getId() );
		
		if (agendacon == null){
			response.put("error", "No existe la Agenda");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
			
		}
		return ResponseEntity.ok( agendacon );
		
	}

	@PostMapping("/generar")
	public ResponseEntity<Integer> generarAgenda(@RequestBody AgendaGeneraDtoR agendageneraDtoR) {
	    Integer generatedId = agendaservice.generarAgenda(
	            agendageneraDtoR.getMes(),
	            agendageneraDtoR.getIdpersprof(),
	            agendageneraDtoR.getDia(),
	            agendageneraDtoR.getIdturnos(),
	            agendageneraDtoR.getIdsocieda());

	    return ResponseEntity.ok(generatedId); // Assuming successful generation
	}
	
	
	@PostMapping("/listar")
	public ResponseEntity<?> ListaAgenda(@Valid @RequestBody AgendaDtoR agendaDtoR, BindingResult result) throws Exception {		
		List<AgendaObtDto> listaagenda = agendaservice.ListaAngenda( agendaDtoR.getIdsocieda(), agendaDtoR.getIdpersprof(), agendaDtoR.getIdturnos(), agendaDtoR.getAnio(), agendaDtoR.getMes() );
		return ResponseEntity.ok( listaagenda );		
		
	}
	
	@PostMapping("/actualiza")
	public ResponseEntity<?> ActualizaAgenda(@Valid @RequestBody AgendaDtoR agendaDtoR , BindingResult result) throws Exception {
		
		Map<String, Object> response = new HashMap<>();
		
		Agenda agenda = agendaservice.edit( agendaDtoR.getId() );

		if ( agenda == null ){
			response.put("error", "No existe la Agenda");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);			
		}

		agenda.setDia( agendaDtoR.getDia() );
		agenda.setHora( agendaDtoR.getHora() );
		agenda.setObservacion( agendaDtoR.getObservacion() );
		agenda.setEstado( agendaDtoR.getEstado() );  
		
		try {
			agendaservice.save( agenda );
		    response.put("mensaje", "Agenda actualizada con exito");
		} catch (Exception e) {
		      response.put("Error", "Error al Grabar la Agenda : " + e.getMessage());
		      return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}    
		
		return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);		
		
	}	
}
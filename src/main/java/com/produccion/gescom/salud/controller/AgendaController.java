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
import com.produccion.gescom.entity.Periodo;
import com.produccion.gescom.entity.Socieda;
import com.produccion.gescom.salud.services.AgendaService;
import com.produccion.gescom.salud.services.ConfagenService;
import com.produccion.gescom.salud.services.EspecialidaService;
import com.produccion.gescom.salud.services.PersprofService;
import com.produccion.gescom.salud.services.TurnosService;
import com.produccion.gescom.services.PeriodoService;
import com.produccion.gescom.salud.dto.AgendaDto;
import com.produccion.gescom.salud.dto.AgendaDtoR;
import com.produccion.gescom.salud.dto.AgendaGeneraDtoR;
import com.produccion.gescom.salud.dto.AgendaObtDto;
import com.produccion.gescom.salud.dto.AgendamesanioDto;
import com.produccion.gescom.salud.dto.ConfAgenDto;
import com.produccion.gescom.salud.entity.Agenda;
import com.produccion.gescom.salud.entity.Confagen;
import com.produccion.gescom.salud.entity.Consultorio;
import com.produccion.gescom.salud.entity.Especialida;
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
	private ConfagenService confagenservice;
	
	@Autowired
	private DatosFecha datosfecha;
	
	@Autowired
	private DatosVarios datosvarios;
	
	@Autowired
	private EspecialidaService especialidaService;
	
	@Autowired
	private PersprofService persprofService;
	
	@Autowired
	private TurnosService turnosService;
	
	@Autowired
	private PeriodoService periodoService;
	
	
	//private final PersprofService persprofservice;
	
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
	
	@PostMapping("/consultaaniomes")
	public ResponseEntity<?> ConsultaAgendaaniomes( @RequestBody AgendaDtoR agendaDtoR )throws Exception {
		//System.out.println("PRUEBA");
		//System.out.println(agendaDtoR.getIdsocieda());
		
		Map<String, Object> response = new HashMap<>();
		List<AgendamesanioDto> agendacon = agendaservice.ListaAngendaaniomes(agendaDtoR.getIdsocieda(), agendaDtoR.getAnio(), agendaDtoR.getMes());
		if (agendacon == null){
			response.put("error", "No existe la Agenda");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.NO_CONTENT);
			
		}
		return ResponseEntity.ok( agendacon );
		
	}

	@PostMapping("/conflista")
	public ResponseEntity<?> configuraAgenda(@RequestBody AgendaGeneraDtoR agendageneraDtoR) throws Exception{
		List<ConfAgenDto> confagen = confagenservice.ListaConfAngenda(agendageneraDtoR.getAnio());

	    return ResponseEntity.ok(confagen); // Assuming successful generation
	}
	
	
	@PostMapping("/generar")
	public ResponseEntity<?> generarAgenda(@RequestBody AgendaGeneraDtoR agendageneraDtoR) throws Exception  {
		Map<String, Object> response = new HashMap<>();
		Confagen confagen = new Confagen();
		confagen.setTipo(agendageneraDtoR.getTipo());
		confagen.setIdespecial(agendageneraDtoR.getIdespecial());
		confagen.setIdpersprof(agendageneraDtoR.getIdpersprof());
		confagen.setIdturno(agendageneraDtoR.getIdturno());
		confagen.setAnio(agendageneraDtoR.getAnio());
		confagen.setMes(agendageneraDtoR.getMes());
		confagen.setDia(agendageneraDtoR.getDia());
		confagen.setAniode(agendageneraDtoR.getAniode());
		confagen.setAnohas(agendageneraDtoR.getAnohas());
		confagen.setMesde(agendageneraDtoR.getMesde());
		confagen.setMeshas(agendageneraDtoR.getMeshas());
		confagen.setIdsocieda(agendageneraDtoR.getIdsocieda());
		confagen.setIdusuario(agendageneraDtoR.getIdusuario());
		confagen.prePersist();
		
		if (agendageneraDtoR.getTipo().equals("I")) {
			Especialida especialida = especialidaService.edita( agendageneraDtoR.getIdespecial() );
			if (especialida == null){
				response.put("error", "No existe la Especialida");
				return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
			}
			Persprof persprof = persprofService.edit(agendageneraDtoR.getIdpersprof());
			if (persprof == null){
				response.put("error", "No existe el Médico");
				return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
			}
			Turnos turnos = turnosService.edita(agendageneraDtoR.getIdturno());
			if (turnos == null){
				response.put("error", "No existe el Turno");
				return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
			}

			Periodo periodo = periodoService.BuscaPeriodo(agendageneraDtoR.getAnio(), agendageneraDtoR.getMes());
			if (periodo == null){
				response.put("error", "No existe Periodo");
				return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
			}
		}
		else
		{
			confagen.setAnio(agendageneraDtoR.getAnohas());
			//System.out.println("Anio Inicial");
			//System.out.println(agendageneraDtoR.getAniode());
			//System.out.println(agendageneraDtoR.getMesde());
			
			Periodo periodod = periodoService.BuscaPeriodo(agendageneraDtoR.getAniode(), agendageneraDtoR.getMesde());
			if (periodod == null){
				response.put("error", "No existe Periodo Desde");
				return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
			}
			
			Periodo periodoh = periodoService.BuscaPeriodo(agendageneraDtoR.getAnohas(), agendageneraDtoR.getMeshas());
			if (periodoh == null){
				response.put("error", "No existe Periodo Hasta");
				return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
			}
		}
		try {
			confagenservice.save(confagen);
		    response.put("mensaje", "Agenda Generada con Exito");
		} catch (Exception e) {
			response.put("error", e.getMessage().substring(27, 43));
		    return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}
		
		
		//response.put("error", "Agenda Generada");
	    /*Integer generatedId = agendaservice.generarAgenda(
	            agendageneraDtoR.getMes(),
	            agendageneraDtoR.getIdpersprof(),
	            agendageneraDtoR.getDia(),
	            agendageneraDtoR.getIdturnos(),
	            agendageneraDtoR.getIdsocieda());*/

		return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);
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
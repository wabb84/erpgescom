package com.produccion.gescom.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.produccion.gescom.dto.TurnosDto;
import com.produccion.gescom.dto.TurnosDtoR;
import com.produccion.gescom.entity.EVigencia;
import com.produccion.gescom.entity.Turnos;
import com.produccion.gescom.services.TurnosService;

import jakarta.validation.Valid;

@Controller
@CrossOrigin
@RequestMapping ("/turnos")
public class TurnosController {
private static final Log logger = LogFactory.getLog(TurnosController.class);
	
	@Autowired
	private TurnosService turnosservice;
	
	@PostMapping("/lista")
	public ResponseEntity<?> ListaTurnos(@Valid @RequestBody TurnosDtoR turnosdtor) throws Exception {
		//logger.info(turnosdtor.getIdsocieda());
		List<TurnosDto> turnoslista = turnosservice.turnosLista(turnosdtor.getIdsocieda());
		return ResponseEntity.ok(turnoslista);
	}	
	
	@PostMapping("/nuevo")
	public ResponseEntity<?> NuevoTurno(@Valid @RequestBody TurnosDtoR turnosDtoR, BindingResult result) throws Exception {
		Map<String, Object> response = new HashMap<>();
		
		Turnos turnosnew = new Turnos();
		turnosnew.setDescripcion(turnosDtoR.getDescripcion());
		turnosnew.setIdsocieda(turnosDtoR.getIdsocieda());
		turnosnew.setHorainicio(turnosDtoR.getHorainicio());
		turnosnew.setHorafin(turnosDtoR.getHorafin());
		turnosnew.setIntervalo(turnosDtoR.getIntervalo());
		turnosnew.setVigencia(turnosDtoR.getVigencia().equals("A") ? EVigencia.A : EVigencia.I );
		
		turnosnew.setIdusuario(turnosDtoR.getIdusuario());
		turnosnew.setIdusuariom(0L);
		turnosnew.prePersist();
		
		try {
			turnosservice.save(turnosnew);
		    response.put("mensaje", "Turno grabado con exito");
		} catch (Exception e) {
		      response.put("error", "Error al Grabar el Turno : " + e.getMessage());
		      return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}    
		return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);
	}	
	
	@PostMapping("/consulta")
	public ResponseEntity<?> ConsultaTurnos(@RequestBody TurnosDtoR turnosDtoR) throws Exception {
		Map<String, Object> response = new HashMap<>();
		TurnosDto turnoscon = turnosservice.consulta(turnosDtoR.getIdturnos());
		if (turnoscon == null){
			response.put("error", "No existe el Turno");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}

		return ResponseEntity.ok(turnoscon);
	}	
	
	@PostMapping("/actualiza")
	public ResponseEntity<?> ActualizaConsultorio(@Valid @RequestBody TurnosDtoR turnosDtoR, BindingResult result) throws Exception {
		Map<String, Object> response = new HashMap<>();
		
		Turnos turnos = turnosservice.edita(turnosDtoR.getIdturnos());
		if (turnos == null){
			response.put("error", "No existe el Turno");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}
		
		turnos.setDescripcion(turnosDtoR.getDescripcion());
		turnos.setIdsocieda(turnosDtoR.getIdsocieda());
		turnos.setHorainicio(turnosDtoR.getHorainicio());
		turnos.setHorafin(turnosDtoR.getHorafin());
		turnos.setIntervalo(turnosDtoR.getIntervalo());
		turnos.setVigencia(turnosDtoR.getVigencia().equals("A") ? EVigencia.A : EVigencia.I );
		turnos.setIdusuariom(turnosDtoR.getIdusuario());
		
		try {
			turnosservice.save(turnos);
		    response.put("mensaje", "Turno grabado con exito");
		} catch (Exception e) {
		      response.put("Error", "Error al Grabar el Turno : " + e.getMessage());
		      return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}    
		
		return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);
	}
	
	@PostMapping("/listacitas")
	public ResponseEntity<?> ListaTurnosCitas(@Valid @RequestBody TurnosDtoR turnosdtor) throws Exception {
		//logger.info(turnosdtor.getIdsocieda());
		List<TurnosDto> turnoslista = turnosservice.turnosListaCita(turnosdtor.getIdsocieda());
		return ResponseEntity.ok(turnoslista);
	}	
	
	
	
}

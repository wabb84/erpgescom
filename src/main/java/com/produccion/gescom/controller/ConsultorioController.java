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
import com.produccion.gescom.dto.ConsultorioDto;
import com.produccion.gescom.dto.ConsultorioDtoR;
import com.produccion.gescom.entity.Consultorio;
import com.produccion.gescom.entity.EVigencia;
import com.produccion.gescom.services.ConsultorioService;
import jakarta.validation.Valid;

@Controller
@CrossOrigin
@RequestMapping ("/consultorio")
public class ConsultorioController {
	private static final Log logger = LogFactory.getLog(ConsultorioController.class);
	
	@Autowired
	private ConsultorioService consultorioservice;
	
	@PostMapping("/lista")
	public ResponseEntity<?> ListaConsultorio(@Valid @RequestBody ConsultorioDtoR consultorioDtoR, BindingResult result) throws Exception {
		List<ConsultorioDto> consultoriolista = consultorioservice.consultorioLista(consultorioDtoR.getIdsocieda());
		return ResponseEntity.ok(consultoriolista);
	}	
	
	@PostMapping("/nuevo")
	public ResponseEntity<?> NuevoConsultorio(@Valid @RequestBody ConsultorioDtoR consultorioDtoR, BindingResult result) throws Exception {
		Map<String, Object> response = new HashMap<>();
		
		Consultorio consultorionew = new Consultorio();
		consultorionew.setDescripcion(consultorioDtoR.getDescripcion());
		consultorionew.setIdsocieda(consultorioDtoR.getIdsocieda());
		consultorionew.setVigencia(consultorioDtoR.getVigencia().equals("A") ? EVigencia.A : EVigencia.I );
		consultorionew.setIdusuario(consultorioDtoR.getIdusuario());
		consultorionew.setIdusuariom(0L);
		consultorionew.prePersist();
		
		try {
			consultorioservice.save(consultorionew);
		    response.put("mensaje", "Consultorio grabado con exito");
		} catch (Exception e) {
		      response.put("error", "Error al Grabar el Consultorio : " + e.getMessage());
		      return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}    
		return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);
	}	
	
	@PostMapping("/consulta")
	public ResponseEntity<?> ConsultaConsultorio(@RequestBody ConsultorioDtoR consultorioDtoR) throws Exception {
		Map<String, Object> response = new HashMap<>();
		//logger.info(consultorioDtoR.getIdconsultorio());
		ConsultorioDto consultoriocon = consultorioservice.consulta(consultorioDtoR.getIdconsultorio());
		if (consultoriocon == null){
			response.put("error", "No existe el Consultorio");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}

		return ResponseEntity.ok(consultoriocon);
	}	
	
	@PostMapping("/actualiza")
	public ResponseEntity<?> ActualizaConsultorio(@Valid @RequestBody ConsultorioDtoR consultorioDtoR, BindingResult result) throws Exception {
		Map<String, Object> response = new HashMap<>();
		
		Consultorio consultorio = consultorioservice.edita(consultorioDtoR.getIdconsultorio());
		if (consultorio == null){
			response.put("error", "No existe el Consultorio");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}
		
		consultorio.setDescripcion(consultorioDtoR.getDescripcion());
		consultorio.setIdsocieda(consultorioDtoR.getIdsocieda());
		consultorio.setVigencia(consultorioDtoR.getVigencia().equals("A") ? EVigencia.A : EVigencia.I );
		consultorio.setIdusuariom(consultorioDtoR.getIdusuario());
		
		try {
			consultorioservice.save(consultorio);
		    response.put("mensaje", "Consultorio grabado con exito");
		} catch (Exception e) {
		      response.put("Error", "Error al Grabar el Consultorio : " + e.getMessage());
		      return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}    
		
		return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);
	}	
}

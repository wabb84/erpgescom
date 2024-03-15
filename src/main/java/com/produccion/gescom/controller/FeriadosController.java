package com.produccion.gescom.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.produccion.gescom.dto.FeriadosDto;
import com.produccion.gescom.dto.FeriadosDtoR;
import com.produccion.gescom.dto.SociedaFiltroDtoR;
import com.produccion.gescom.entity.Feriados;
import com.produccion.gescom.services.FeriadosService;

import jakarta.validation.Valid;

@RestController
@RequestMapping ("/feriados")
@CrossOrigin
public class FeriadosController {
	@Autowired
	private FeriadosService feriadosservice;
	
	@PostMapping("/lista")
    public ResponseEntity<?> ListaFeriados(@Valid @RequestBody SociedaFiltroDtoR sociedaDtoR, BindingResult result){
		List<FeriadosDto> feriadoslista =  feriadosservice.listaferiados(sociedaDtoR.getIdsocieda());
		
	    return ResponseEntity.ok(feriadoslista);
	}
	
	@PostMapping("/nuevo")
    public ResponseEntity<?> NuevoFeriados(@Valid @RequestBody FeriadosDtoR feriadosDtoR, BindingResult result) throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		FeriadosDto feriadobus = feriadosservice.buscaferiado(feriadosDtoR.getIdsocieda(), feriadosDtoR.getFechaferia());
		
		if (feriadobus != null) {
			response.put("error", "La Fecha ya se encuentra registrada");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}
		
		Feriados feriadonew = new Feriados();
		feriadonew.setDescripcion(feriadosDtoR.getDescripcion());
		feriadonew.setFechaferia(feriadosDtoR.getFechaferia());
		feriadonew.setIdsocieda(feriadosDtoR.getIdsocieda());
		feriadonew.setIdusuario(feriadosDtoR.getIdusuario());
		feriadonew.setIdusuariom(0L);
		feriadonew.prePersist();
		try {
			feriadosservice.save(feriadonew);
		    response.put("mensaje", "Feriado grabado con exito");
		} catch (Exception e) {
		      response.put("error", "Error al Grabar día Feriado : " + e.getMessage());
		      return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}    
		
	    return ResponseEntity.ok(feriadonew);
	}
	
	@PostMapping("/edita")
    public ResponseEntity<?> EditaFeriados(@Valid @RequestBody FeriadosDtoR feriadosDtoR, BindingResult result) throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		Feriados feriadobus = feriadosservice.edita(feriadosDtoR.getIdferiado());
		
		if (feriadobus == null) {
			response.put("error", "La fecha no se encuentra registrada como feriado");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}
		
		feriadobus.setDescripcion(feriadosDtoR.getDescripcion());
		feriadobus.setIdusuariom(feriadosDtoR.getIdusuario());
		try {
			feriadosservice.save(feriadobus);
		    response.put("mensaje", "Feriado grabado con exito");
		} catch (Exception e) {
		      response.put("error", "Error al Grabar día Feriado : " + e.getMessage());
		      return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}    
		
	    return ResponseEntity.ok(feriadobus);
	}
	
	@PostMapping("/elimina")
    public ResponseEntity<?> EliminaFeriados(@Valid @RequestBody FeriadosDtoR feriadosDtoR, BindingResult result) throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		Feriados feriadobus = feriadosservice.edita(feriadosDtoR.getIdferiado());
		
		if (feriadobus == null) {
			response.put("error", "La fecha no se encuentra registrada como feriado");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}
		
		try {
			feriadosservice.elimina(feriadosDtoR.getIdferiado());
		    response.put("mensaje", "Feriado Eliminado con exito");
		} catch (Exception e) {
		      response.put("error", "Error al Grabar día Feriado : " + e.getMessage());
		      return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}    
		
	    return ResponseEntity.ok(response);
	}
}

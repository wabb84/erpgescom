package com.produccion.gescom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.produccion.gescom.dto.PeriodoDto;
import com.produccion.gescom.dto.PeriodoDtoR;
import com.produccion.gescom.dto.UsuarioDtoR;
import com.produccion.gescom.services.PeriodoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping ("/periodos")
@CrossOrigin
public class PeriodosController {
	
	@Autowired
	private PeriodoService periodoService;
	
	@PostMapping("/anios")
    public ResponseEntity<?> ListaAnios(){
		List<PeriodoDto> periodolistaa = periodoService.ListaAnios();
		
	    return ResponseEntity.ok(periodolistaa);
	}
	
	@PostMapping("/meses")
    public ResponseEntity<?> ListaMeses(@RequestBody PeriodoDtoR periodoDtoR) throws Exception {
		List<PeriodoDto> periodolistam = periodoService.ListaMeses(periodoDtoR.getAnio());
		
	    return ResponseEntity.ok(periodolistam);
	}
}

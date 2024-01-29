package com.produccion.gescom.salud.controller;

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

import com.produccion.gescom.commons.DatosFecha;
import com.produccion.gescom.commons.DatosVarios;
import com.produccion.gescom.entity.Documento;
import com.produccion.gescom.entity.Persona;
import com.produccion.gescom.entity.Seriexdoc;
import com.produccion.gescom.entity.Socieda;
import com.produccion.gescom.salud.dto.HistoriaDtoR;
import com.produccion.gescom.salud.entity.Historia;
import com.produccion.gescom.salud.services.HistoriaService;
import com.produccion.gescom.services.DocumentoService;
import com.produccion.gescom.services.SeriexdocService;

import jakarta.validation.Valid;

@Controller
@CrossOrigin
@RequestMapping ("/historia")
public class HistoriaController {
	
	@Autowired
	private HistoriaService historiaservice;

	@Autowired
	private DatosFecha datosfecha;
	
	@Autowired
	private DatosVarios datosvarios;
	
	@Autowired
	private DocumentoService documentoservice;	
	
	@Autowired
	private SeriexdocService seriexdocservice;
		
	private String TIDO = "HI";
	private String ANIO;
	private String MES;
	private String ANIOACTUAL; 
	private String MESACTUAL; 	
	
	@PostMapping("/nuevo")
	public ResponseEntity<?> NuevaHistoria( @Valid @RequestBody  HistoriaDtoR historiaDtor, BindingResult result) throws Exception {
		
		Map<String, Object> response = new HashMap<>();
		
		ANIOACTUAL = datosfecha.Obdatosfecha("A"); 
		MESACTUAL = datosvarios.seriadoNumero(Long.parseLong(datosfecha.Obdatosfecha("M")),2L);
		Documento documento = documentoservice.BuscaDocumentoAnio(historiaDtor.getIdsocieda(),TIDO,ANIOACTUAL);
		ANIO = documento.getTiposerie().equals("T") ? "0000" : documento.getTiposerie().equals("A") ? ANIOACTUAL : ANIOACTUAL;
		MES = documento.getTiposerie().equals("T") ? "00" : documento.getTiposerie().equals("A") ? "00" : MESACTUAL;
		//System.out.println(datosvarios.seriadoNumero(1L, 15L));
		//Seriexdoc seriexdoc = seriexdocservice.BuscaDocumentoAnioMes(historiaDtor.getIdsocieda(), documento.getId(), ANIO, MES);
		//seriexdoc.setCorrelativo(seriexdoc.getCorrelativo() + 1 );		
		
		Historia historia = new Historia();		
		historia.setNrohistori( historiaDtor.getNrohistori() );		
		Persona persona   = new Persona();
		persona.setId( historiaDtor.getId() );
		historia.setPersona( persona );
		historia.setHistfecingr( historiaDtor.getHistfecingr() );		
		historia.setTutor( historiaDtor.getTutor() );
		historia.setIdpersprof( historiaDtor.getIdpersprof() );
		Socieda socieda = new Socieda();
		socieda.setId( historiaDtor.getIdsocieda() );		
		historia.setIdsocieda( socieda );
		historia.setIdtippacie( historiaDtor.getIdtippacie() );
		historia.setIdtiphisto( historiaDtor.getIdtiphisto() );
	
		try {
			historiaservice.save( historia );
		    response.put("mensaje", "Datos de historia grabada con exito");
		} catch (Exception e) {
		      response.put("error", "Error al Grabar Datos de historia : " + e.getMessage());
		      return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}    
		return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);		
		
	}

}

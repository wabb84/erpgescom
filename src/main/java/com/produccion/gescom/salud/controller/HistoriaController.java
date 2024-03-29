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
import com.produccion.gescom.salud.dto.HistoriaDto;
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
		
	private String TIDO = "HC";
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
		Seriexdoc seriexdoc = seriexdocservice.BuscaDocumentoAnioMes(historiaDtor.getIdsocieda(), documento.getId(), ANIO, MES);
		seriexdoc.setCorrelativo(seriexdoc.getCorrelativo() + 1 );		
		
		Historia historia = new Historia();		
		
		historia.setAnio(ANIOACTUAL);
		historia.setMes(MESACTUAL);		
		
		Persona persona   = new Persona();
		persona.setId( historiaDtor.getIdpersona() );
		historia.setIdpersona( persona );
		
		historia.setSerie(documento.getSerie());
		historia.setNumeroserie(datosvarios.seriadoNumero(seriexdoc.getCorrelativo(), seriexdoc.getLongitud()));		
		historia.setHistfecingr( historiaDtor.getHistfecingr() );
		historia.setIdpersprof( historiaDtor.getIdpersprof() );
		Socieda socieda = new Socieda();
		socieda.setId( historiaDtor.getIdsocieda() );		
		historia.setIdsocieda( socieda );
		historia.setIdtippacie( historiaDtor.getIdtippacie());
		historia.setIdtiphisto( historiaDtor.getIdtiphisto());
		historia.setAlergias(historiaDtor.getAlergias());
		historia.setIdtiposangre(historiaDtor.getIdtiposangre());
		historia.setObservacion(historiaDtor.getObservacion());
		historia.setIdusuario( historiaDtor.getIdusuario() );
		historia.setIdusuariom(0L);
		historia.prePersist();
		
		HistoriaDto historiacon;
	
		try {
			historiaservice.save( historia, seriexdoc );
			
			historiacon = historiaservice.consultaHistoriaPersona(historiaDtor.getIdpersona());
			
		    //response.put("mensaje", "Datos de historia grabada con exito");
		} catch (Exception e) {
		      response.put("error", "Error al Grabar Datos de historia : " + e.getMessage());
		      return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}    
		//return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);		
		return ResponseEntity.ok(historiacon);
	}
	
	@PostMapping("/consulta")
	public ResponseEntity<?> ConsultaHistoria( @RequestBody HistoriaDtoR  historiaDtoR )throws Exception {
		Map<String, Object> response = new HashMap<>();
		
		HistoriaDto historiacon = historiaservice.consulta( historiaDtoR.getId() );
		if (historiacon == null){
			response.put("error", "No existe la Historia");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(historiacon);
	}

	@PostMapping("/actualiza")
	public ResponseEntity<?> ActualizaHistoria(@Valid @RequestBody HistoriaDtoR historiaDtoR, BindingResult result) throws Exception {
		
		Map<String, Object> response = new HashMap<>();
		Historia historia = historiaservice.edit( historiaDtoR.getId() );
		if ( historia == null ){
			response.put("error", "No existe la Historia");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}		
		historia.setIdpersprof( historiaDtoR.getIdpersprof() );
		historia.setIdtippacie( historiaDtoR.getIdtippacie() );
		historia.setIdtiphisto( historiaDtoR.getIdtiphisto() );
		historia.setAlergias(historiaDtoR.getAlergias());
		historia.setIdtiposangre(historiaDtoR.getIdtiposangre());
		historia.setObservacion(historiaDtoR.getObservacion());
		historia.setIdusuariom(historiaDtoR.getIdusuario());
		
		try {
			historiaservice.saveedit( historia);
		    response.put("mensaje", "Historia actualizada con exito");
		} catch (Exception e) {
		      response.put("error", "Error al Grabar la Historia : " + e.getMessage());
		      return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}    
		return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);		
	}
	
	@PostMapping("/consulta-historia-persona")
	public ResponseEntity<?> ConsultaHistoriaPersona( @RequestBody HistoriaDtoR  historiaDtoR )throws Exception {
		//Map<String, Object> response = new HashMap<>();
		
		HistoriaDto historiacon = historiaservice.consultaHistoriaPersona(historiaDtoR.getIdpersona());
		if (historiacon == null){
			//response.put("mensaje", "No existe la Historia");
			//return new ResponseEntity<Map<String,Object>>(response , HttpStatus.NOT_FOUND);
			return ResponseEntity.noContent().build();
			//return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe Historia Clínica");
		}
		return ResponseEntity.ok(historiacon);
	}
}
package com.produccion.gescom.controller;

import java.util.HashMap;
import java.util.Map;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.produccion.gescom.dto.SociedaDto;
import com.produccion.gescom.dto.SociedaDtoR;
import com.produccion.gescom.entity.ETipoPersona;
import com.produccion.gescom.entity.Socieda;
import com.produccion.gescom.services.SociedaService;

import jakarta.validation.Valid;

@Controller
@CrossOrigin
@RequestMapping ("/socieda")
public class SociedaController {
//private static final Log logger = LogFactory.getLog(SociedaController.class);
	
	@Autowired
	private SociedaService sociedaservice;
	
	@PostMapping("/nuevosocieda")
	public ResponseEntity<?> NuevaSocieda(@Valid @RequestBody SociedaDtoR sociedaDtoR, BindingResult result) throws Exception {
		Map<String, Object> response = new HashMap<>();
		
		Socieda sociedanew = new Socieda();
		sociedanew.setSociedanombre(sociedaDtoR.getSociedanombre());
		sociedanew.setIdrubro(sociedaDtoR.getIdrubro());
		sociedanew.setTipopersona(sociedaDtoR.getTipopersona().equals("N") ? ETipoPersona.N : ETipoPersona.J );
		sociedanew.setIdtipodoc(sociedaDtoR.getIdtipodoc());
		sociedanew.setNumerodocumento(sociedaDtoR.getNumerodocumento());
		sociedanew.setNombrecomercial(sociedaDtoR.getNombrecomercial());
		sociedanew.setIdpais(sociedaDtoR.getIdpais());
		sociedanew.setSerie(sociedaDtoR.getSerie());
		sociedanew.setEstadosocieda(sociedaDtoR.getEstadosocieda());
		sociedanew.setIdusuario(sociedaDtoR.getIdusuario());
		sociedanew.setIdusuariom(0L);
		sociedanew.prePersist();
		
		try {
			sociedaservice.save(sociedanew);
		    response.put("mensaje", "Sociedad grabada con exito");
		} catch (Exception e) {
		      response.put("error", "Error al Grabar la Sociedad : " + e.getMessage());
		      return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}    
		return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);
	}	
	
	@PostMapping("/consultasocieda")
	public ResponseEntity<?> ConsultaSocieda(@RequestBody SociedaDtoR sociedaDtoR) throws Exception {
		Map<String, Object> response = new HashMap<>();
		SociedaDto sociedacon = sociedaservice.consulta(sociedaDtoR.getId());
		if (sociedacon == null){
			response.put("error", "No existe la Sociedad");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}
		//return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);
		return ResponseEntity.ok(sociedacon);
	}	
	
	@PostMapping("/actualizasocieda")
	public ResponseEntity<?> ActualizaSocieda(@Valid @RequestBody SociedaDtoR sociedaDtoR, BindingResult result) throws Exception {
		Map<String, Object> response = new HashMap<>();
		
		Socieda socieda = sociedaservice.edita(sociedaDtoR.getId());
		if (socieda == null){
			response.put("error", "No existe la Sociedad");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}
		
		socieda.setSociedanombre(sociedaDtoR.getSociedanombre());
		socieda.setIdrubro(sociedaDtoR.getIdrubro());
		socieda.setTipopersona(sociedaDtoR.getTipopersona().equals("N") ? ETipoPersona.N : ETipoPersona.J );
		socieda.setIdtipodoc(sociedaDtoR.getIdtipodoc());
		socieda.setNumerodocumento(sociedaDtoR.getNumerodocumento());
		socieda.setNombrecomercial(sociedaDtoR.getNombrecomercial());
		socieda.setIdpais(sociedaDtoR.getIdpais());
		socieda.setSerie(sociedaDtoR.getSerie());
		socieda.setEstadosocieda(sociedaDtoR.getEstadosocieda());
		socieda.setIdusuariom(sociedaDtoR.getIdusuario());
		
		try {
			sociedaservice.save(socieda);
		    response.put("mensaje", "Sociedad grabada con exito");
		} catch (Exception e) {
		      response.put("Error", "Error al Grabar la Sociedad : " + e.getMessage());
		      return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}    
		
		return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);
	}	
}

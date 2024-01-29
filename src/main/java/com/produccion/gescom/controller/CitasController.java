package com.produccion.gescom.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.produccion.gescom.entity.Cita;
import com.produccion.gescom.entity.Documento;
import com.produccion.gescom.entity.EEstadoCita;
import com.produccion.gescom.entity.ETipoPersona;
import com.produccion.gescom.entity.Seriexdoc;
import com.produccion.gescom.services.CitasService;
import com.produccion.gescom.services.DocumentoService;
import com.produccion.gescom.services.SeriexdocService;

import jakarta.validation.Valid;

import com.produccion.gescom.commons.DatosFecha;
import com.produccion.gescom.commons.DatosVarios;
import com.produccion.gescom.dto.CitaDtoR;

@Controller
@CrossOrigin
@RequestMapping ("/citas")
// Falta marcar que la Agenda este ocupada
public class CitasController {
	@Autowired
	private DocumentoService documentoservice;
	
	@Autowired
	private SeriexdocService seriexdocservice;
	
	@Autowired
	private CitasService citaservice;
	
	@Autowired
	private DatosFecha datosfecha;
	
	@Autowired
	private DatosVarios datosvarios;
	
	private String TIDO = "CI";
	private String ANIO;
	private String MES;
	private String ANIOACTUAL; 
	private String MESACTUAL; 
	
	@PostMapping("/nueva")
	//public ResponseEntity<?> NuevCita(@Valid @RequestBody ConsultorioDtoR consultorioDtoR, BindingResult result) throws Exception {
	public ResponseEntity<?> NuevCita(@Valid @RequestBody CitaDtoR citadtor) throws Exception {
		ANIOACTUAL = datosfecha.Obdatosfecha("A"); 
		MESACTUAL = datosvarios.seriadoNumero(Long.parseLong(datosfecha.Obdatosfecha("M")),2L);
		Documento documento = documentoservice.BuscaDocumentoAnio(citadtor.getIdsocieda(),TIDO,ANIOACTUAL);
		ANIO = documento.getTiposerie().equals("T") ? "0000" : documento.getTiposerie().equals("A") ? ANIOACTUAL : ANIOACTUAL;
		MES = documento.getTiposerie().equals("T") ? "00" : documento.getTiposerie().equals("A") ? "00" : MESACTUAL;
		//System.out.println(datosvarios.seriadoNumero(1L, 15L));
		Seriexdoc seriexdoc = seriexdocservice.BuscaDocumentoAnioMes(citadtor.getIdsocieda(), documento.getId(), ANIO, MES);
		seriexdoc.setCorrelativo(seriexdoc.getCorrelativo() + 1 );
		
		Cita cita = new Cita();
		cita.setAnio(ANIOACTUAL);
		cita.setMes(MESACTUAL);
		cita.setSerie(documento.getSerie());
		cita.setNumeroserie(datosvarios.seriadoNumero(seriexdoc.getCorrelativo(), seriexdoc.getLongitud()));
		
		cita.setIdagenda(2L);
		cita.setIdhistoria(1L);
		
		cita.setIdsocieda(citadtor.getIdsocieda());
		cita.setEstadocita(EEstadoCita.R);
		cita.setIdusuario(citadtor.getIdusuario());
		cita.setIdusuariom(0L);
		cita.prePersist();		
		
		citaservice.save(cita,seriexdoc);
		return ResponseEntity.ok(seriexdoc);
	}
}

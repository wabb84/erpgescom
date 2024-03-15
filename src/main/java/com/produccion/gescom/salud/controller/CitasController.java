package com.produccion.gescom.salud.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
import com.produccion.gescom.entity.Documento;
import com.produccion.gescom.entity.Seriexdoc;
import com.produccion.gescom.salud.dto.CitaDtoR;
import com.produccion.gescom.salud.dto.CitasListadoDto;
import com.produccion.gescom.salud.dto.CitasListadoDtoR;
import com.produccion.gescom.salud.entity.Agenda;
import com.produccion.gescom.salud.entity.Cita;
import com.produccion.gescom.salud.entity.EEstadoCita;
import com.produccion.gescom.salud.entity.Turnos;
import com.produccion.gescom.salud.services.AgendaService;
import com.produccion.gescom.salud.services.CitasService;
import com.produccion.gescom.services.DocumentoService;
import com.produccion.gescom.services.SeriexdocService;
import jakarta.validation.Valid;
import com.produccion.gescom.commons.DatosFecha;
import com.produccion.gescom.commons.DatosVarios;

@Controller
@CrossOrigin
@RequestMapping ("/citas")
 
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
	private AgendaService agendaservice;
	
	@Autowired
	private DatosVarios datosvarios;
	
	private String TIDO = "CI";
	private String ANIO;
	private String MES;
	private String ANIOACTUAL; 
	private String MESACTUAL; 
	
	@PostMapping("/nueva")
	public ResponseEntity<?> NuevCita(@Valid @RequestBody CitaDtoR citadtor) throws Exception {
		Map<String, Object> response = new HashMap<>();
		
		ANIOACTUAL = datosfecha.Obdatosfecha("A"); 
		MESACTUAL = datosvarios.seriadoNumero(Long.parseLong(datosfecha.Obdatosfecha("M")),2L);
		Documento documento = documentoservice.BuscaDocumentoAnio(citadtor.getIdsocieda(),TIDO,ANIOACTUAL);
		ANIO = documento.getTiposerie().equals("T") ? "0000" : documento.getTiposerie().equals("A") ? ANIOACTUAL : ANIOACTUAL;
		MES = documento.getTiposerie().equals("T") ? "00" : documento.getTiposerie().equals("A") ? "00" : MESACTUAL;

		Seriexdoc seriexdoc = seriexdocservice.BuscaDocumentoAnioMes(citadtor.getIdsocieda(), documento.getId(), ANIO, MES);
		seriexdoc.setCorrelativo(seriexdoc.getCorrelativo() + 1 );
		Agenda agenda = agendaservice.edit( citadtor.getIdagenda());
		
		if (agenda == null){
			response.put("error", "No existe la Agenda");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}
		
		if (agenda.getEstado().equals("C")){
			response.put("error", "Agenda ya se encuentra Separada");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}
		
		Cita cita = new Cita();
		
		cita.setAnio(ANIOACTUAL);
		cita.setMes(MESACTUAL);
		cita.setSerie(documento.getSerie());
		cita.setNumeroserie(datosvarios.seriadoNumero(seriexdoc.getCorrelativo(), seriexdoc.getLongitud()));
		
		cita.setIdagenda(agenda.getId());
		cita.setFechacita(agenda.getFechaagenda());
		cita.setHoracita(agenda.getHora());
		cita.setIdpersona(citadtor.getIdpersona());
		cita.setIdsocieda(citadtor.getIdsocieda());
		cita.setEstadocita(EEstadoCita.C);
		cita.setIdestadocita(1L);
		cita.setIdusuario(citadtor.getIdusuario());
		cita.setIdusuariom(0L);
		cita.prePersist();
		agenda.setEstado("C");
		agenda.setIdusuariom(citadtor.getIdusuario());
		
		//System.out.println(documento.getSerie());
		//System.out.println(cita.getNumeroserie());
		
		try {
			citaservice.save(cita,seriexdoc,agenda);
			response.put("mensaje", "Cita grabada con exito");
		} catch (Exception e) {
		    response.put("error", "Error al Grabar La Cita : " + e.getMessage());
		    return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}  
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/edita")
	public ResponseEntity<?> EditaCita(@Valid @RequestBody CitaDtoR citadtor) throws Exception {
		Map<String, Object> response = new HashMap<>();
		
		Cita cita = citaservice.buscacita(citadtor.getIdcita()); 
		if (cita == null){
			response.put("error", "No existe la Cita");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.NO_CONTENT);
		}
		cita.setIdestadocita(citadtor.getIdestadocita());
		cita.setIdusuariom(citadtor.getIdusuario());
		
		Agenda agenda = agendaservice.edit( cita.getIdagenda());
		if (citadtor.getIdestadocita() == 3 || citadtor.getIdestadocita() == 5) {
			agenda.setEstado("P");
		}
		else {
			//agenda.setEstado("F");
		}
		agenda.setIdusuariom(citadtor.getIdusuario());
		
		try {
			citaservice.saveedicion(cita,agenda);
			response.put("mensaje", "Cita grabada con exito");
		} catch (Exception e) {
		    response.put("error", "Error al Grabar La Cita : " + e.getMessage());
		    return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}  
		return ResponseEntity.ok(response);
	}
	
	
	@PostMapping("/listacitas")
	public ResponseEntity<?> ListadoCita(@Valid @RequestBody CitasListadoDtoR citalistadodtor) throws Exception {
		
		List<CitasListadoDto> citaslista = citaservice.ListadoCitas(citalistadodtor.getFechai(), citalistadodtor.getFechaf(), citalistadodtor.getIdturno(), citalistadodtor.getIdpaciente(), citalistadodtor.getIdmedico(), citalistadodtor.getEstado(), citalistadodtor.getIdsocieda());
		
		return ResponseEntity.ok(citaslista);
	}
	
	
	@PostMapping("/adicional")
	public ResponseEntity<?> AdicionalCita(@Valid @RequestBody CitaDtoR citadtor) throws Exception {
		Map<String, Object> response = new HashMap<>();
		
		Long intervaloini = 0L;
		String horainicio = "";
		String horanueva = "";
		Long minutos = 0L;
		
		Agenda agenda = agendaservice.edit(citadtor.getIdagenda());
		Cita cita = citaservice.buscacita(citadtor.getIdcita());
		
		Agenda agendanew = new Agenda();
		agendanew.setTurnos(agenda.getTurnos());
		agendanew.setSocieda(agenda.getSocieda());
		agendanew.setConsultorio(agenda.getConsultorio());
		agendanew.setPersprof(agenda.getPersprof());
		agendanew.setAnio(agenda.getAnio());
		agendanew.setMes(agenda.getMes());
		agendanew.setDia(agenda.getDia());
		agendanew.setHora(citadtor.getHora());
		agendanew.setFechaagenda(agenda.getFechaagenda());
		agendanew.setEstado( "C" );
		agendanew.setIdespecialida(agenda.getIdespecialida());
		agendanew.setIdconfagenda(agenda.getIdconfagenda());
		
		intervaloini = agenda.getIntervalo();
		horainicio = agenda.getHora();
		horanueva = citadtor.getHora();
		
		
		System.out.println(agenda.getAnio());
		System.out.println(agenda.getMes());
		System.out.println(agenda.getDia());
		System.out.println(horainicio);
		System.out.println(horainicio.substring(0, 2));
		System.out.println(horainicio.substring(3, 5));
		
		LocalDateTime fechaIni = LocalDateTime.of(Integer.parseInt(agenda.getAnio()), Integer.parseInt(agenda.getMes()), Integer.parseInt(agenda.getDia()), Integer.parseInt(horainicio.substring(0, 2)), Integer.parseInt(horainicio.substring(3, 5)));
		LocalDateTime fechaFin = LocalDateTime.of(Integer.parseInt(agenda.getAnio()), Integer.parseInt(agenda.getMes()), Integer.parseInt(agenda.getDia()), Integer.parseInt(horanueva.substring(0, 2)), Integer.parseInt(horanueva.substring(3, 5)));
		minutos = ChronoUnit.MINUTES.between(fechaIni, fechaFin);
		agenda.setIntervalo(minutos);
		agenda.setIdusuariom(citadtor.getIdusuario());
		agendanew.setIntervalo(intervaloini - minutos);
		agendanew.setIdusuariom(0L);
		agendanew.setIdusuario(citadtor.getIdusuario());
		agendanew.prePersist();
		
		Cita citanew = new Cita();
		
		ANIOACTUAL = cita.getAnio(); 
		MESACTUAL = datosvarios.seriadoNumero(Long.parseLong(datosfecha.Obdatosfecha("M")),2L);
		Documento documento = documentoservice.BuscaDocumentoAnio(cita.getIdsocieda(),TIDO,ANIOACTUAL);
		ANIO = documento.getTiposerie().equals("T") ? "0000" : documento.getTiposerie().equals("A") ? ANIOACTUAL : ANIOACTUAL;
		MES = documento.getTiposerie().equals("T") ? "00" : documento.getTiposerie().equals("A") ? "00" : MESACTUAL;
		Seriexdoc seriexdoc = seriexdocservice.BuscaDocumentoAnioMes(cita.getIdsocieda(), documento.getId(), ANIO, MES);
		seriexdoc.setCorrelativo(seriexdoc.getCorrelativo() + 1 );
		
		//citanew.setIdagenda(minutos);
		citanew.setIdpersona(citadtor.getIdpersona());
		citanew.setIdsocieda(cita.getIdsocieda());
		citanew.setEstadocita(EEstadoCita.A);
		citanew.setAnio(cita.getAnio());
		citanew.setMes(cita.getMes());
		citanew.setSerie(documento.getSerie());
		citanew.setNumeroserie(datosvarios.seriadoNumero(seriexdoc.getCorrelativo(), seriexdoc.getLongitud()));
		citanew.setFechacita(cita.getFechacita());
		citanew.setHoracita(citadtor.getHora());
		citanew.setIdestadocita(1L);
		citanew.setIdusuario(citadtor.getIdusuario());
		citanew.setIdusuariom(0L);
		citanew.prePersist();
		
		try {
			citaservice.saveadicional(agenda, agendanew, citanew ,seriexdoc);
			response.put("mensaje", "Cita Adicional grabada con exito");
		} catch (Exception e) {
		    response.put("error", "Error al Grabar La Cita Adicional: " + e.getMessage());
		    return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}  
	
		return ResponseEntity.ok(response);
		//return ResponseEntity.ok(agendanew);
		
	}
}
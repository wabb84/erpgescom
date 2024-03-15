package com.produccion.gescom.salud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.produccion.gescom.salud.dto.ConfAgenDto;
import com.produccion.gescom.salud.entity.Confagen;
import com.produccion.gescom.salud.repository.AgendaRepository;
import com.produccion.gescom.salud.repository.ConfagenRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConfagenServiceImpl implements ConfagenService {
	
	@Autowired
	private ConfagenRepository confagenrep;
	
	@Autowired
	private AgendaRepository agendarep;
	
	@Override
	@Transactional
	public Confagen save( Confagen confagen ){
		confagenrep.save( confagen );
		if(confagen.getTipo().equals("I")) {
			Integer resultado = agendarep.generarAgendaI(confagen.getTipo(), confagen.getIdpersprof(), confagen.getIdespecial(), confagen.getIdturno(), confagen.getAnio(), confagen.getMes(), confagen.getDia(), confagen.getIdsocieda(), confagen.getId(), confagen.getIdusuario());
			if (resultado == -1) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Agenda duplicada");
			}
		} 
		else {
			Integer resultado = agendarep.generarAgendaM(confagen.getAniode(), confagen.getMesde(), confagen.getAnohas(), confagen.getMeshas(), confagen.getIdsocieda(), confagen.getIdusuario(), confagen.getId());
			if (resultado == -1) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Agenda duplicada");
			}
		}
		return confagenrep.save( confagen );
		//return ResponseEntity.ok( confagen );
	}
	
	@Override
	public List<ConfAgenDto> ListaConfAngenda( String anio)
	{
		return confagenrep.ListadoConfAgens(anio);

	}
}

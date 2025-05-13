package com.produccion.gescom.salud.services;

import java.time.format.DateTimeFormatter;
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
		Integer resultado;
		
		//System.out.println(confagen.getTipo());
		if(confagen.getTipo().equals("I")) {
			resultado = agendarep.generarAgendaI(confagen.getTipo(), confagen.getIdpersprof(), confagen.getIdespecial(), confagen.getIdturno(), confagen.getAnio(), confagen.getMes(), confagen.getDia(), confagen.getIdsocieda(), confagen.getId(), confagen.getIdusuario());
			if (resultado == -1) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Agenda duplicada");
			}
		} 
		else if(confagen.getTipo().equals("T")){
			resultado = agendarep.generarAgendaM(confagen.getAniode(), confagen.getMesde(), confagen.getAnohas(), confagen.getMeshas(), confagen.getIdsocieda(), confagen.getIdusuario(), confagen.getId());
			if (resultado == -1) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Agenda duplicada");
			}
		}	
		else if(confagen.getTipo().equals("B")){
			
			//System.out.println("LLegue aca");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String fechai = confagen.getFechai().format(formatter);
			String fechaf = confagen.getFechaf().format(formatter);
			
			//System.out.println(fechai);
			//System.out.println(fechaf);
			
			resultado = agendarep.generarAgendaB(fechai, fechaf, confagen.getIdpersprof(), confagen.getIdturno(), confagen.getIdsocieda(), confagen.getIdusuario());
			if (resultado == -1) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Agenda error");
			}
		}
		return confagenrep.save( confagen );
		//return ResponseEntity.ok( confagen );
	}
	
	@Override
	public List<ConfAgenDto> ListaConfAngenda(String anio, Long idsocieda)
	{
		return confagenrep.ListadoConfAgens(anio,idsocieda);

	}
	
	@Override
	public ConfAgenDto VerificaBloqueo(String pfechai, String pfechaf, Long idpersprof, Long idsocieda) {
		return confagenrep.BloqueoConfAgen(pfechai, pfechaf, idpersprof, idsocieda);
	}
	
	@Override
	public ConfAgenDto VerificaBloqueoT(String pfechai, String pfechaf, Long idpersprof, Long idturnos, Long idsocieda) {
		return confagenrep.BloqueoConfAgenT(pfechai, pfechaf, idpersprof, idturnos, idsocieda);
	}
	
	@Override
	public ConfAgenDto BuscaAgenB(String pfechai, String pfechaf, Long idpersprof, Long idsocieda) {
		return confagenrep.BuscaAgenB(pfechai, pfechaf, idpersprof, idsocieda);
	}
	
	@Override
	public ConfAgenDto BuscaAgenBT(String pfechai, String pfechaf, Long idpersprof, Long idturnos, Long idsocieda) {
		return confagenrep.BuscaAgenBT(pfechai, pfechaf, idpersprof, idturnos, idsocieda);
	}
	
	
	
	
}

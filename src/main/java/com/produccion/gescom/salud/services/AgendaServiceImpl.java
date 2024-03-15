package com.produccion.gescom.salud.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produccion.gescom.salud.dto.AgendaDto;
import com.produccion.gescom.salud.dto.AgendaObtDto;
import com.produccion.gescom.salud.dto.AgendamesanioDto;
import com.produccion.gescom.salud.entity.Agenda;
import com.produccion.gescom.salud.repository.AgendaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AgendaServiceImpl implements AgendaService{

	@Autowired
	private AgendaRepository agendarep;
	
	@Override
	public Agenda save( Agenda agenda ){
		return agendarep.save( agenda );
	}
	
	@Override
	public AgendaDto consulta( Long idagenda ){		
		return agendarep.FindByAgenda( idagenda ); 
	}

	/*@Override
	@Transactional
    public Integer generarAgenda( String mes, Long idpersprof, Long dia, Long idturnos, Long idsocieda ) {       
       try {
           return agendarep.generarAgenda(mes, idpersprof, dia, idturnos, idsocieda);
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }       
    }*/	

	@Override
	public List<AgendaObtDto> ListaAngenda( Long idsocieda, Long idpersprof, Long idturnos, String anio,  String mes ){
		return agendarep.ListaAngenda( idsocieda, idpersprof, idturnos, anio, mes );
	}
	
	@Override
	public Agenda edit( Long idagenda ){
		return agendarep.findById(idagenda).orElse(null);
		
	}
	
	@Override
	public List<AgendamesanioDto> ListaAngendaaniomes( Long idsocieda, String anio,  String mes ){
		return agendarep.ListaAngendaaniomes(idsocieda, anio, mes);
	};
	
}
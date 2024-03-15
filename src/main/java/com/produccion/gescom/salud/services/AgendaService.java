package com.produccion.gescom.salud.services;

import java.util.List;
import com.produccion.gescom.salud.dto.AgendaDto;
import com.produccion.gescom.salud.dto.AgendaObtDto;
import com.produccion.gescom.salud.dto.AgendamesanioDto;
import com.produccion.gescom.salud.entity.Agenda;

public interface AgendaService {
	
	public Agenda save( Agenda agenda );
	
	public AgendaDto consulta( Long idagenda );
	
	//public Integer generarAgenda( String mes, Long idpersprof, Long dia, Long idturnos, Long idsocieda );
	
	public List<AgendaObtDto> ListaAngenda( Long idsocieda, Long idpersprof, Long idturnos, String anio,  String mes );
	
	public Agenda edit( Long idagenda );
	
	public List<AgendamesanioDto> ListaAngendaaniomes( Long idsocieda, String anio,  String mes );
	
}
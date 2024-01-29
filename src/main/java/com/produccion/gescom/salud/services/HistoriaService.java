package com.produccion.gescom.salud.services;

import java.util.List;

import com.produccion.gescom.salud.dto.HistoriaDto;
import com.produccion.gescom.salud.entity.Historia;

public interface HistoriaService {
	
	public Historia save( Historia historia );
	
	public HistoriaDto consulta( Long idhistoria );
	
	public List<HistoriaDto> ListaHistoria();

	public Historia edit( Long idhistoria );
	
}

package com.produccion.gescom.salud.services;

import com.produccion.gescom.entity.Seriexdoc;
import com.produccion.gescom.salud.dto.HistoriaDto;
import com.produccion.gescom.salud.entity.Historia;

public interface HistoriaService {
	
	public Historia save( Historia historia, Seriexdoc seriexdoc );
	public Historia saveedit( Historia historia);
	
	public HistoriaDto consulta( Long idhistoria );
	public Historia edit( Long idhistoria );
	public HistoriaDto consultaHistoriaPersona( Long idpersona );
	
}
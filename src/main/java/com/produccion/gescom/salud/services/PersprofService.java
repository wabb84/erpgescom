package com.produccion.gescom.salud.services;

import java.util.List;

import com.produccion.gescom.salud.dto.PersprofDto;
import com.produccion.gescom.salud.dto.PersprofdatosDto;
import com.produccion.gescom.salud.entity.Persprof;

public interface PersprofService {
	
	public Persprof save ( Persprof persprof );
	
	public PersprofDto consulta ( Long idpersprof );
	
	public List<PersprofdatosDto> ListaPersprof();
	
	public Persprof edit ( Long idpersprof );
	
}
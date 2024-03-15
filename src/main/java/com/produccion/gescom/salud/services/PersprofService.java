package com.produccion.gescom.salud.services;

import java.util.List;

import com.produccion.gescom.salud.dto.PersprofDto;
import com.produccion.gescom.salud.dto.PersprofdatosDto;
import com.produccion.gescom.salud.entity.Persprof;

public interface PersprofService {
	
	public Persprof save ( Persprof persprof );
	
	public PersprofDto consulta ( Long idpersprof );
	
	public List<PersprofdatosDto> ListaPersprof( Long idsocieda );
	
	public Persprof edit ( Long idpersprof );
	
	public PersprofDto buscarxpersona(Long idpersona);
	
	//public Persprof buscaid( Long idpersprof );
	
}
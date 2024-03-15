package com.produccion.gescom.salud.services;

import java.util.List;

import com.produccion.gescom.salud.dto.ConfAgenDto;
import com.produccion.gescom.salud.entity.Confagen;

public interface ConfagenService {
	public Confagen save( Confagen confagen );
	public List<ConfAgenDto> ListaConfAngenda( String anio);
}

package com.produccion.gescom.services;

import java.util.List;

import com.produccion.gescom.dto.TipoBusquedaDto;

public interface TipobusquedaService {
	public List<TipoBusquedaDto> listaTipoBusqueda(String tipo);
}

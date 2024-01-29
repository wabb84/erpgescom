package com.produccion.gescom.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produccion.gescom.dto.TipoBusquedaDto;
import com.produccion.gescom.repository.TipoBusquedaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TipoBusquedaServiceImpl implements TipobusquedaService {
	
	@Autowired
	private TipoBusquedaRepository tipobusrep;
	
	@Override
	public List<TipoBusquedaDto> listaTipoBusqueda(String tipo)
	{
		return tipobusrep.gettipobusquedae(tipo);
	}

}

package com.produccion.gescom.salud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produccion.gescom.salud.dto.HistoriaDto;
import com.produccion.gescom.salud.entity.Historia;
import com.produccion.gescom.salud.repository.HistoriaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HistoriaServiceimpl implements HistoriaService {

	@Autowired
	private HistoriaRepository historiarep;
	
	public Historia save( Historia historia ) {
		return historiarep.save( historia );
		
	}

	@Override
	public HistoriaDto consulta(Long idhistoria) {
		return historiarep.findByHistoria( idhistoria );
	}

	@Override
	public List<HistoriaDto> ListaHistoria() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Historia edit(Long idhistoria) {		
		return historiarep.findById( idhistoria ).orElse(null);
	}		
	
}
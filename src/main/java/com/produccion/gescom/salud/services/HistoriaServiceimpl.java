package com.produccion.gescom.salud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produccion.gescom.entity.Seriexdoc;
import com.produccion.gescom.repository.SeriexdocRepository;
import com.produccion.gescom.salud.dto.HistoriaDto;
import com.produccion.gescom.salud.entity.Historia;
import com.produccion.gescom.salud.repository.HistoriaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HistoriaServiceimpl implements HistoriaService {

	@Autowired
	private HistoriaRepository historiarep;
	
	@Autowired
	private SeriexdocRepository seriexdocrep;
	
	@Override
	@Transactional
	public Historia save( Historia historia, Seriexdoc seriexdoc ) {
		seriexdocrep.save(seriexdoc);
		return historiarep.save( historia);
	}
	
	@Override
	@Transactional
	public Historia saveedit( Historia historia ) {
		return historiarep.save( historia);
	}

	@Override
	public HistoriaDto consulta(Long idhistoria) {
		return historiarep.findByHistoria( idhistoria );
	}

	@Override
	public Historia edit(Long idhistoria) {		
		return historiarep.findById( idhistoria ).orElse(null);
	}
	
	@Override
	public HistoriaDto consultaHistoriaPersona( Long idpersona ){
		return historiarep.findByHisPers( idpersona );
	}
}
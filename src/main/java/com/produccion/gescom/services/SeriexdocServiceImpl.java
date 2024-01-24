package com.produccion.gescom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.produccion.gescom.entity.Seriexdoc;
import com.produccion.gescom.repository.SeriexdocRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeriexdocServiceImpl implements SeriexdocService {
	
	@Autowired
    private SeriexdocRepository seriexdocrep;
	
	public Seriexdoc BuscaDocumentoAnioMes(Long socieda, Long documento, String anio, String mes)
	{
		return seriexdocrep.findByIdsociedaAndIddocumentoAndAnioAndMes(socieda, documento, anio, mes);
	};
}
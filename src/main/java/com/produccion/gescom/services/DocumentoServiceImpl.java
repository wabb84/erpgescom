package com.produccion.gescom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.produccion.gescom.entity.Documento;
import com.produccion.gescom.repository.DocumentoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocumentoServiceImpl implements DocumentoService {
	
	@Autowired
    private DocumentoRepository documentorep;
	
	public Documento BuscaDocumentoAnio(Long socieda, String documento, String anio)
	{
		return documentorep.findByIdsociedaAndSerieAndAnio(socieda, documento, anio);
	};
}
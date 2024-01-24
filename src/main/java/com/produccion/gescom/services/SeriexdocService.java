package com.produccion.gescom.services;

import com.produccion.gescom.entity.Seriexdoc;

public interface SeriexdocService {
	public Seriexdoc BuscaDocumentoAnioMes(Long socieda, Long documento, String anio, String mes);
}

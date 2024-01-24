package com.produccion.gescom.services;

import com.produccion.gescom.entity.Documento;

public interface DocumentoService {
	public Documento BuscaDocumentoAnio(Long socieda, String documento, String anio);
}

package com.produccion.gescom.dto;

import java.util.Date;

public interface PersonaDto {

	Long	getIdpersona();
	Long	getTipoDocumento();
	Long	getIdsocieda();
	Long	getIdpais();
	String	getTipopersona();
	String	getNumerodocumento();
	String	getApellidopaterno();
	String	getApellidomaterno();
	String	getPrimernombre();
	String	getSegundonombre();
	String	getNombrelargo();
	String	getRazonsocial();
	String	getNomabreviado();
	Date 	getFecnacimiento();
	String 	getSexo();
	String	getvigencia();
}
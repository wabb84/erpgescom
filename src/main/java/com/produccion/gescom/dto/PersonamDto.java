package com.produccion.gescom.dto;

import java.time.LocalDate;

public interface PersonamDto {

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
	LocalDate 	getFecnacimiento();
	String 	getSexo();
	String	getVigencia();
	String	getEstadocivil();
	String	getLugarnacimi();
	String	getTelefono();
	String	getEmail();
	String	getDomicilio();
	String	getCodubigeo();
	String	getObservacion();
	Long	getIdpersprof();
}
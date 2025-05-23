package com.produccion.gescom.dto;

import java.time.LocalDate;

public interface PersonaMultipleDto {
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
	LocalDate 	getFecnacimiento();
	String 	getSexo();
	String	getvigencia();
	String	getEstadocivil();
	String	getLugarnacimi();
	String	getTelefono();
	String	getEmail();
	String	getDomicilio();
	String	getCodubigeo();
	Long	getTutor();
	String	getObservacion();
	String	getNumeroserie();
	String	getNombretutor();
	Long	getIdpersprof();
	String	getTipoparentutor();
	Long	getIdtipodoctutor();
	String  getNumerodoctutor();
}
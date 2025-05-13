package com.produccion.gescom.dto;

import java.time.LocalDate;

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
	String	getTipoparentutor();
	Long	getIdtipodoctutor();
	String	getApellnombtutor();
	String  getNumerodoctutor();
}
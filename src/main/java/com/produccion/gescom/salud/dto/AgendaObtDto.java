package com.produccion.gescom.salud.dto;

import java.time.LocalDate;

public interface AgendaObtDto {
	
	Long	getIdagenda();	
	Long	getIdturnos();
	String	getDescripcion();
	Long	getIdsocieda();	
	Long	getIdconsultorio();	
	Long	getIdpersprof();	
	String	getPersprof();
	String	getAnio();
	String	getMes();
	String	getDia();
	String	getHora();
	String	getObservacion();
	LocalDate getFechaagenda();
	String	getEstado();
}
package com.produccion.gescom.salud.dto;

import java.time.LocalDate;

public interface AgendaDto {
	Long		getIdagenda();
	Long		getIdturnos();
	Long		getIdsocieda();
	Long		getIdconsultorio();	
	Long 		getIdpersprof();
	String  	getAnio();
	String  	getMes();
	String  	getDia();
	String  	getHora();
	String  	getObservacion();
	LocalDate 	getFechaagenda();
	String  	getEstado();
}
package com.produccion.gescom.salud.dto;

import java.util.Date;

public interface HistoriaDto {
	Long	getIdhistoria();
	Long	getNrohistori();
	Long    getPersona();
	Date 	getHistfecingr();
	Long	getTutor();
	Long 	getIdpersprof();
	Long	getIdsocieda();
	Long 	GetIdtippacie();
	Long 	GetIdtiphisto();
	String 	GetSerie();
	String  GetNumeroserie();
}

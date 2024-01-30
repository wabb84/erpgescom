package com.produccion.gescom.salud.dto;

import java.time.LocalDate;


public interface HistoriaDto {
	Long	getIdhistoria();	
	Long    getIdpersona();
	LocalDate 	getHistfecingr();
	Long	getTutor();
	Long 	getIdpersprof();
	Long	getIdsocieda();
	Long 	getIdtippacie();
	Long 	getIdtiphisto();
	String 	getSerie();
	String  getNumeroserie();
	String  getAnio();
	String  getMes();
}
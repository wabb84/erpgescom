package com.produccion.gescom.dto;

import java.time.LocalDate;

public interface FeriadosDto {
	long getIdferiado();
	LocalDate getFechaferia();
	String getDescripcion();
}

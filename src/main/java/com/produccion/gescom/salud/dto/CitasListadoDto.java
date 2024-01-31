package com.produccion.gescom.salud.dto;
import java.time.LocalDate;
public interface CitasListadoDto {
	String getTturno();
	String getColortback();
	LocalDate getFechacita();
	String getHoracita();
	String getDoccita();
	String getPaciente();
	String getHistoria();
	String getMedico();
	String getConsultorio();
	String getEstadoc();
	String getColorestado();
	Long getIdcita();
}

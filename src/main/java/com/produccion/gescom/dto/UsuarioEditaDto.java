package com.produccion.gescom.dto;
import java.util.Date;

public interface UsuarioEditaDto {
	String getDesusuario();
	String getEmail();
	String getTelefono();
	Long getIdtipodoc();
	String getNumerodoc();
	String getEstadousuario();
	Date getFechaini();
	Date getFechafin();
	String getEstadopass();
	String getSexo();
}

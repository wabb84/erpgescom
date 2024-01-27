package com.produccion.gescom.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ReinicioPasswordDtoR  implements Serializable {
	private static final long serialVersionUID = 5926468583005150707L;
	private Long idusuario;
	
	
	private String passant;
	
	@NotNull
	@NotBlank(message = "Nuevo Password es requerido")
	private String passnew;
	private String passcon;

}

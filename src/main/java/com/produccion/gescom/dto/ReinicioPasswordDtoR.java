package com.produccion.gescom.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class ReinicioPasswordDtoR  implements Serializable {
	private static final long serialVersionUID = 5926468583005150707L;
	
	@NotNull
	@NotBlank(message = "Contraseña anterior es requerida")
	private String passant;
	
	@NotNull
	@NotBlank(message = "Nueva contraseña es requerido")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{8,}$",
			message = "La contraseña debe tener mínimo 8 caracteres, una mayúscula, una minúscula y un carácter especial")
	private String passnew;
	
	@NotNull
	@NotBlank(message = "Confirmacion de contraseña es requerida")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{8,}$",
			 message = "La confirmación de contraseña debe tener mínimo 8 caracteres, una mayúscula, una minúscula y un carácter especial")
	private String passcon;
}

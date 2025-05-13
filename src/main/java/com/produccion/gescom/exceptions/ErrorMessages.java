package com.produccion.gescom.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ErrorMessages {
	private static final Map<String, String> errorMessages = new HashMap<>();

    static {
        errorMessages.put("SEGU-0001", "El Usuario no existe");
        errorMessages.put("SEGU-0002", "La Sociedad no existe");
        errorMessages.put("SEGU-0003", "Login de Usuario ya existe");
        errorMessages.put("SEGU-0004", "El Perfil no existe");
        errorMessages.put("SEGU-0005", "Usuario no tiene Menú asignado");
        errorMessages.put("SEGU-0006", "Password anterior incorrecto");
        errorMessages.put("SEGU-0007", "Error Password nuevo y confirmación deben ser iguales");
    }
    // Método para obtener el mensaje de error por código
    public static String getErrorMessage(String codigoError) {
       return errorMessages.getOrDefault(codigoError, "Mensaje de error no disponible");
    }
}

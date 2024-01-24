package com.produccion.gescom.commons;

import org.springframework.stereotype.Component;

@Component
public class DatosVarios {
	public String seriadoNumero(Long val1, Long val2)
	{
		String formatted = String.format("%0" + val2 + "d", Integer.valueOf(String.valueOf(val1)));
		
		return formatted;
	}
	
	
}

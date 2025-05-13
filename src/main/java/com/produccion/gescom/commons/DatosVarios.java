package com.produccion.gescom.commons;

import java.util.Arrays;
import org.springframework.stereotype.Component;
import com.produccion.gescom.exceptions.MensajeResponse;

@Component
public class DatosVarios {
	public String seriadoNumero(Long val1, Long val2)
	{
		String formatted = String.format("%0" + val2 + "d", Integer.valueOf(String.valueOf(val1)));
		return formatted;
	}
	
	public MensajeResponse mensajeDev(Object dato, String mensajeok ) throws Exception {
		MensajeResponse mensaje = new MensajeResponse(
	        	1,
	        	dato,
	            "",           
	            mensajeok,
	            Arrays.asList(),
	            ""
	        );
		return mensaje;
	}
}

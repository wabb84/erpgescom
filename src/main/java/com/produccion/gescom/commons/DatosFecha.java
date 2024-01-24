package com.produccion.gescom.commons;

import java.time.ZonedDateTime;

import org.springframework.stereotype.Component;

@Component
public class DatosFecha {
	
	public String Obdatosfecha(String val1) {
		if (val1.equals("Z")) {
			return ZonedDateTime.now().getZone().toString();
		}
		else if (val1.equals("A")) {
			return String.valueOf(ZonedDateTime.now().getYear());
		}
		else if (val1.equals("M")) {
			return String.valueOf(ZonedDateTime.now().getMonthValue());
		}
		
		
		return null;
	}

}

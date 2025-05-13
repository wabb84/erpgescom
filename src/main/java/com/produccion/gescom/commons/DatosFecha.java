package com.produccion.gescom.commons;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
		else if (val1.equals("D")) {
			return String.valueOf(ZonedDateTime.now().getDayOfMonth());
		}
		else if (val1.equals("FH")) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			String formattedDate = ZonedDateTime.now().format(formatter);
			return formattedDate;
		}
		else if (val1.equals("F")) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String formattedDate = ZonedDateTime.now().format(formatter);
			return formattedDate;
		}
		else if (val1.equals("H")) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
			String formattedDate = ZonedDateTime.now().format(formatter);
			return formattedDate;
		}
		
		return null;
	}

}

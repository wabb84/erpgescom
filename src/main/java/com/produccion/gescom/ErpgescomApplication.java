package com.produccion.gescom;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
//@EntityScan(basePackages = {"com.produccion.gescom.entity"})
@PropertySource("classpath:basedatos.properties")
public class ErpgescomApplication {
	public static void main(String[] args) {
		SpringApplication.run(ErpgescomApplication.class, args);
		TimeZone tz = TimeZone.getTimeZone("America/Lima");
		TimeZone.setDefault(tz);
	}
}
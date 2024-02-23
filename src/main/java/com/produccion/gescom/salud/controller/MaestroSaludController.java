package com.produccion.gescom.salud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.produccion.gescom.salud.entity.Tiposangre;
import com.produccion.gescom.salud.services.TiposangreService;

@Controller
@CrossOrigin
@RequestMapping ("/maestrosalud")
public class MaestroSaludController {
	@Autowired
	private TiposangreService tiposangreservice;
	
	@PostMapping("/tiposangre")
	public ResponseEntity<?> ListaTiposangre() throws Exception {
		List<Tiposangre> tiposangre = tiposangreservice.Listatiposangre();
		return ResponseEntity.ok(tiposangre);
	}	
}

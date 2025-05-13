package com.produccion.gescom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.produccion.gescom.services.CatalogoTipoService;

@RestController
@RequestMapping ("/catalogo")
@CrossOrigin
public class CatalogoController {
	@Autowired
	
	private CatalogoTipoService catalogotiposervice;
	
	@PostMapping("/lista")
    public ResponseEntity<?> listaCatalogoTipo(){
		
	    return ResponseEntity.ok(catalogotiposervice.listaCatalogo(true)); 
	    		

	}
}

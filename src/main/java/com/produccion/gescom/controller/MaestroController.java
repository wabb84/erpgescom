package com.produccion.gescom.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.produccion.gescom.entity.Pais;
import com.produccion.gescom.entity.Rubro;
import com.produccion.gescom.entity.TipoDocumento;
import com.produccion.gescom.services.PaisService;
import com.produccion.gescom.services.RubroService;
import com.produccion.gescom.services.TipoDocumentoService;

@RestController
@RequestMapping ("/maestro")
@CrossOrigin
public class MaestroController {
	@Autowired
	private PaisService pais;

	@Autowired
	private RubroService rubro;
	
	@Autowired
	private TipoDocumentoService tipodocumento;
	
	@PostMapping("/tipodocumento")
    public ResponseEntity<?> ListaTipoDocumento(){
	    List<TipoDocumento> TipoDocumento = new ArrayList<>();
	    TipoDocumento = tipodocumento.listaTipoDocumento();
	    
	    return ResponseEntity.ok(TipoDocumento);
	}
	
	@PostMapping("/rubro")
    public ResponseEntity<?> ListaRubro(){
	    List<Rubro> Rubro = new ArrayList<>();
	    Rubro = rubro.listaRubro();
	    
	    return ResponseEntity.ok(Rubro);
	}
	
	@PostMapping("/pais")
    public ResponseEntity<?> ListaPais(){
	    List<Pais> Pais = new ArrayList<>();
	    Pais = pais.listaPais();
	    
	    return ResponseEntity.ok(Pais);
	}
}

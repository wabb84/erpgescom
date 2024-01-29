package com.produccion.gescom.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.produccion.gescom.dto.TipoBusquedaDto;
import com.produccion.gescom.dto.TipoBusquedaDtoR;
import com.produccion.gescom.entity.Pais;
import com.produccion.gescom.entity.Rubro;
import com.produccion.gescom.entity.TipoDocumento;
import com.produccion.gescom.services.PaisService;
import com.produccion.gescom.services.RubroService;
import com.produccion.gescom.services.TipoDocumentoService;
import com.produccion.gescom.services.TipobusquedaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping ("/maestro")
@CrossOrigin
public class MaestroController {
	@Autowired
	private PaisService paisser;

	@Autowired
	private RubroService rubroser;
	
	@Autowired
	private TipoDocumentoService tipodocumentoser;
	
	@Autowired
	private TipobusquedaService tipodobusquedaser;
	
	@PostMapping("/tipodocumento")
    public ResponseEntity<?> ListaTipoDocumento(){
	    List<TipoDocumento> TipoDocumento = new ArrayList<>();
	    TipoDocumento = tipodocumentoser.listaTipoDocumento();
	    
	    return ResponseEntity.ok(TipoDocumento);
	}
	
	@PostMapping("/rubro")
    public ResponseEntity<?> ListaRubro(){
	    List<Rubro> Rubro = new ArrayList<>();
	    Rubro = rubroser.listaRubro();
	    
	    return ResponseEntity.ok(Rubro);
	}
	
	@PostMapping("/pais")
    public ResponseEntity<?> ListaPais(){
	    List<Pais> Pais = new ArrayList<>();
	    Pais = paisser.listaPais();
	    
	    return ResponseEntity.ok(Pais);
	}
	
	@PostMapping("/tipobusqueda")
    public ResponseEntity<?> ListaTipoBusqueda(@Valid @RequestBody TipoBusquedaDtoR tipobusquedaDtoR, BindingResult result){
	    List<TipoBusquedaDto> tipobusqueda = new ArrayList<>();
	    
	    
	    tipobusqueda = tipodobusquedaser.listaTipoBusqueda(tipobusquedaDtoR.getTipo());
	    
	    return ResponseEntity.ok(tipobusqueda);
	}
}

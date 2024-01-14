package com.produccion.gescom.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.produccion.gescom.dto.UsuarioDatosLoginDto;
import com.produccion.gescom.dto.UsuarioDtoR;
import com.produccion.gescom.services.MenuService;
import com.produccion.gescom.services.UsuarioService;
import com.produccion.gescom.dto.MenulistaDto;

@RestController
@RequestMapping ("/usuario")
@CrossOrigin
public class UsuarioController {
	@Autowired
	private UsuarioService userservice;
	@Autowired
	private MenuService menuservice;

	@PostMapping("/datos")
	public ResponseEntity<?> ConsultaUsuarioLogin(@RequestBody UsuarioDtoR userDtoR) throws Exception {
		Map<String, Object> response = new HashMap<>();
		UsuarioDatosLoginDto usuariologindatos = userservice.FindByDatosLogin(userDtoR.getCodusuario());
		if (usuariologindatos == null){
			response.put("error", "No existe Usuario");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}
		//return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);
		return ResponseEntity.ok(usuariologindatos);
	}
	
	@PostMapping("/menu")
	public ResponseEntity<?> ListaMenu(@RequestBody UsuarioDtoR userDtoR)  throws Exception {
		Map<String, Object> response = new HashMap<>();
		List<MenulistaDto> menulista = menuservice.menulista(userDtoR.getIdusuario());;
		if (menulista == null){
			response.put("error", "Usuario No tiene Menú asignado");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}
		//return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);
		return ResponseEntity.ok(menulista);
	}
	
}

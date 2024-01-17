package com.produccion.gescom.controller;

//import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.produccion.gescom.dto.UsuarioDatosLoginDto;
import com.produccion.gescom.dto.UsuarioDtoR;
import com.produccion.gescom.dto.UsuarioListaDto;
import com.produccion.gescom.entity.EEstadoUsuario;
//import com.produccion.gescom.entity.ETipoPersona;
//import com.produccion.gescom.entity.Socieda;
import com.produccion.gescom.entity.UserEntity;
import com.produccion.gescom.services.MenuService;
import com.produccion.gescom.services.SociedaService;
//import com.produccion.gescom.services.UserDetailsServiceImpl;
import com.produccion.gescom.services.UsuarioService;

import jakarta.validation.Valid;

import com.produccion.gescom.dto.MenulistaDto;
import com.produccion.gescom.dto.SociedaDto;
//import com.produccion.gescom.dto.SociedaDtoR;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
@RestController
@RequestMapping ("/usuario")
@CrossOrigin
public class UsuarioController {
	//private static final Log logger = LogFactory.getLog(UsuarioController.class);
	
	//@Autowired
	//private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuarioService userservice;
	@Autowired
	private MenuService menuservice;
	
	@Autowired
	private SociedaService sociedaservice;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/lista")
	public ResponseEntity<?> ListaUsuario(@RequestBody UsuarioDtoR userDtoR) throws Exception {
		List<UsuarioListaDto> usuariolista = userservice.ListaUsuarios(userDtoR.getIdsocieda());
		return ResponseEntity.ok(usuariolista);
	}
	
	@PostMapping("/nuevo")
	public ResponseEntity<?> NuevaSocieda(@Valid @RequestBody UsuarioDtoR userDtoR, BindingResult result) throws Exception {
		Map<String, Object> response = new HashMap<>();
		String encriptPassword = "";
		SociedaDto socieda = sociedaservice.consulta(userDtoR.getIdsocieda());
		
		UsuarioDatosLoginDto usuariologindatos = userservice.FindByDatosLogin(userDtoR.getCodusuario()+'@'+socieda.getSerie());
		if (usuariologindatos != null){
			
			response.put("error", "Login de Usuario ya existe");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}
		
		UserEntity usuarionew = new UserEntity();
		usuarionew.setUsername(userDtoR.getCodusuario()+'@'+socieda.getSerie());
		encriptPassword = passwordEncoder.encode(userDtoR.getPassword());
		usuarionew.setPassword(encriptPassword);
		usuarionew.setNombreusuario(userDtoR.getDesusuario());
		usuarionew.setEmail(userDtoR.getEmail());
		usuarionew.setTelefono(userDtoR.getTelefono());
		usuarionew.setIdsocieda(userDtoR.getIdsocieda());
		usuarionew.setIdtipodoc(userDtoR.getIdtipodoc());
		usuarionew.setNumdocu(userDtoR.getNumerodoc());
		usuarionew.setEstadousuario(userDtoR.getEstadousario().equals("A") ? EEstadoUsuario.A : userDtoR.getEstadousario().equals("I") ? EEstadoUsuario.I :  EEstadoUsuario.T);
		usuarionew.setFechai(userDtoR.getFechaini());
		usuarionew.setFechaf(userDtoR.getFechafinal());
		usuarionew.setIdusuario(userDtoR.getIdusuario());
		usuarionew.setEstadopas("0");
		usuarionew.prePersist();
		
		try {
			userservice.save(usuarionew);
		    response.put("mensaje", "Usuario grabado con exito");
		} catch (Exception e) {
		      response.put("error", "Error al Grabar el Usuario : " + e.getMessage());
		      return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}    
		return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);
	}
	
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

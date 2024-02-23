package com.produccion.gescom.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.produccion.gescom.dto.MenuListaDtoR;
import com.produccion.gescom.dto.MenulistaDto;
import com.produccion.gescom.dto.PerfilDtoR;
import com.produccion.gescom.dto.UsuarioDtoR;
import com.produccion.gescom.entity.EVigencia;
import com.produccion.gescom.entity.Perfil;
import com.produccion.gescom.entity.Perfildet;
import com.produccion.gescom.services.PerfilService;
import com.produccion.gescom.dto.PerfilListaDto;
import com.produccion.gescom.dto.PerfilListasDto;

@RestController
@RequestMapping ("/perfil")
@CrossOrigin
public class PerfilController {
	//private static final Log logger = LogFactory.getLog(UsuarioController.class);
	@Autowired
	private PerfilService perfilservice;
	
	@PostMapping("/listanuevo")
	public ResponseEntity<?> ListaPerfilnuevo(@RequestBody UsuarioDtoR userDtoR) throws Exception {
		
		List<MenulistaDto> menulistanuevo = perfilservice.menulistaperfilnuevo(userDtoR.getIdsocieda());
		
		/*List<PerfilListaDto> perfillista = perfilservice.perfilLista(userDtoR.getIdsocieda());
		List<PerfilListasDto> perfillistafinal = new ArrayList<>();
		
		for(PerfilListaDto perfillistaline : perfillista) {
			PerfilListasDto perfillistadtonew = new PerfilListasDto();
			perfillistadtonew.setIdperfil(perfillistaline.getIdperfil());
			perfillistadtonew.setDesperfil(perfillistaline.getDesperfil());
			perfillistadtonew.setVigencia(perfillistaline.getVigencia());
			List<MenulistaDto> perfildetallelista = perfilservice.perfildetalleLista(perfillistaline.getIdperfil(), userDtoR.getIdsocieda());
			perfillistadtonew.setDetalles(perfildetallelista);
			perfillistafinal.add(perfillistadtonew);
		}*/
		return ResponseEntity.ok(menulistanuevo);
	}
	
	@PostMapping("/lista")
	public ResponseEntity<?> ListaPerfil(@RequestBody UsuarioDtoR userDtoR) throws Exception {
		List<PerfilListaDto> perfillista = perfilservice.perfilLista(userDtoR.getIdsocieda());
		List<PerfilListasDto> perfillistafinal = new ArrayList<>();
		
		for(PerfilListaDto perfillistaline : perfillista) {
			PerfilListasDto perfillistadtonew = new PerfilListasDto();
			perfillistadtonew.setIdperfil(perfillistaline.getIdperfil());
			perfillistadtonew.setDesperfil(perfillistaline.getDesperfil());
			perfillistadtonew.setVigencia(perfillistaline.getVigencia());
			List<MenulistaDto> perfildetallelista = perfilservice.perfildetalleLista(perfillistaline.getIdperfil(), userDtoR.getIdsocieda());
			perfillistadtonew.setDetalles(perfildetallelista);
			perfillistafinal.add(perfillistadtonew);
		}
		return ResponseEntity.ok(perfillistafinal);
	}

	/*@PostMapping("/listadetalle")
	public ResponseEntity<?> ListadetallePerfil(@RequestBody PerfilDtoR perfildtor) throws Exception {
		List<MenulistaDto> perfildetallelista = perfilservice.perfildetalleLista(perfildtor.getIdperfil());
		return ResponseEntity.ok(perfildetallelista);
	}*/
	
	@PostMapping("/nuevo")
	public ResponseEntity<?> nuevoPerfil(@RequestBody PerfilDtoR perfildtor) throws Exception {
		Map<String, Object> response = new HashMap<>();
		Perfil perfilnew = new Perfil();
		perfilnew.setDesperfil(perfildtor.getDesperfil());
		perfilnew.setIdsocieda(perfildtor.getIdsocieda());
		perfilnew.setVigencia(EVigencia.A);
		perfilnew.setIdusuario(perfildtor.getIdusuario());
		perfilnew.prePersist();

		List<Perfildet> perfildetallenew = new ArrayList<>();
		List<MenuListaDtoR> menulistaperfil = perfildtor.getDetalleperfil(); 
		for(MenuListaDtoR menulistaline : menulistaperfil) {
			if (menulistaline.getAcceso().equals("1")) {
				Perfildet perfildetnew = new Perfildet();
				perfildetnew.setPerfilmapdet(perfilnew);
				perfildetnew.setIdmenu(menulistaline.getIdmenu());
				perfildetnew.setIdmenurubro(menulistaline.getIdmenurubro());
				perfildetnew.setAcceso("1");
				perfildetallenew.add(perfildetnew);
			}
			//System.out.println(menulistaline.getDesmenu());
		}
		perfilnew.setPerfildet(perfildetallenew);
		
		try {
			perfilservice.save( perfilnew );
		    response.put("mensaje", "Nuevo perfil grabado con exito");
		} catch (Exception e) {
		      response.put("error", "Error al Grabar Nuevo Perfil : " + e.getMessage());
		      return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}    
		return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);	
	}
	
	@PostMapping("/edita")
	public ResponseEntity<?> editaPerfil(@RequestBody PerfilDtoR perfildtor) throws Exception {
		Map<String, Object> response = new HashMap<>();
		
		Perfil perfiledit = perfilservice.edita(perfildtor.getIdperfil());
		perfiledit.setDesperfil(perfildtor.getDesperfil());
		perfiledit.setVigencia(perfildtor.getVigencia().equals("A") ? EVigencia.A : EVigencia.I);
		perfiledit.setIdusuariom(perfildtor.getIdusuario());

		List<Perfildet> perfildetallenew = new ArrayList<>();
		List<MenuListaDtoR> menulistaperfil = perfildtor.getDetalleperfil(); 
		for(MenuListaDtoR menulistaline : menulistaperfil) {
			if (menulistaline.getIdperfildet() == 0L) {
				if (menulistaline.getAcceso().equals("1")) {
					Perfildet perfildetnew = new Perfildet();
					perfildetnew.setPerfilmapdet(perfiledit);
					perfildetnew.setIdmenurubro(menulistaline.getIdmenurubro());
					perfildetnew.setIdmenu(menulistaline.getIdmenu());
					perfildetnew.setAcceso("1");
					perfildetallenew.add(perfildetnew);
				}
			}
			else {
				Perfildet perfildetedit = perfilservice.editadet(menulistaline.getIdperfildet());
				if (perfildetedit.getAcceso() != menulistaline.getAcceso()) {
					perfildetedit.setAcceso(menulistaline.getAcceso());
					perfilservice.savedet( perfildetedit );
					
				}
			}
			//System.out.println(menulistaline.getDesmenu());
		}
		perfiledit.setPerfildet(perfildetallenew);
		
		try {
			perfilservice.save( perfiledit );
		    response.put("mensaje", "Edición del Perfil grabado con exito");
		} catch (Exception e) {
		      response.put("error", "Error al Grabar Edición de Perfil : " + e.getMessage());
		      return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}    
		return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);	
	}
}
package com.produccion.gescom.controller;

import java.awt.Menu;
import java.util.ArrayList;
import java.util.Date;
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
import com.produccion.gescom.dto.UsuarioEditaDto;
import com.produccion.gescom.dto.UsuarioListaDto;
import com.produccion.gescom.dto.ValidaMenuDtoR;
import com.produccion.gescom.dto.ValidacionesDto;
import com.produccion.gescom.entity.EEstadoUsuario;
import com.produccion.gescom.entity.ESexo;
import com.produccion.gescom.entity.ETipoPersona;
import com.produccion.gescom.entity.MenuPrueba;
import com.produccion.gescom.entity.Socieda;
//import com.produccion.gescom.entity.ETipoPersona;
//import com.produccion.gescom.entity.Socieda;
import com.produccion.gescom.entity.UserEntity;
import com.produccion.gescom.repository.MenuPruebaRepository;
import com.produccion.gescom.services.MenuService;
import com.produccion.gescom.services.SociedaService;
//import com.produccion.gescom.services.UserDetailsServiceImpl;
import com.produccion.gescom.services.UsuarioService;
import jakarta.validation.Valid;

import com.produccion.gescom.dto.MenulistaDto;
import com.produccion.gescom.dto.ReinicioPasswordDtoR;
import com.produccion.gescom.dto.SociedaDto;
import com.produccion.gescom.dto.SociedaDtoR;

//import com.produccion.gescom.dto.SociedaDtoR;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
@RestController
@RequestMapping ("/usuario")
@CrossOrigin
public class UsuarioController {
	private static final Log logger = LogFactory.getLog(UsuarioController.class);
	
	//@Autowired
	//private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuarioService userservice;
	
	@Autowired
	private MenuService menuservice;
	
	@Autowired
	private MenuPruebaRepository menuprueba;
	
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
		usuarionew.setSexo(userDtoR.getSexo().equals("M") ? ESexo.M : ESexo.F);
		usuarionew.setFechai(userDtoR.getFechaini());
		usuarionew.setFechaf(userDtoR.getFechafin());
		usuarionew.setIdusuario(userDtoR.getIdusuario());
		usuarionew.setEstadopas("1");
		usuarionew.prePersist();
		
		try {
			userservice.save(usuarionew);
		    response.put("mensaje", "Usuario grabado con exito");
		} catch (Exception e) {
		      response.put("error", "Error al Grabar el Usuario : " + e.getMessage());
		      return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}    
		//return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);
		return ResponseEntity.ok(usuarionew);
	}
	
	@PostMapping("/reinicio")
	public ResponseEntity<?> reinicioPassword(@RequestBody ReinicioPasswordDtoR reinicioDtoR) throws Exception {
		Map<String, Object> response = new HashMap<>();
		UserEntity user = userservice.edita(reinicioDtoR.getIdusuario());
		
		if (user == null){
			response.put("error", "Error Usuario no existe");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}
		if (!passwordEncoder.matches(reinicioDtoR.getPassant(), user.getPassword()))
		{
			response.put("error", "Error Password anterior incorrecto");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}
		
		if (!reinicioDtoR.getPassnew().equals(reinicioDtoR.getPasscon()) )
		{
			response.put("error", "Error Password Nuevo y confirmación deben ser iguales");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}
				
		user.setPassword(passwordEncoder.encode(reinicioDtoR.getPassnew())); 
		
		try {
			userservice.save(user);
		    response.put("mensaje", "Password actualizado con éxito");
		} catch (Exception e) {
		      response.put("Error", "Error al Grabar la actualización del Password");
		      return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}   
		return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);
	}
	
	@PostMapping("/consulta")
	public ResponseEntity<?> ConsultaUsuario(@RequestBody UsuarioDtoR userDtoR) throws Exception {
		Map<String, Object> response = new HashMap<>();
		UsuarioEditaDto usuarioedita = userservice.consulta(userDtoR.getIdusuario());
		if (usuarioedita == null){
			response.put("error", "No existe el Usuario");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}
		//return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);
		return ResponseEntity.ok(usuarioedita);
	}	
	
	@PostMapping("/actualiza")
	public ResponseEntity<?> ActualizaSocieda(@Valid @RequestBody UsuarioDtoR userDtoR, BindingResult result) throws Exception {
		Map<String, Object> response = new HashMap<>();
		
		UserEntity useredit = userservice.edita(userDtoR.getIdusuario());
		if (useredit == null){
			response.put("error", "No existe el Usuario");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}
		
		useredit.setNombreusuario(userDtoR.getDesusuario());
		useredit.setEmail(userDtoR.getEmail());
		useredit.setTelefono(userDtoR.getTelefono());
		useredit.setIdtipodoc(userDtoR.getIdtipodoc());
		useredit.setNumdocu(userDtoR.getNumerodoc());
		useredit.setEstadousuario(userDtoR.getEstadousario().equals("A") ? EEstadoUsuario.A : userDtoR.getEstadousario().equals("I") ? EEstadoUsuario.I :  EEstadoUsuario.T);
		
		useredit.setFechai(userDtoR.getFechaini());
		useredit.setFechaf(userDtoR.getFechafin());
		useredit.setEstadopas(userDtoR.getEstadopas());
		useredit.setSexo(userDtoR.getSexo().equals("M") ? ESexo.M : ESexo.F);
		
		try {
			userservice.save(useredit);
		    response.put("mensaje", "Usuario grabado con exito");
		} catch (Exception e) {
		      response.put("Error", "Error al Grabar el Usuario : " + e.getMessage());
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
		List<MenulistaDto> menulista = menuservice.menulista(userDtoR.getIdusuario());
		if (menulista == null){
			response.put("error", "Usuario No tiene Menú asignado");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}
		//return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);
		return ResponseEntity.ok(menulista);
	}
	
	@PostMapping("/menutemporal")
	public ResponseEntity<?> ListaMenuFinal(@RequestBody UsuarioDtoR userDtoR) throws Exception {
		List<com.produccion.gescom.entity.Menu> menulistatemporal = menuservice.menulistatemporal(userDtoR.getIdusuario());
				
		/*List<MenuPrueba> menus = roles.stream()
				.flatMap(role -> role.getMenus().stream())
                .distinct()
                .collect(Collectors.toList());*/
		
		//List<MenuPrueba> menulistaprueba = menuprueba.menuLista(4L);
		
		//List<MenulistaDto> menulistaprueba = menuprueba.menuListaFinal(1L);

		return ResponseEntity.ok(menulistatemporal);
	}
	
	@PostMapping("/validamenutemporal")
	public ResponseEntity<?> ValidaMenu(@RequestBody ValidaMenuDtoR validamenuR) throws Exception {
		Map<String, Object> response = new HashMap<>();
		//logger.info(validamenuR.getIdusuario());
		//logger.info(validamenuR.getIdmenu());
		ValidacionesDto validamenu = menuservice.FindByMenu(validamenuR.getIdusuario(), validamenuR.getIdmenu());
		if (validamenu.getCantidad() == 0){
			response.put("error", "Usuario con acceso restringido");
			return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		}
		
		response.put("mensaje", "Acceso Permitido");
		return ResponseEntity.ok(response);
	}
	
	
	@PostMapping("/menuv1")
	public ResponseEntity<?> ListaMenuv1()  throws Exception {
		//Map<String, Object> response = new HashMap<>();
		List<com.produccion.gescom.entity.Menu> menulista = menuservice.menulistafinal();
		//if (menulista == null){
		//	response.put("error", "Usuario No tiene Menú asignado");
		//	return new ResponseEntity<Map<String,Object>>(response , HttpStatus.BAD_REQUEST);
		//}
		//return new ResponseEntity<Map<String,Object>>(response , HttpStatus.OK);
		return ResponseEntity.ok(menulista);
	}
	
	
	@PostMapping("/menuprueba")
	public ResponseEntity<?> ListaMenuPrueba() throws Exception {

		List<MenuPrueba> menulistaprueba = menuprueba.findByParentidIsNull();
		
		//List<MenuPrueba> listamenu= new ArrayList<MenuPrueba>();
		
		/*List<MenuPrueba> menus = roles.stream()
				.flatMap(role -> role.getMenus().stream())
                .distinct()
                .collect(Collectors.toList());*/
		
		//List<MenuPrueba> menulistaprueba = menuprueba.menuLista(4L);
		
		//List<MenuPrueba> menulistaprueba = menuprueba.menuLista();

		return ResponseEntity.ok(menulistaprueba);
	}
	
	@PostMapping("/menuprueba1")
	public ResponseEntity<?> ListaMenuFinal() throws Exception {
		List<MenuPrueba> menulistaprueba2 = menuprueba.menuListaFinal();
				
		/*List<MenuPrueba> menus = roles.stream()
				.flatMap(role -> role.getMenus().stream())
                .distinct()
                .collect(Collectors.toList());*/
		
		//List<MenuPrueba> menulistaprueba = menuprueba.menuLista(4L);
		
		//List<MenulistaDto> menulistaprueba = menuprueba.menuListaFinal(1L);

		return ResponseEntity.ok(menulistaprueba2);
	}
	
	
	
	@PostMapping("/menuprueba2")
	public ResponseEntity<?> ListaMenuPrueba2() throws Exception {

		List<MenuPrueba> menulistaprueba = menuprueba.findByParentidIsNull();
		
		//List<MenuPruebaDto> menulistaprueba = menuprueba.menuLista2(4L);
		

		return ResponseEntity.ok(menulistaprueba);
	}
	
	@PostMapping("/datosprueba")
	public ResponseEntity<?> ConsultaUsuarioLogin2() throws Exception {
		//Map<String, Object> response = new HashMap<>();
		Long idusuario = 5L;
		//logger.info("wbarrantes1");
		UserEntity usuariologindatos = userservice.Datospruebauser(idusuario);

		return ResponseEntity.ok(usuariologindatos);
	}
	
}

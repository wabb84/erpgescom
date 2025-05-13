package com.produccion.gescom.services.impl;

import java.util.List;

//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.produccion.gescom.repository.MenuRepository;
//import com.produccion.gescom.repository.PerfilRepository;
//import com.produccion.gescom.repository.SociedaRepository;
//import com.produccion.gescom.repository.UserLoginRepository;
//import com.produccion.gescom.repository.UsuarioperRepository;
import com.produccion.gescom.services.MenuService;
import com.produccion.gescom.commons.UserContextHolder;
import com.produccion.gescom.dto.MenulistaDto;
//import com.produccion.gescom.dto.ValidacionesDto;
//import com.produccion.gescom.entity.Menu;
import com.produccion.gescom.exceptions.CustomException;
//import com.produccion.gescom.mapper.UsuarioMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
	
	private final MenuRepository menurepository;
	private Long idusuario;
	
	@Override
	public List<MenulistaDto> menulista() {
		idusuario = UserContextHolder.getUserId();
		//System.out.println(idusuario);
		List<MenulistaDto> menulista = menurepository.menuLista(idusuario);
		if (menulista == null || menulista.isEmpty()){
			throw new CustomException("SEGU-0005");
		}
   		return menulista;
   	}	
	
	/*@Override
	public List<Menu> menulistafinal(){
		return menuRepository.findByIdpadreIsNull();
	};
	
	public List<Menu> menulistatemporal(Long idusuario)
	{
		return menuRepository.menuListaTemporal(idusuario);
	};
	
	public ValidacionesDto FindByMenu(Long idusuario, Long idmenu) {
		return menuRepository.validaMenu(idusuario, idmenu);
	};*/
}

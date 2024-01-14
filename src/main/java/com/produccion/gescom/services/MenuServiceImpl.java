package com.produccion.gescom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.produccion.gescom.repository.MenuRepository;
import com.produccion.gescom.dto.MenulistaDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
	
	@Autowired
    private MenuRepository menuRepository;
	
	@Override
	public List<MenulistaDto> menulista(Long idusuario) {
   		return menuRepository.menuLista(idusuario);
   	}	
}

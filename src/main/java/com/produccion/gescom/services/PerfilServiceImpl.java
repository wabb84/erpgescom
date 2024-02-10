package com.produccion.gescom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produccion.gescom.dto.MenulistaDto;
import com.produccion.gescom.dto.PerfilListaDto;
import com.produccion.gescom.entity.Perfil;
import com.produccion.gescom.entity.Perfildet;
import com.produccion.gescom.repository.PerfilRepository;
import com.produccion.gescom.repository.PerfildetRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PerfilServiceImpl implements PerfilService {
	
	@Autowired
	private PerfilRepository perfilrep;

	@Autowired
	private PerfildetRepository perfildetrep;

	
	@Override
	public List<PerfilListaDto> perfilLista(Long idsocieda) {
		return perfilrep.perfilLista(idsocieda);
	}
	
	@Override
	public List<MenulistaDto> perfildetalleLista(Long idperfil) {
		return perfilrep.perfildetalleLista(idperfil);
	}
	
	@Override
	public Perfil save(Perfil perfil) {
		return perfilrep.save(perfil);
	};
	
	@Override
	public Perfil edita(Long idperfil) {
		return perfilrep.findById(idperfil).orElse(null);
	}
	
	@Override
	public Perfildet editadet(Long idperfildet) {
		
		return perfildetrep.findById(idperfildet).orElse(null);
	};
	
	public Perfildet savedet(Perfildet perfildet) {
		return perfildetrep.save(perfildet);
	};
	
	/*@Override
	public Perfildet editadetval(Long idperfil, Long idmenurubro) {
		return perfildetrep.findByIdperfilAndIdmenurubro(idperfil, idmenurubro);
		
	};*/
}
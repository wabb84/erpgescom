package com.produccion.gescom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.produccion.gescom.dto.SociedaDto;
import com.produccion.gescom.dto.SociedaListaDto;
import com.produccion.gescom.entity.Socieda;
import com.produccion.gescom.repository.SociedaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SociedaServiceImpl implements SociedaService {
	
	@Autowired
	private SociedaRepository sociedarep;
	
	@Override
	public Socieda save(Socieda socieda) {
		return sociedarep.save(socieda);
	}	
	
	@Override
   	public SociedaDto consulta(Long idsocieda) {
   		return sociedarep.FindBySocieda(idsocieda);
   	}	

	@Override
	public Socieda edita(Long sociedaid) {
		return sociedarep.findById(sociedaid).orElse(null);
	}
	
	@Override
	public List<SociedaListaDto> sociedaLista() {
		return sociedarep.sociedaLista();
	}
	
}
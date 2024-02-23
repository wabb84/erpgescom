package com.produccion.gescom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produccion.gescom.dto.DepartamentoDto;
import com.produccion.gescom.dto.DistritoDto;
import com.produccion.gescom.dto.ProvinciaDto;
import com.produccion.gescom.repository.UbigeoRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UbigeoServiceImpl  implements UbigeoService {
	@Autowired
    private UbigeoRepository ubigeorep;
	
	@Override
	public List<DepartamentoDto> Listadepartamentos(){
		return ubigeorep.listadepartamentos();
	};
	
	@Override
	public List<ProvinciaDto> Listaprovincias(String departamento){
		return ubigeorep.listaprovincias(departamento);
	};
	
	@Override
	public List<DistritoDto> Listadistritos(String departamento, String provincia){
		return ubigeorep.listadistritos(departamento,provincia);
	};
	
}

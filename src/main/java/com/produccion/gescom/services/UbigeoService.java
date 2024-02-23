package com.produccion.gescom.services;

import java.util.List;

import com.produccion.gescom.dto.DepartamentoDto;
import com.produccion.gescom.dto.DistritoDto;
import com.produccion.gescom.dto.ProvinciaDto;

public interface UbigeoService {
	public List<DepartamentoDto> Listadepartamentos();
	public List<ProvinciaDto> Listaprovincias(String departamento);
	public List<DistritoDto> Listadistritos(String departamento, String provincia);
}

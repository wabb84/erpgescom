package com.produccion.gescom.services;

import java.util.List;

import com.produccion.gescom.dto.SociedaDto;
import com.produccion.gescom.dto.SociedaListaDto;
import com.produccion.gescom.entity.Socieda;

public interface SociedaService {
	public Socieda save(Socieda socieda);
	public SociedaDto consulta(Long idsocieda);
	public List<SociedaListaDto> sociedaLista();
	public Socieda edita(Long idsocieda);
}

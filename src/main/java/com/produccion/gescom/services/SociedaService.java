package com.produccion.gescom.services;

import com.produccion.gescom.dto.SociedaDto;
import com.produccion.gescom.entity.Socieda;

public interface SociedaService {
	public Socieda save(Socieda socieda);
	public SociedaDto consulta(Long idsocieda);
	public Socieda edita(Long idsocieda);
}

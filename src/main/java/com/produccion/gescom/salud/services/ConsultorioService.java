package com.produccion.gescom.salud.services;

import java.util.List;

import com.produccion.gescom.salud.dto.ConsultorioDto;
import com.produccion.gescom.salud.entity.Consultorio;

public interface ConsultorioService {
	public Consultorio save(Consultorio consultorio);
	public ConsultorioDto consulta(Long idconsultorio);
	public List<ConsultorioDto> consultorioLista(Long idsocieda);
	public Consultorio edita(Long idconsultorio);
}

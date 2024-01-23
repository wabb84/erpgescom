package com.produccion.gescom.services;

import java.util.List;

import com.produccion.gescom.dto.ConsultorioDto;
import com.produccion.gescom.entity.Consultorio;

public interface ConsultorioService {
	public Consultorio save(Consultorio consultorio);
	public ConsultorioDto consulta(Long idconsultorio);
	public List<ConsultorioDto> consultorioLista(Long idsocieda);
	public Consultorio edita(Long idconsultorio);
}

package com.produccion.gescom.services;

import java.util.List;

import com.produccion.gescom.entity.Cita;
import com.produccion.gescom.entity.Seriexdoc;

public interface CitasService {
	public Cita save(Cita cita, Seriexdoc seriexdoc);
	
	/*public ConsultorioDto consulta(Long idconsultorio);
	public List<ConsultorioDto> consultorioLista(Long idsocieda);
	public Consultorio edita(Long idconsultorio);*/
}

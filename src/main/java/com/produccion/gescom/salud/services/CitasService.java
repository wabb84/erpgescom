package com.produccion.gescom.salud.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.produccion.gescom.entity.Seriexdoc;
import com.produccion.gescom.salud.dto.CitasListadoDto;
import com.produccion.gescom.salud.entity.Cita;

public interface CitasService {
	public Cita save(Cita cita, Seriexdoc seriexdoc);
	public List<CitasListadoDto> ListadoCitas(String pfechai, String pfechaf, Long pturno, Long ppaciente, Long pmedico, String pestado);
	
	/*public ConsultorioDto consulta(Long idconsultorio);
	public List<ConsultorioDto> consultorioLista(Long idsocieda);
	public Consultorio edita(Long idconsultorio);*/
}

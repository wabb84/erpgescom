package com.produccion.gescom.salud.services;


import java.util.List;
import com.produccion.gescom.entity.Seriexdoc;
import com.produccion.gescom.salud.dto.CitasListadoDto;
import com.produccion.gescom.salud.entity.Agenda;
import com.produccion.gescom.salud.entity.Cita;

public interface CitasService {
	public Cita save(Cita cita, Seriexdoc seriexdoc, Agenda agenda);
	public List<CitasListadoDto> ListadoCitas(String pfechai, String pfechaf, Long pturno, Long ppaciente, Long pmedico, String pestado, Long idsocieda);
	public Cita buscacita(Long idcita);
	public Cita saveedicion(Cita cita, Agenda agenda);
	public Cita saveadicional(Agenda agenda, Agenda agendanew, Cita citanew, Seriexdoc seriexdoc);
}

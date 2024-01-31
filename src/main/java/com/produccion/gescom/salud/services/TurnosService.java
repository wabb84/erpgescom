package com.produccion.gescom.salud.services;

import java.util.List;

import com.produccion.gescom.salud.dto.TurnosDto;
import com.produccion.gescom.salud.entity.Turnos;

public interface TurnosService {
	public Turnos save(Turnos turnos);
	public TurnosDto consulta(Long idturnos);
	public List<TurnosDto> turnosLista(Long idsocieda);
	public Turnos edita(Long idturnos);
	public List<TurnosDto> turnosListaCita(Long idsocieda);
}

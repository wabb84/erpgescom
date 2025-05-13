package com.produccion.gescom.services;

import java.time.LocalDate;
import java.util.List;

import com.produccion.gescom.dto.FeriadosDto;
import com.produccion.gescom.entity.Feriados;
public interface FeriadosService {
	public List<FeriadosDto> listaferiados(String anio, Long idsocieda);
	public Feriados save(Feriados feriados);
	public FeriadosDto buscaferiado(Long idsocieda, LocalDate fechaferia);
	public Feriados edita(Long idferiado);
	public void elimina(Long idferiado);
}

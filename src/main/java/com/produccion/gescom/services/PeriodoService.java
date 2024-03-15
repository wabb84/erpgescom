package com.produccion.gescom.services;

import java.util.List;

import com.produccion.gescom.dto.PeriodoDto;
import com.produccion.gescom.entity.Periodo;

public interface PeriodoService {
	public List<PeriodoDto> ListaAnios();
	public List<PeriodoDto> ListaMeses(String anio);
	public Periodo BuscaPeriodo(String anio, String Mes);
}

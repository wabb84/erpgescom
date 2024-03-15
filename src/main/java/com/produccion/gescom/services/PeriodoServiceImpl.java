package com.produccion.gescom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produccion.gescom.dto.PeriodoDto;
import com.produccion.gescom.entity.Periodo;
import com.produccion.gescom.repository.PeriodoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PeriodoServiceImpl implements PeriodoService  {
	@Autowired
	private PeriodoRepository periodorep;
	
	@Override
	public List<PeriodoDto> ListaAnios(){
		return periodorep.ListaAnios();
	};
	
	@Override
	public List<PeriodoDto> ListaMeses(String anio){
		return periodorep.ListaMeses(anio);
	};
	
	@Override
	public Periodo BuscaPeriodo(String anio, String mes)
	{
		return periodorep.findByAnioAndMes(anio, mes);
	};
	
}

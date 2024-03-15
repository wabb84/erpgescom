package com.produccion.gescom.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produccion.gescom.dto.FeriadosDto;
import com.produccion.gescom.entity.Feriados;
import com.produccion.gescom.repository.FeriadosRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeriadosServiceImpl implements FeriadosService {
	@Autowired
    private FeriadosRepository feriadorep;
	
	@Override
	public List<FeriadosDto> listaferiados(Long idsocieda){
		return feriadorep.findByIdsociedaOrderByFechaferia(idsocieda);
	};
	
	@Override
	public Feriados save(Feriados feriados) {
		return feriadorep.save(feriados);
	};

	@Override
	public FeriadosDto buscaferiado(Long idsocieda, LocalDate fechaferia) {
		return feriadorep.findByIdsociedaAndFechaferia(idsocieda, fechaferia);
	};
	
	@Override
	public Feriados edita(Long idferiado) {
		return feriadorep.findById(idferiado).orElse(null);
	}
	
	@Override
	public void elimina(Long idferiado) {
		feriadorep.deleteById(idferiado);
	}

}

package com.produccion.gescom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produccion.gescom.dto.TurnosDto;
import com.produccion.gescom.entity.Turnos;
import com.produccion.gescom.repository.TurnosRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TurnosServiceImpl implements TurnosService {
	
	@Autowired
	private TurnosRepository turnosrep;
	
	@Override
	public Turnos save(Turnos turnos) {
		return turnosrep.save(turnos);
	}	
	
	@Override
   	public TurnosDto consulta(Long idturnos) {
   		return turnosrep.FindByTurnos(idturnos);
   	}	

	@Override
	public List<TurnosDto> turnosLista(Long idsocieda){
		return turnosrep.TurnosLista(idsocieda);
	}
	
	@Override
	public Turnos edita(Long idconsultorio) {
		return turnosrep.findById(idconsultorio).orElse(null);
	}
}
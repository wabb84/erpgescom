package com.produccion.gescom.salud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.produccion.gescom.salud.entity.Especialida;
import com.produccion.gescom.salud.repository.EspecialidaRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class EspecialidaServiceImpl implements EspecialidaService  {
	@Autowired
	private EspecialidaRepository especialidarep;
	
	public List<Especialida> listaespecialida(Long idsocieda){
		return especialidarep.findByIdsocieda(idsocieda);
	};
	
	@Override
	public Especialida save(Especialida especialida) {
		return especialidarep.save(especialida);
	}	
	
	@Override
	public Especialida edita(Long idespecialida) {
		return especialidarep.findById(idespecialida).orElse(null);
	}
}

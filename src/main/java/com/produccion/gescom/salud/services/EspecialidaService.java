package com.produccion.gescom.salud.services;

import java.util.List;

import com.produccion.gescom.salud.entity.Especialida;


public interface EspecialidaService {
	public List<Especialida> listaespecialida(Long idsocieda);
	public Especialida save(Especialida especialida);
	public Especialida edita(Long idespecialida);
}

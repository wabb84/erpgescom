package com.produccion.gescom.salud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produccion.gescom.salud.entity.Tiposangre;
import com.produccion.gescom.salud.repository.TiposangreRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class TipoSangreServiceImpl implements TiposangreService {
	
	@Autowired
	private TiposangreRepository tiposangrerep;
	
	public List<Tiposangre> Listatiposangre(){
		return tiposangrerep.findAll();
	};
}

package com.produccion.gescom.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produccion.gescom.entity.Pais;
import com.produccion.gescom.repository.PaisRepository;
import com.produccion.gescom.services.PaisService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaisServiceImpl implements PaisService {
	@Autowired
	private PaisRepository paisrep;
	
	@Override
	public List<Pais> listaPais()
	{
		return paisrep.findAll();
	}	
}

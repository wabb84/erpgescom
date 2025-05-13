package com.produccion.gescom.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produccion.gescom.entity.Rubro;
import com.produccion.gescom.repository.RubroRepository;
import com.produccion.gescom.services.RubroService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RubroServiceImpl implements RubroService{
	@Autowired
	private RubroRepository rubrorep;
	
	@Override
	public List<Rubro> listaRubro()
	{
		return rubrorep.findAll();
	}	
}

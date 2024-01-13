package com.produccion.gescom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produccion.gescom.entity.Rubro;
import com.produccion.gescom.repository.RubroRepository;

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

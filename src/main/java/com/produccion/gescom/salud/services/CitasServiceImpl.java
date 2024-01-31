package com.produccion.gescom.salud.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produccion.gescom.entity.Seriexdoc;
import com.produccion.gescom.repository.SeriexdocRepository;
import com.produccion.gescom.salud.dto.CitasListadoDto;
import com.produccion.gescom.salud.entity.Cita;
import com.produccion.gescom.salud.repository.CitaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

@Service
@RequiredArgsConstructor
public class CitasServiceImpl implements CitasService {
	//private static final Log logger = LogFactory.getLog(CitasServiceImpl.class);
	
	@Autowired
	private CitaRepository citarep;
	
	@Autowired
	private SeriexdocRepository seriexdocrep;
	
	@Override
	@Transactional
	public Cita save(Cita cita, Seriexdoc seriexdoc) {
		seriexdocrep.save(seriexdoc);
		return citarep.save(cita);
	}	
	
	@Override
	public List<CitasListadoDto> ListadoCitas(String pfechai, String pfechaf, Long pturno, Long ppaciente, Long pmedico, String pestado)
	{
		//Date fechaIni = Date.valueOf(pfechai);
        //Date fechaFin = Date.valueOf(pfechaf);
        
        //Date date = Date.valueOf(localDate);
        
        //Date fechaIni = java.util.Date.from(pfechai.atStartOfDay());
        //Date fechaFin = java.util.Date.from(pfechai.atStartOfDay());
        
        //java.util.Date fechaIni = java.util.Date.from(pfechai.atStartOfDay().toInstant());
        //java.util.Date fechaFin = java.util.Date.from(pfechaf.atStartOfDay().toInstant());
        
        //Date fechaIni = Date.from(pfechai.atStartOfDay(ZoneId.systemDefault()).toInstant());
        //Date fechaFin = Date.from(pfechai.atStartOfDay(ZoneId.systemDefault()).toInstant());
        
		return citarep.ListadoCitas(pfechai, pfechaf, pturno, ppaciente, pmedico, pestado);
	}
}
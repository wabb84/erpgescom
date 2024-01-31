package com.produccion.gescom.salud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produccion.gescom.salud.dto.ConsultorioDto;
import com.produccion.gescom.salud.entity.Consultorio;
import com.produccion.gescom.salud.repository.ConsultorioRepository;

import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
@Service
@RequiredArgsConstructor
public class ConsultorioServiceImpl implements ConsultorioService {
	private static final Log logger = LogFactory.getLog(ConsultorioServiceImpl.class);
	
	@Autowired
	private ConsultorioRepository consultoriorep;
	
	@Override
	public Consultorio save(Consultorio consultorio) {
		return consultoriorep.save(consultorio);
	}	
	
	@Override
   	public ConsultorioDto consulta(Long idconsultorio) {
   		return consultoriorep.FindByConsultorio(idconsultorio);
   	}	

	@Override
	public List<ConsultorioDto> consultorioLista(Long idsocieda){
		logger.info(idsocieda);
		return consultoriorep.ConsultorioLista(idsocieda);
	}
	
	@Override
	public Consultorio edita(Long idconsultorio) {
		return consultoriorep.findById(idconsultorio).orElse(null);
	}
}
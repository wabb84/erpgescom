package com.produccion.gescom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.produccion.gescom.entity.Cita;
import com.produccion.gescom.repository.CitaRepository;

import lombok.RequiredArgsConstructor;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
@Service
@RequiredArgsConstructor
public class CitasServiceImpl implements CitasService {
	//private static final Log logger = LogFactory.getLog(CitasServiceImpl.class);
	
	@Autowired
	private CitaRepository citarep;
	
	@Override
	public Cita save(Cita cita) {
		return citarep.save(cita);
	}	
	
	/*@Override
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
	}*/
}
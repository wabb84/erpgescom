package com.produccion.gescom.salud.services;

import java.util.List;

import com.produccion.gescom.salud.dto.ConfAgenDto;
import com.produccion.gescom.salud.entity.Confagen;

public interface ConfagenService {
	public Confagen save( Confagen confagen );
	public List<ConfAgenDto> ListaConfAngenda(String anio, Long idsocieda);
	
	public ConfAgenDto VerificaBloqueo(String pfechai, String pfechaf, Long idpersprof, Long idsocieda);
	public ConfAgenDto VerificaBloqueoT(String pfechai, String pfechaf, Long idpersprof, Long idturnos, Long idsocieda);
	
	public ConfAgenDto BuscaAgenB(String pfechai, String pfechaf, Long idpersprof, Long idsocieda);
	public ConfAgenDto BuscaAgenBT(String pfechai, String pfechaf, Long idpersprof, Long idturnos, Long idsocieda);
	
}

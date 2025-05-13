package com.produccion.gescom.services;

import java.util.List;
import com.produccion.gescom.dto.MenulistaDto;
import com.produccion.gescom.dto.PerfilDtoReq;
import com.produccion.gescom.dto.PerfilDtoRes;
import com.produccion.gescom.dto.PerfilListaDto;
import com.produccion.gescom.dto.PerfilListasDto;
import com.produccion.gescom.dto.UsuarioDtoReq;
import com.produccion.gescom.dto.UsuarioDtoRes;
import com.produccion.gescom.entity.Perfil;
import com.produccion.gescom.entity.Perfildet;

public interface PerfilService {
	public List<PerfilListasDto> perfilLista(Long idsocieda);
	public PerfilDtoRes nuevo(PerfilDtoReq perfilDtoR);
	public PerfilDtoRes edita(PerfilDtoReq perfilDtoR);
	public List<MenulistaDto> menulistaperfilnuevo(Long idsocieda);
	
	/*public List<MenulistaDto> perfildetalleLista(Long idperfil, Long idsocieda);
	public List<MenulistaDto> menulistaperfilnuevo(Long idsocieda);
	public Perfil save(Perfil perfil);
	public Perfil edita(Long idperfil);
	public Perfildet editadet(Long idperfildet);
	public Perfildet savedet(Perfildet perfildet);*/
}

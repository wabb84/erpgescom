package com.produccion.gescom.services.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produccion.gescom.dto.MenuListaDtoR;
import com.produccion.gescom.dto.MenulistaDto;
import com.produccion.gescom.dto.PerfilDtoReq;
import com.produccion.gescom.dto.PerfilDtoRes;
import com.produccion.gescom.dto.PerfilListaDto;
import com.produccion.gescom.dto.PerfilListasDto;
import com.produccion.gescom.dto.SociedaDto;
import com.produccion.gescom.entity.EVigencia;
import com.produccion.gescom.entity.Perfil;
import com.produccion.gescom.entity.Perfildet;
import com.produccion.gescom.entity.UserEntity;
import com.produccion.gescom.exceptions.CustomException;
import com.produccion.gescom.mapper.PerfilMapper;
import com.produccion.gescom.repository.PerfilRepository;
import com.produccion.gescom.repository.PerfildetRepository;
import com.produccion.gescom.repository.SociedaRepository;
import com.produccion.gescom.services.PerfilService;

import jakarta.transaction.Transactional;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PerfilServiceImpl implements PerfilService {
	
	private final PerfilRepository perfilrepository;
	private final SociedaRepository sociedarepository;
	private final PerfildetRepository perfildetrepository;
	private final PerfilMapper perfilmapper;
	
	//private final PerfildetRepository perfildetrep;
	
	/*@Override
	public List<PerfilListasDto> perfilLista(Long idsocieda) {
		
		List<PerfilListaDto> perfillista = perfilrep.perfilLista(idsocieda);
		List<PerfilListasDto> perfillistafinal = new ArrayList<>();
		
		for(PerfilListaDto perfillistaline : perfillista) {
			PerfilListasDto perfillistadtonew = new PerfilListasDto();
			perfillistadtonew.setIdperfil(perfillistaline.getIdperfil());
			perfillistadtonew.setDesperfil(perfillistaline.getDesperfil());
			perfillistadtonew.setVigencia(perfillistaline.getVigencia());
			//List<MenulistaDto> perfildetallelista = perfilservice.perfildetalleLista(perfillistaline.getIdperfil(), userDtoR.getIdsocieda());
			List<MenulistaDto> perfildetallelista = perfilrep.perfildetalleLista(perfillistaline.getIdperfil(), idsocieda);
			perfillistadtonew.setDetalles(perfildetallelista);
			perfillistafinal.add(perfillistadtonew);
		}
		//return perfilrep.perfilLista(idsocieda);
		return perfillistafinal;
	}*/
	
	@Override
	public List<PerfilListasDto> perfilLista(Long idsocieda) {
		SociedaDto socieda = sociedarepository.FindBySocieda(idsocieda);
		if (socieda == null){
			throw new CustomException("SEGU-0002");
		}
		
	    return perfilrepository.perfilLista(idsocieda).stream()
	        .map(perfillistaline -> {
	            PerfilListasDto perfillistadtonew = new PerfilListasDto();
	            perfillistadtonew.setIdperfil(perfillistaline.getIdperfil());
	            perfillistadtonew.setDesperfil(perfillistaline.getDesperfil());
	            perfillistadtonew.setVigencia(perfillistaline.getVigencia());
	            perfillistadtonew.setDetalles(perfilrepository.perfildetalleLista(perfillistaline.getIdperfil(), idsocieda));
	            return perfillistadtonew;
	        })
	        .collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public PerfilDtoRes nuevo(PerfilDtoReq perfildtor) {
		SociedaDto socieda = sociedarepository.FindBySocieda(perfildtor.getIdsocieda());
		if (socieda == null){
			throw new CustomException("SEGU-0002");
		}
		
		Perfil perfilnew = perfilmapper.toPerfil(perfildtor);
		List<Perfildet> perfildetallenew = new ArrayList<>();
		List<MenuListaDtoR> menulistaperfil = perfildtor.getDetalleperfil(); 
		for(MenuListaDtoR menulistaline : menulistaperfil) {
			if (menulistaline.getAcceso().equals("1")) {
				Perfildet perfildetnew = new Perfildet();
				perfildetnew.setPerfilmapdet(perfilnew);
				perfildetnew.setIdmenu(menulistaline.getIdmenu());
				perfildetnew.setIdmenurubro(menulistaline.getIdmenurubro());
				perfildetnew.setAcceso("1");
				perfildetallenew.add(perfildetnew);
			}
		}
		perfilnew.setPerfildet(perfildetallenew);
		try {
			perfilnew.prePersist();
			perfilrepository.save( perfilnew );
		} catch (Exception e) {
		}
		return perfilmapper.PerfilTo(perfilnew);
	};
	
	@Override
	@Transactional
	public PerfilDtoRes edita(PerfilDtoReq perfildtor)
	{
		SociedaDto socieda = sociedarepository.FindBySocieda(perfildtor.getIdsocieda());
		if (socieda == null){
			throw new CustomException("SEGU-0002");
		}
		
		Perfil perfiledit = perfilrepository.findById(perfildtor.getIdperfil()).orElse(null);
		if (perfiledit == null){
			throw new CustomException("SEGU-0004");
		}
		perfiledit.setDesperfil(perfildtor.getDesperfil());
		perfiledit.setVigencia(perfildtor.getVigencia().equals("A") ? EVigencia.A : EVigencia.I);
		perfiledit.setIdusuariom(perfildtor.getIdusuario());

		List<Perfildet> perfildetallenew = new ArrayList<>();
		List<MenuListaDtoR> menulistaperfil = perfildtor.getDetalleperfil();
		for(MenuListaDtoR menulistaline : menulistaperfil) {
			//System.out.println("Aca me quede 2");
			//System.out.println(menulistaline.getIdperfildet());
			if (menulistaline.getIdperfildet() == 0L) {
				if (menulistaline.getAcceso().equals("1")) {
					Perfildet perfildetnew = new Perfildet();
					perfildetnew.setPerfilmapdet(perfiledit);
					perfildetnew.setIdmenurubro(menulistaline.getIdmenurubro());
					perfildetnew.setIdmenu(menulistaline.getIdmenu());
					perfildetnew.setAcceso("1");
					perfildetallenew.add(perfildetnew);
				}
			}
			else {
				
				Perfildet perfildetedit = perfildetrepository.findById(menulistaline.getIdperfildet()).orElse(null);
				if (perfildetedit.getAcceso() != menulistaline.getAcceso()) {
					perfildetedit.setAcceso(menulistaline.getAcceso());
					perfildetrepository.save(perfildetedit);
				}
			}
			//System.out.println(menulistaline.getDesmenu());
		}
		perfiledit.setPerfildet(perfildetallenew);
		return perfilmapper.PerfilTo(perfiledit);
	};
	
	public List<MenulistaDto> menulistaperfilnuevo(Long idsocieda)
	{
		SociedaDto socieda = sociedarepository.FindBySocieda(idsocieda);
		if (socieda == null){
			throw new CustomException("SEGU-0002");
		}
		return perfilrepository.listamenuperfilnuevo(idsocieda);
	};
	
	/*@Override
	public List<MenulistaDto> perfildetalleLista(Long idperfil, Long idsocieda) {
		return perfilrep.perfildetalleLista(idperfil, idsocieda);
	}
	
	@Override
	public List<MenulistaDto> menulistaperfilnuevo(Long idsocieda){
		return perfilrep.listamenuperfilnuevo(idsocieda);
	};
	
	@Override
	public Perfil save(Perfil perfil) {
		return perfilrep.save(perfil);
	};
	
	@Override
	public Perfil edita(Long idperfil) {
		return perfilrep.findById(idperfil).orElse(null);
	}
	
	@Override
	public Perfildet editadet(Long idperfildet) {
		
		return perfildetrep.findById(idperfildet).orElse(null);
	};
	
	public Perfildet savedet(Perfildet perfildet) {
		return perfildetrep.save(perfildet);
	};*/
	
	/*@Override
	public Perfildet editadetval(Long idperfil, Long idmenurubro) {
		return perfildetrep.findByIdperfilAndIdmenurubro(idperfil, idmenurubro);
		
	};*/
}
package com.produccion.gescom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.produccion.gescom.dto.PerfilDtoReq;
import com.produccion.gescom.dto.PerfilDtoRes;
import com.produccion.gescom.entity.Perfil;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PerfilMapper {
	Perfil toPerfil(PerfilDtoReq perfilreq);
	PerfilDtoRes PerfilTo(Perfil perfil);
}

package com.produccion.gescom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import com.produccion.gescom.dto.UsuarioDtoRes;
import com.produccion.gescom.entity.UserEntity;
import com.produccion.gescom.dto.UsuarioDtoReq;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuarioMapper{
	@Mapping(source = "codusuario", target = "username")
	@Mapping(target = "estadopas", ignore = true)
	@Mapping(target = "iduser", ignore = true)
	UserEntity toUsuario(UsuarioDtoReq usuarioreq);
	
	@Mapping(source = "username", target = "codusuario")
	UsuarioDtoRes UsuarioTo(UserEntity usuario);
	
	@Mapping(target = "iduser", ignore = true)
	@Mapping(target = "username", ignore = true)
	//@Mapping(source = "estado_auditoria", target = "estado")
    void toUsuarioE(UsuarioDtoReq request, @MappingTarget UserEntity usuario);
}
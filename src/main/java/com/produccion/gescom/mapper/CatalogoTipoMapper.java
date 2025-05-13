package com.produccion.gescom.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

//import org.mapstruct.Mapping;
import com.produccion.gescom.dto.CatalogoTipoDto;
import com.produccion.gescom.entity.CatalogoTipo;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CatalogoTipoMapper {
	List<CatalogoTipoDto> toListaCatalogoTipo(List<CatalogoTipo> catalogotipo);
}
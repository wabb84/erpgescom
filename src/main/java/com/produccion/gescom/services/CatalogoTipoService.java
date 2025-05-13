package com.produccion.gescom.services;
import java.util.List;
import com.produccion.gescom.dto.CatalogoTipoDto;

public interface CatalogoTipoService {
	public List<CatalogoTipoDto> listaCatalogo(boolean mantenimiento);
}

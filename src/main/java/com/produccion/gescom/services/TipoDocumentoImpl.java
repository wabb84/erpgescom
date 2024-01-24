package com.produccion.gescom.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produccion.gescom.entity.TipoDocumento;
import com.produccion.gescom.repository.TipoDocumentoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TipoDocumentoImpl implements TipoDocumentoService {
	
	@Autowired
	private TipoDocumentoRepository tipodocrep;
	
	@Override
	public List<TipoDocumento> listaTipoDocumento()
	{
		return tipodocrep.findAll();
	}
	
	/*private final TipoDocumentoRepository tipodocrep;

    @Autowired
    public void TipoDocumentoImpl2(TipoDocumentoRepository tipodocrep) {
        this.tipodocrep = tipodocrep;
    }
    
    @Override
	public List<TipoDocumento> listaTipoDocumento()
	{
		return tipodocrep.findAll();
	}*/
	

}

package com.produccion.gescom.salud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.produccion.gescom.salud.dto.PersprofDto;
import com.produccion.gescom.salud.dto.PersprofdatosDto;
import com.produccion.gescom.salud.entity.Persprof;

@Repository
public interface PersprofRepository extends JpaRepository<Persprof, Long>{

	@Transactional(readOnly=true)
	
	@Query(value = "Select idpersprof,  idpersona, idprofesion, nrocolegio, rne, vigencia \n"
			     + "  From persprof \n"
			     + "  Where idpersprof = :idpersprof" ,nativeQuery = true)
	
	public PersprofDto  findByPersprof( @Param("idpersprof") Long idpersprof );
	
	@Transactional(readOnly=true)
	@Query(value = "Select a.idpersprof, \n"
				  + "  	   a.idpersona, \n"
				  + "	   c.abreviatura ||' '||b.nomlargo as medico \n"
				  + " From persprof a \n"
				  + " Inner Join persona b On b.idpersona = a.idpersona \n"
				  + " Inner Join profesion c On c.idprofesion = a.idprofesion \n"
				  + " Where a.vigencia = 'A' \n"
				  + "   And a.idprofesion = 1 \n"
				  + " Order by b.nomlargo", nativeQuery = true )
	
	public List<PersprofdatosDto> ListaPersprof();

}
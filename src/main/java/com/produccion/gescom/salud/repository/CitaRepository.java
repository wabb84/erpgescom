package com.produccion.gescom.salud.repository;
//import java.time.LocalDate;
import java.util.Date;
//import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.produccion.gescom.salud.dto.CitasListadoDto;
import com.produccion.gescom.salud.entity.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
	@Transactional(readOnly=true)
	@Query(value = "select tturno,colortback,fechacita,horacita,doccita,paciente,historia,medico,consultorio,estadoc, colorestado, idcita\r\n"
			+ "   from f_lista_citas(:pfechai, :pfechaf, :pturno, :ppaciente, :pmedico, :pestado)\r\n"
			+ "   as (tturno varchar,colortback varchar,fechacita date,horacita varchar,doccita text,paciente varchar,\r\n"
			+ "       historia varchar,medico varchar,consultorio varchar,estadoc varchar, colorestado varchar,\r\n"
			+ "       idcita bigint)",nativeQuery = true)
	
	public List<CitasListadoDto> ListadoCitas(String pfechai, String pfechaf, Long pturno, Long ppaciente, Long pmedico, String pestado);
	
	
}

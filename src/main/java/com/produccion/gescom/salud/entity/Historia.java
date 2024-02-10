package com.produccion.gescom.salud.entity;

import java.io.Serializable;
import java.time.LocalDate;
//import java.util.Date;

//import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.produccion.gescom.entity.Auditable;
import com.produccion.gescom.entity.Persona;
import com.produccion.gescom.entity.Socieda;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="historia")
public class Historia extends Auditable<String> implements Serializable{
	private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idhistoria", unique=true, nullable=false)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "idpersona")
    private Persona idpersona;
	
	@JsonFormat( pattern="yyyy-MM-dd" )
	@Column(name="histfecingr")
	private LocalDate histfecingr;
	
	@Column(name="tutor")	
	private Long tutor;
	
	@Column(name="idpersprof")	
	private Long idpersprof;
	
	@ManyToOne
    @JoinColumn(name = "idsocieda")
    private Socieda idsocieda;
	
	@Column(name="idtippacie")	
	private Long idtippacie;
	
	@Column(name="idtiphisto")
	private Long idtiphisto;

	@Column(name="serie")
	private String serie;
	
	@Column(name="numeroserie")
	private String numeroserie;
	
	@Column(name="anio")
	private String anio;

	@Column(name="mes")
	private String mes;
	
}
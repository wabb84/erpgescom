package com.produccion.gescom.salud.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	
	@NotNull
	@Column(name="nrohistori")
	private Long nrohistori; 
	
	@ManyToOne
    @JoinColumn(name = "idpersona")
    private Persona persona;
	
	@Column(name="histfecingr")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")	
	private Date histfecingr;
	
	@NotNull
	@Column(name="tutor")	
	private Long tutor;
	
	@NotNull
	@Column(name="idpersprof")	
	private Long idpersprof;
	
	@ManyToOne
    @JoinColumn(name = "idsocieda")
    private Socieda idsocieda;
	
	@NotNull
	@Column(name="idtippacie")	
	private Long idtippacie;
	
	@NotNull
	@Column(name="idtiphisto")
	private Long idtiphisto;

	@NotNull
	@Column(name="serie")
	private String serie;
	
	@NotNull
	@Column(name="numeroserie")
	private String numeroserie;
	
	@NotNull
	@Column(name="anio")
	private String anio;

	@NotNull
	@Column(name="mes")
	private String mes;
	
	
}
package com.produccion.gescom.salud.entity;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.produccion.gescom.entity.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="cita")
public class Cita extends Auditable<String> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idcita", unique=true, nullable=false)
	private Long id;
	
	@NotNull
	@Column(name="idagenda")
	private Long idagenda;
	
	@NotNull
	@Column(name="idhistoria")
	private Long idhistoria;
	
	@NotNull
	@Column(name="idsocieda")
	private Long idsocieda;
	
	@NotNull
	@Column(name="serie")
	private String serie;
	
	@NotNull
	@Column(name="anio")
	private String anio;
	
	@NotNull
	@Column(name="mes")
	private String mes;
	
	@NotNull
	@Column(name="numeroserie")
	private String numeroserie;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private EEstadoCita estadocita;
	
	//@NotNull
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="fechacita")
    private LocalDate fechacita;
	
	//@NotNull
	@Column(name="horacita")
	private String horacita;
	
	@NotNull
	@Column(name="idestadocita")
	private Long idestadocita;
	
}

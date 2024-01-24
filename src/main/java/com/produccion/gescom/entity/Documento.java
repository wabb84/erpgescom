package com.produccion.gescom.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name="documento")
public class Documento implements  Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="iddocumento", unique=true, nullable=false)
	private Long id;
	
	@NotNull
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="serie")
	private String serie;

	@Column(name="tipodoc")
	private String tipodoc;

	@Column(name="tiposerie")
	private String tiposerie;
	
	@Column(name="anio")
	private String anio;
	
	@NotNull
	@Column(name="idsocieda")
	private Long idsocieda;
	
}

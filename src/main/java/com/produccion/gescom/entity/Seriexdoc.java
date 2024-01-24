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
@Table(name="seriexdoc")
public class Seriexdoc extends Auditable<String> implements  Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idseriexdoc", unique=true, nullable=false)
	private Long id;
	
	@NotNull
	@Column(name="iddocumento")
	private Long iddocumento;
	
	@Column(name="anio")
	private String anio;
	
	@Column(name="mes")
	private String mes;
		
	@NotNull
	@Column(name="longitud")
	private Long longitud;
	
	@NotNull
	@Column(name="idsocieda")
	private Long idsocieda;
	
	@NotNull
	@Column(name="correlativo")
	private Long correlativo;
}

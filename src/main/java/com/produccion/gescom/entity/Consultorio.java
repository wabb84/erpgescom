package com.produccion.gescom.entity;

import java.io.Serializable;

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
@Table(name="consultorio")
public class Consultorio extends Auditable<String> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 @Column(name="idconsultorio", unique=true, nullable=false)
	 private Long id;
	
	
	@NotNull
	@Column(name="idsocieda")
	private Long idsocieda;
	
	@NotNull
	@Column(name="descripcion")
	private String descripcion;
	
	
	@Enumerated(EnumType.STRING)
	private EVigencia vigencia;

}

package com.produccion.gescom.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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
@Table(name="feriados")
public class Feriados extends Auditable<String> implements Serializable {
	 private static final long serialVersionUID = 1L;
		
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 @Column(name="idferiado", unique=true, nullable=false)
	 private Long idferiado;
	 
	 @JsonFormat( pattern="yyyy-MM-dd" )
	 @Column(name="fechaferia")
     private LocalDate fechaferia;
	 
	 @NotNull
	 @NotBlank(message = "Nombre de Feriado")
	 @Column(name="descripcion")
	 private String descripcion;
	 
	 @NotNull
	 @Column(name="idsocieda")
	 private Long idsocieda;
 
}

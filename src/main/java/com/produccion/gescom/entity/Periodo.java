package com.produccion.gescom.entity;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="periodo")
public class Periodo  extends Auditable<String> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 @Column(name="periodoid", unique=true, nullable=false)
	 private Long id;
	 
	 @Column(name="anio")
	 private String anio;
	 
	 @Column(name="mes")
	 private String mes;
	 
	 @Column(name="descrmes")
	 private String descrmes;
	 
	 @Column(name="estadop")
	 private String estadop;
	 
	 @JsonFormat(pattern="yyyy-MM-dd")
	 @Column(name="fechaci")
	 private LocalDate fechaci;
	 
	 @JsonFormat(pattern="yyyy-MM-dd")
	 @Column(name="fechacf")
	 private LocalDate fechacf;

	 @JsonFormat(pattern="yyyy-MM-dd")
	 @Column(name="fechavi")
	 private LocalDate fechavi;

	 @JsonFormat(pattern="yyyy-MM-dd")
	 @Column(name="fechavf")
	 private LocalDate fechavf;

	 @JsonFormat(pattern="yyyy-MM-dd")
	 @Column(name="fechadi")
	 private LocalDate fechadi;

	 @JsonFormat(pattern="yyyy-MM-dd")
	 @Column(name="fechadf")
	 private LocalDate fechadf;
	
}

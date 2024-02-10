package com.produccion.gescom.salud.entity;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.produccion.gescom.entity.Auditable;
import com.produccion.gescom.entity.Socieda;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
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
public class Agenda extends Auditable<String> implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idagenda", unique=true, nullable=false)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "idturnos")
    private Turnos turnos;
	
	@ManyToOne
    @JoinColumn(name = "idsocieda")
    private Socieda socieda;

	@ManyToOne
    @JoinColumn(name = "idconsultorio")
    private Consultorio consultorio;
	
	@ManyToOne
    @JoinColumn(name = "idpersprof")
    private Persprof persprof;

	@NotNull(message = "El año no puede ser nulo")
    @NotBlank(message = "El año no puede estar en blanco")	
	@Column(name="anio")
	private String anio; 

	@NotNull(message = "El mes no puede ser nulo")
    @NotBlank(message = "El mes no puede estar en blanco")	
	@Column(name="mes")
	private String mes; 
	
	@Column(name="dia")
	private String dia; 

	@Column(name="hora")
	private String hora; 
	
	@Column(name="observacion")
	private String observacion; 
	
	@Future(message = "La fecha dea agenda debe ser en el futuro")
	@JsonFormat( pattern="yyyy-MM-dd" )
	@Column(name="fechaagenda")
	private LocalDate fechaagenda; 

	@NotNull(message = "El estado no puede ser nulo")
    @NotBlank(message = "El estado no puede estar en blanco")	
	@Column(name="estado")
	private String estado; 
	
}
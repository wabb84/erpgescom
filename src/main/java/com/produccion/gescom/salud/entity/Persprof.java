package com.produccion.gescom.salud.entity;

import java.io.Serializable;

import com.produccion.gescom.entity.Auditable;
import com.produccion.gescom.entity.Persona;
import com.produccion.gescom.entity.Profesion;
import com.produccion.gescom.entity.Socieda;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="persprof")
public class Persprof extends Auditable<String> implements Serializable{
	private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idpersprof", unique=true, nullable=false)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "idpersona")
    private Persona idpersona;

	@ManyToOne
    @JoinColumn(name = "idprofesion")
    private Profesion idprofesion;

	@Column(name="nrocolegio")
	private String nrocolegio;	
	
	@Column(name="rne")
	private String rne;	

	@NotNull(message = "La vigencia no puede estar en blanco")
	@Column(name="vigencia")
	private String vigencia;

	@ManyToOne
    @JoinColumn(name = "idsocieda")
    private Socieda idsocieda;
	

}
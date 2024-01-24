package com.produccion.gescom.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="otrosdatos")
public class Otrosdatos extends Auditable<String> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idotrosdat", unique=true, nullable=false)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "idpersona")
    private Persona persona;
	
	@ManyToOne
    @JoinColumn(name = "idtipoodat")
    private Tipoodat tipoodat;
	
	@Column(name="codubigeo")
	private String codubigeo;
	
	@Column(name="telefono")
	private String telefono;
	
	@Column(name="lugarnaci")
	private String lugarnaci;
	
	@Column(name="correo")
	private String correo;

}

package com.produccion.gescom.entity;

import java.io.Serializable;
import java.time.LocalDate;

//import java.util.Date;
//import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="persona")
public class Persona extends Auditable<String> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idpersona", unique=true, nullable=false)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "idtipodoc")
    private TipoDocumento tipoDocumento;	

	@ManyToOne
    @JoinColumn(name = "idsocieda")
    private Socieda idsocieda;

	@ManyToOne
    @JoinColumn(name = "idpais")
    private Pais idpais;
	
	@Enumerated(EnumType.STRING)
	private ETipoPersona tipopersona;	
	
	@Column(name="nrodoc")
	private String numerodocumento;
	
	@Column(name="appaterno")
	private String apellidopaterno;
	
	@Column(name="appmaterno")
	private String apellidomaterno;
	
	@Column(name="primernom")
	private String primernombre;
	
	@Column(name="segundonom")
	private String segundonombre;
	
	@Column(name="nomlargo")
	private String nombrelargo;

	@Column(name="razonsocial")
	private String razonsocial;
	
	@Column(name="nomabrev")
	private String nomabreviado;
	
	@JsonFormat( pattern="yyyy-MM-dd" )
	@Column(name="fecnacimi")
	private LocalDate fecnacimi;

	@Column(name="sexo")
	private String sexo;
	
	@Enumerated(EnumType.STRING)
	private EVigencia vigencia;

	@Enumerated(EnumType.STRING)
	private EEstadoCivil estadocivil;
	
	@Column(name="lugarnacimi")
	private String lugarnacimi;
	
	@Column(name="telefono")
	private String telefono;
	
	@Email(message = "Correo electrónico no válido")
	@Column(name="email")
	private String email;
	
	@Column(name="domicilio")
	private String domicilio;
	
	@Column(name="observacion")
	private String observacion;

	@Column(name="codubigeo")
	private String codubigeo;
	
	/*@Column(name="tutor")
	private Long tutor;*/
	
	//@Column(name="tipoparentutor")
	@Enumerated(EnumType.STRING)
	private ETipotutor tipoparentutor;

	@Column(name="idtipodoctutor")
	private Long idtipodoctutor;

	@Column(name="apellnombtutor")
	private String apellnombtutor;
	
	@Column(name="numerodoctutor")
	private String numerodoctutor;
}
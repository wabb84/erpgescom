package com.produccion.gescom.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;


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
	
	@Column(name="fecnacimi")
	private Date fecnacimiento;

	@Column(name="sexo")
	private String sexo;

}

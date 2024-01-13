package com.produccion.gescom.entity;

import java.io.Serializable;

import jakarta.persistence.*;
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
@Table(name="socieda")
public class Socieda extends Auditable<String> implements Serializable {
	 private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 @Column(name="idsocieda", unique=true, nullable=false)
	 private Long id;
	 
	 @NotNull
	 @NotBlank(message = "Nombre de Sociedad es Requerido")
	 @Column(name="dessocieda")
	 private String sociedanombre;
	 
	 @NotNull
	 @Column(name="idrubro")
	 private Long idrubro;
	 
	 /*@JsonIgnore
	 @ManyToOne
	 @JoinColumn(foreignKey = @ForeignKey(name = "socieda_rubro_fkey"))
	 private Rubro rubro;*/
	 
	 //@Size(max=1)
	 @Enumerated(EnumType.STRING)
	 private ETipoPersona tipopersona;
	 
	 @NotNull
	 @Column(name="idtipodoc")
	 private Long idtipodoc;
	 
	 /*@JsonIgnore
	 @ManyToOne
	 @JoinColumn(foreignKey = @ForeignKey(name = "socieda_tipodoc_fkey"))
	 private TipoDocumento tipodocumento;*/
	 
	 @NotNull
	 @NotBlank(message = "Número de Documento es Requerido")
	 @Column(name="numerodoc")
	 private String numerodocumento;

	 @NotNull
	 @NotBlank(message = "Nombre Comercial es requerido")
	 @Column(name="nombrecome")
	 private String nombrecomercial;
	 
	 @NotNull
	 @Column(name="idpais")
	 private Long idpais;

	 /*@JsonIgnore
	 @ManyToOne
	 @JoinColumn(foreignKey = @ForeignKey(name = "socieda_pais_fkey"))
	 private Pais pais;*/
	 
	 @NotNull
	 @NotBlank(message = "Nombre de Serie es Requerido")
	 @Column(name="serie")
	 private String serie;
	 
	 @NotNull
	 @Column(name="estadosocieda")
	 private String estadosocieda;
 }
package com.produccion.gescom.entity;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name="usuarioper", schema="seguridad")
public class Usuarioper implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idusuarioper", unique=true, nullable=false)
	private Long id;
	
	//@NotNull
	@Column(name="idusuario")
	private Long idusuario;
	
	@NotNull
	@Column(name="idperfil")
	private Long idperfil;
	
	/*@OneToMany(mappedBy = "usuarioper", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Usuarioper> usuarioper;*/
	
	/*@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE, targetEntity = UserEntity.class)
	@JoinColumn(name="iduser")	
	private UserEntity userentity;*/
}

package com.produccion.gescom.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="ubigeo")
public class Ubigeo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idubigeo", unique=true, nullable=false)
	private Long id;

	@Column(name="codubigeo")
	private String codubigeo;
	
	@Column(name="iddepart")
	private String iddepart;
	
	@Column(name="idprovin")
	private String idprovin;
	
	@Column(name="iddistri")
	private String iddistri;
	
	@Column(name="depart_des")
	private String depart_des;
	
	@Column(name="provin_des")
	private String provin_des;
	
	@Column(name="distri_des")
	private String distri_des;

}

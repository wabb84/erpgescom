package com.produccion.gescom.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="usuario", schema="seguridad")
public class UserEntity extends Auditable<String> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="iduser", unique=true, nullable=false)
	private Long iduser;
	@Column(name="codusuario")
    private String username;
	private String desusuario;
	@Email(message = "Email no válido")
	private String email;
	private String telefono;
	private String password;
	@NotNull
	private Long idsocieda;
	@NotNull
	private Long idtipodoc;
	private String numerodoc;
	@Enumerated(EnumType.STRING)
	private EEstadoUsuario estadousuario;
	@JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate fechaini;
	@JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate fechafin;
	private String estadopas;	
	@Enumerated(EnumType.STRING)
	private ESexo sexo;

	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Rol.class, cascade = CascadeType.PERSIST)
	@JoinTable(name = "usuarol", schema = "seguridad", joinColumns = @JoinColumn(name = "idusuario"), inverseJoinColumns = @JoinColumn(name = "idrol"))
	private Set<Rol> roles;
	
	@Transient
    private Long idperfil;
	
	//@ManyToMany(fetch = FetchType.EAGER, targetEntity = Rol.class, cascade = CascadeType.PERSIST)
	//@JoinTable(name = "usuarioper", joinColumns = @JoinColumn(name = "idusuario"), inverseJoinColumns = @JoinColumn(name = "idperfil"))
	//private Usuarioper usuarioper;
	/*@OneToMany(mappedBy = "userentity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Usuarioper> usuarioper;*/
}

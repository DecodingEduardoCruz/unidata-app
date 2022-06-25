package br.dev.ec.unidata.domain.usuario;

import lombok.Data;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Usuario {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long id;	
	@Column(unique = true)
	private String username;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;	

	private String name;
	private String fone;
	
	@JoinTable(name = "usuario_roles")
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Role> roles = new ArrayList<>();
}

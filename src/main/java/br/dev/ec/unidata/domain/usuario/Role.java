package br.dev.ec.unidata.domain.usuario;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor

public class Role {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)	
	private Long id;
	private String name;
}

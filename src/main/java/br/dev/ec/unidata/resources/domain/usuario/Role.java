package br.dev.ec.unidata.resources.domain.usuario;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
@Table(schema = "unidata", name = "roles")
public class Role {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)	
	private Long id;
	private String name;
}

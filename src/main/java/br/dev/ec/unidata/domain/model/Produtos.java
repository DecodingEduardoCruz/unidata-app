package br.dev.ec.unidata.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Produtos {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)	

	private Long id;
	private String nome;
	private BigDecimal qtd;
	private BigDecimal valor;
}

package br.dev.ec.unidata.domain.model.contaspagar;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;

import br.dev.ec.unidata.domain.model.Produtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class ContasPagar {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)	

	private Long id;
	private String Nome;
	private Integer matricula;	
	
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "data_cadastro", nullable = false)
	private Date dataCadastro;
	
	@Column(name = "data_vencimento", nullable = false)
	private Date dataVencimento;
	
	private BigDecimal TotalGeral;	
	
	@JoinTable(name = "contaspagar_produtos")
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Produtos> produtos = new ArrayList<>();

}

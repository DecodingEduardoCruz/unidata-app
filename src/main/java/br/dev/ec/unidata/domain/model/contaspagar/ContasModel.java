package br.dev.ec.unidata.domain.model.contaspagar;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class ContasModel {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)	
	private Long id;
	
	private String nome;
	private String metodo;
	private BigDecimal valor;
	private Date dataCompra;
	private Date dataVencimento;
}

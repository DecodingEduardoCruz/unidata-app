package br.dev.ec.unidata.domain.repositorio;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.ec.unidata.domain.model.contaspagar.ContasPagar;

public interface ContasPagarRep extends JpaRepository<ContasPagar, Long>{
		ContasPagar findByMatricula(Integer matricula);
		ContasPagar findByDataVencimento(Date vencimento);

}

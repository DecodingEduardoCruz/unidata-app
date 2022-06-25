package br.dev.ec.unidata.domain.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import br.dev.ec.unidata.domain.model.Produtos;

public interface ProdutosRep extends JpaRepository<Produtos, Long>{
	Produtos findByNome(String nome);
}

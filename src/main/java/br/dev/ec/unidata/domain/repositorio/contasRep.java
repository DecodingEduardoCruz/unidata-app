package br.dev.ec.unidata.domain.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import br.dev.ec.unidata.domain.model.contaspagar.ContasModel;

public interface contasRep extends JpaRepository<ContasModel, Long>{

}

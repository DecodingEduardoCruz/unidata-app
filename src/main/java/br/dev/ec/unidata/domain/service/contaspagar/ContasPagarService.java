package br.dev.ec.unidata.domain.service.contaspagar;

import java.util.List;

import br.dev.ec.unidata.domain.model.contaspagar.ContasModel;

public interface ContasPagarService {
	List<ContasModel> todasContas();
	ContasModel salvar(ContasModel contasPagar);
}

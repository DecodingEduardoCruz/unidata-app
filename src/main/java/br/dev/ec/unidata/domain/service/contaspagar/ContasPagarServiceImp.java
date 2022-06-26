package br.dev.ec.unidata.domain.service.contaspagar;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.dev.ec.unidata.UnidataApplication;
import br.dev.ec.unidata.domain.model.contaspagar.ContasModel;
import br.dev.ec.unidata.domain.repositorio.contasRep;
import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor @Transactional
public class ContasPagarServiceImp implements ContasPagarService {
	private static final Logger logger = LoggerFactory.getLogger(UnidataApplication.class);
	private final contasRep contasRep;

	@Override
	public List<ContasModel> todasContas() {
		List<ContasModel> contas = contasRep.findAll(); 
		if(contas.isEmpty()) throw new NullPointerException("Você não possui contas ativas!");
		
		logger.info("Foram encontradas {} contas ativas.", contas.size());
		return contas;
	}

	@Override
	public ContasModel salvar(ContasModel contasModel) {
		if(contasModel == null) throw new NullPointerException("Preencha todos os campos!");
		logger.info("Conta a pagar adicionada {} : {}", contasModel.getNome(), contasModel.getValor());
		contasRep.save(contasModel);
		return contasModel;
	}
}

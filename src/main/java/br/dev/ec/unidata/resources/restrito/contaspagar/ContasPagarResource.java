package br.dev.ec.unidata.resources.restrito.contaspagar;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.ec.unidata.UnidataApplication;
import br.dev.ec.unidata.domain.model.contaspagar.ContasModel;
import br.dev.ec.unidata.domain.service.contaspagar.ContasPagarService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restrito")
@SecurityRequirement(name = "javainuseapi")
@Tag(name = "Contas:", description = "A pagar")
public class ContasPagarResource {
	private static final Logger logger = LoggerFactory.getLogger(UnidataApplication.class);
	private final ContasPagarService contasPagar;
	
	@GetMapping("/contas-pagar")
	public ResponseEntity<List<ContasModel>> todos(){		
		logger.info("Busca de contas solicitada:");
		return ResponseEntity.ok(contasPagar.todasContas());
	}
	
	@PostMapping("/contas-pagar")
	public ResponseEntity<ContasModel> nova(@RequestBody ContasModel contasModel){		
		logger.info("Busca de contas solicitada:");
		return ResponseEntity.ok(contasPagar.salvar(contasModel));
	}
}

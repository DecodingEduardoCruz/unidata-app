package br.dev.ec.unidata.resources.restrito;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import br.dev.ec.unidata.UnidataApplication;
import br.dev.ec.unidata.domain.model.usuario.Usuario;
import br.dev.ec.unidata.domain.service.usuario.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restrito")
@SecurityRequirement(name = "javainuseapi")
@Tag(name = "Usuário", description = "Restrito")
public class restrito_usuario {
	private static final Logger logger = LoggerFactory.getLogger(UnidataApplication.class);
	private final UsuarioService usuarioService;	

	@GetMapping("/usuario")
	public ResponseEntity<List<Usuario>> todos(){		
		logger.info("Busca de usuário solicitada:");
		return ResponseEntity.ok(usuarioService.getUsuarios());
	}	
}

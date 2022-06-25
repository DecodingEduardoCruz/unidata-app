package br.dev.ec.unidata.resources.administrador.usuario;

import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.dev.ec.unidata.UnidataApplication;
import br.dev.ec.unidata.domain.model.usuario.Role;
import br.dev.ec.unidata.domain.model.usuario.Usuario;
import br.dev.ec.unidata.domain.service.usuario.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@SecurityRequirement(name = "javainuseapi")
@Tag(name = "Usuário", description = "Administrador")
public class admin_usuario {
	private static final Logger logger = LoggerFactory.getLogger(UnidataApplication.class);
	private final UsuarioService usuarioService;	

	@PostMapping("/usuario")
	public ResponseEntity<Usuario> saveCooperado(@RequestBody Usuario usuario){
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/novo/usuario").toUriString());
		logger.info("Novo cadastro solicidado:");
		return ResponseEntity.created(uri).body(usuarioService.salvar(usuario));
	}	

	@PostMapping("/role")
	public ResponseEntity<Role> saveRole(@RequestBody Role role){
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/nova/permissao").toUriString());
		logger.info("Novo cadastro de permissão solicidado:");
		return ResponseEntity.created(uri).body(usuarioService.saveRole(role));
	}	

	@PostMapping("/addrole")
	public ResponseEntity<?> permissaoToCooperado(@RequestBody RoleToCooperado form){
		usuarioService.addRole(form.getUsername(), form.getRoleName());
		logger.info("Nova permissão adicionada:");
		return ResponseEntity.ok().build();
	}
}

@Data
class RoleToCooperado {
	private String username;
	private String roleName;	
} 

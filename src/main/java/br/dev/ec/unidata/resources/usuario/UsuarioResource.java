package br.dev.ec.unidata.resources.usuario;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.ec.unidata.UnidataApplication;
import br.dev.ec.unidata.domain.service.usuario.UsuarioService;
import br.dev.ec.unidata.domain.usuario.Usuario;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
@Tag(name = "Usuário", description = "Usuários da aplicação.")
public class UsuarioResource {
	private static final Logger logger = LoggerFactory.getLogger(UnidataApplication.class);
	private final UsuarioService usuarioService;	

	@GetMapping("/usuario")
	public ResponseEntity<List<Usuario>> todos(){		
		logger.info("Busca de cooperado solicitada:");
		return ResponseEntity.ok(usuarioService.getUsuarios());
	}	
/*
	@PostMapping("/cooperado")
	public ResponseEntity<Cooperado> saveCooperado(@RequestBody Cooperado cooperado){
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/cooperados/auth/v1/cooperado/novo").toUriString());
		logger.info("Novo cadastro solicidado:");
		return ResponseEntity.created(uri).body(cooperadoService.saveCooperado(cooperado));
	}	

	@PostMapping("/permissao")
	public ResponseEntity<Role> saveRole(@RequestBody Role role){
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/cooperados/auth/v1/permissao/novo").toUriString());
		logger.info("Novo cadastro de permissão solicidado:");
		return ResponseEntity.created(uri).body(cooperadoService.saveRole(role));
	}	

	@PostMapping("/cooperado-permitir")
	public ResponseEntity<?> permissaoToCooperado(@RequestBody RoleToCooperado form){
		cooperadoService.addRoleToCooperado(form.getMatricula(), form.getRoleName());
		logger.info("Nova permissão adicionada:");
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/refreshToken")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws StreamWriteException, DatabindException, IOException{	
		String authorizationHeader = request.getHeader(AUTHORIZATION);
		
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			try {
				String refreshToken = authorizationHeader.substring("Bearer ".length());
				Algorithm algorithm = Algorithm.HMAC512(CustomAuthenticationFilter.secret_token);
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decodeJWT = verifier.verify(refreshToken);
				String username = decodeJWT.getSubject();
				Cooperado cooperado = cooperadoService.daMatricula(Integer.parseInt(username));
				
				String accessToken = JWT.create()
						.withSubject(cooperado.getMatricula().toString())
						.withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
						.withIssuer(request.getRequestURL().toString())
						.withClaim("roles", cooperado.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
						.sign(algorithm);
				
				Map<String, String> tokens = new HashMap<>();		
				tokens.put("acessToken", accessToken);
				tokens.put("refreshToken", refreshToken);
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), tokens);
				
			}catch( Exception e ) {
				response.setHeader("Erro ",e.getMessage());
				response.setStatus(HttpStatus.FORBIDDEN.value());
				
				Map<String, String> error = new HashMap<>();		
				error.put("error_message", e.getMessage());
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), error);
			}
		}else {
			throw new RuntimeException("Seu Token precisa de atualização!");
		}
		logger.info("Busca de cooperado solicitada:");
	}*/
}

@Data
class RoleToCooperado {
	private String username;
	private String roleName;	
} 

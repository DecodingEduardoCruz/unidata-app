package br.dev.ec.unidata.resources.publico;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.dev.ec.unidata.UnidataApplication;
import br.dev.ec.unidata.domain.filter.CustomAuthenticationFilter;
import br.dev.ec.unidata.domain.model.usuario.Role;
import br.dev.ec.unidata.domain.model.usuario.Usuario;
import br.dev.ec.unidata.domain.service.usuario.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/publico")
@Tag(name = "Reflesh Token", description = "Publico")
public class Token {
	private static final Logger logger = LoggerFactory.getLogger(UnidataApplication.class);
	private final UsuarioService usuarioService;	
	
	@GetMapping("/token")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws StreamWriteException, DatabindException, IOException{	
		String authorizationHeader = request.getHeader(AUTHORIZATION);
		
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			try {
				String refreshToken = authorizationHeader.substring("Bearer ".length());
				Algorithm algorithm = Algorithm.HMAC512(CustomAuthenticationFilter.secret_token);
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decodeJWT = verifier.verify(refreshToken);
				String username = decodeJWT.getSubject();
				Usuario usuario = usuarioService.username(username);
				
				String accessToken = JWT.create()
						.withSubject(usuario.getName())
						.withExpiresAt(new Date(System.currentTimeMillis() + 360*  60 * 1000))
						.withIssuer(request.getRequestURL().toString())
						.withClaim("roles", usuario.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
						.sign(algorithm);
				
				Map<String, String> tokens = new HashMap<>();		
				tokens.put("acessToken", accessToken);
				tokens.put("refreshToken", refreshToken);
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), tokens);
				
			}catch( Exception e ) {
				response.setHeader("token_message ",e.getMessage());
				response.setStatus(HttpStatus.FORBIDDEN.value());
				
				Map<String, String> error = new HashMap<>();		
				error.put("token_message", e.getMessage());
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), error);
			}
		}else {
			throw new RuntimeException("Atualise seu token!");
		}
		logger.info("Renovação de token solicitada:");
	}
}

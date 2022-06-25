package br.dev.ec.unidata;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.dev.ec.unidata.domain.model.usuario.Role;
import br.dev.ec.unidata.domain.model.usuario.Usuario;
import br.dev.ec.unidata.domain.service.usuario.UsuarioService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
@SecurityScheme(name = "javainuseapi", scheme = "Bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class UnidataApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(UnidataApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {	
	 return application.sources(UnidataApplication.class);
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return  new BCryptPasswordEncoder();
	}
	
	
	CommandLineRunner run(UsuarioService usuarioService) {		
		return args -> {	
			if(usuarioService.getUsuarios() == null) {
				usuarioService.saveRole(new Role(1L, "ROLE_USER"));
				usuarioService.saveRole(new Role(2L, "ROLE_ADMIN"));
				
				usuarioService.salvar(new Usuario(3L, 001, "admin", "Administrador", "46999147716", "tulipa", new ArrayList<>()));
				
				usuarioService.addRole("admin", "ROLE_USER");
				usuarioService.addRole("admin", "ROLE_ADMIN");
			}						
		};
	}

}

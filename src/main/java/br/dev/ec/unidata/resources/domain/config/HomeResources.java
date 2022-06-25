package br.dev.ec.unidata.resources.domain.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Controller
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/")
@SecurityRequirement(name = "javainuseapi")
@Tag(name = "Unidata", description = "Banco de dados multifunção")
public class HomeResources {
	
	@GetMapping("*")
	public String index() {
		return "/swagger/openapi/swagger-ui.html";
	}
}

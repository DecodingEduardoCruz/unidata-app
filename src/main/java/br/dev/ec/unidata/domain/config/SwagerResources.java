package br.dev.ec.unidata.domain.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Controller
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/")
@Tag(name = "Swagger", description = "Swagger page")
public class SwagerResources {
	
	@GetMapping("*")
	public String swagger() {
		return "/swagger/openapi/swagger-ui.html";
	}
}

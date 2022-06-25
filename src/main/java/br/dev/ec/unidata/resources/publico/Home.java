package br.dev.ec.unidata.resources.publico;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Controller
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/publico/")
@Tag(name = "Publico", description = "Conteúdo publico")
public class Home {
	
	@GetMapping("*")
	public String index() {
		return "Pasta publica";
	}

}

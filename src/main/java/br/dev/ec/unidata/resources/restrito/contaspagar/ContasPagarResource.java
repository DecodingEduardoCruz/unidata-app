package br.dev.ec.unidata.resources.restrito.contaspagar;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@SecurityRequirement(name = "javainuseapi")
@Tag(name = "Contas a pagar:", description = "Administrador")
public class ContasPagarResource {

}

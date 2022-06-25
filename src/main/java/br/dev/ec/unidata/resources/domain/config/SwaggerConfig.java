package br.dev.ec.unidata.resources.domain.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
	@Bean
    public OpenAPI openApi() {
        return new OpenAPI()
	        .info(new Info()
                .title("UNIDATA 1.0")
                .description("Api Gerenciamento Gestão Software")
                .version("v1.0")
                .contact(new Contact()
                        .name("Eduardo Cruz")
                        .url("https://www.ec.dev.br/")
                        .email("tecnologiaesolucoespr@gmail.com"))
                .license(new License().name("Apache License Version 2.0")
                .url("https://www.apache.org/licenses/LICENSE-2.0.txt"))
	        );
    }

}

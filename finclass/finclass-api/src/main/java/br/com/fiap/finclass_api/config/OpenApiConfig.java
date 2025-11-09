package br.com.fiap.finclass_api.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI finClassOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("FinClass API")
                        .version("v1")
                        .description("API FinClass - documentação OpenAPI"))
                .externalDocs(new ExternalDocumentation()
                        .description("Repositório do projeto")
                        .url("https://github.com/seu-usuario/finclass-api"));
    }
}

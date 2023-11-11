package br.com.startrip.imoveis.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI openApi() {
        var openAPI = new OpenAPI();
        openAPI.setComponents(new Components());
        openAPI.info(new Info().title("API de imóveis do StarTrip")
                        .description("Microsserviço responsável pelo CRUD dos imóveis")
                        .version("1.0.0"));

        return openAPI;
    }
}

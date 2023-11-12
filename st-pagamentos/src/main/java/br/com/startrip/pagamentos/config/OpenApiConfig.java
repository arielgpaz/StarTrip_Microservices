package br.com.startrip.pagamentos.config;

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
        openAPI.info(new Info().title("API de pagamentos do StarTrip")
                .description("Microsserviço responsável pelos pagamento das reservas")
                .version("1.0.0"));

        return openAPI;
    }
}

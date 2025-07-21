package br.edu.iftm.app_ensino_matematica_backend_resposta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .addServersItem(new Server().url("http://localhost:8082").description("Local Server"))
            .addServersItem(new Server().url("https://app-matematica-backend-respost-af372f52044d.herokuapp.com/").description("Production Server"))
            .info(new Info().title("API Microserviço Resposta - App Matemática")
                .version("1.0.0")
                .description("API para gerenciamento de respostas, rodadas e rankings do aplicativo de ensino de matemática."));
    }
}

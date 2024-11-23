package dev.abeatriz.account_service.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@OpenAPIDefinition
@Configuration
public class OpenApiConfigs {
    @Bean
    public OpenAPI userOpenAPI(@Value("${openapi.service.url}") String url) {
        Info info = new Info()
                            .title("Account Service")
                            .version("1.0")
                            .description("APIs para gerencias o Athena OS");
        return new OpenAPI().servers(List.of(new Server().url(url))).info(info);
    }
}


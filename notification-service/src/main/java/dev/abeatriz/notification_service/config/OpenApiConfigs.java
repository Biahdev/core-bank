package dev.abeatriz.notification_service.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@OpenAPIDefinition
@Configuration
public class OpenApiConfigs {

    @Bean
    public OpenAPI userOpenAPI() {
        Info info = new Info()
                .title("Notification Service")
                .version("1.0")
                .description("Serviço que gerência as notificações do Banco");

        Server serve = new Server().url("http://localhost:8080/notification-service");
        return new OpenAPI().servers(List.of(serve)).info(info);
    }
}


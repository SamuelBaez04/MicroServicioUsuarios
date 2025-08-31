package com.farmaclick.usuarios.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "FarmaSync Microservicio Usuarios",
                version = "1.0",
                description = "API REST para la gestión de usuarios en el sistema FarmaSync"
        )
)
public class SwaggerConfig {
}

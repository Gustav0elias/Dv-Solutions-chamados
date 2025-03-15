package com.javaspring.sistemadechamados.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "User Management API",
        version = "v1",
        description = "API for managing users, including creating, updating, deleting, and retrieving users."
    )
)
public class SwaggerConfig {

}

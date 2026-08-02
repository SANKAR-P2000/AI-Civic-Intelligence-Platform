package com.sankar.aicip.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI aiCivicIntelligencePlatformOpenAPI() {

        return new OpenAPI()

                .info(new Info()

                        .title("AI Civic Intelligence Platform API")

                        .description(
                                "REST API documentation for the AI Civic Intelligence Platform (AICIP). " +
                                        "This API enables citizens to register complaints, track complaint status, " +
                                        "and provides administrative modules for complaint management, analytics, " +
                                        "dashboard statistics, authentication, and user management."
                        )

                        .version("1.0.0")

                        .contact(new Contact()
                                .name("Sankar P")
                                .email("sankar0708m@gmail.com")
                                .url("https://github.com/SANKAR-P2000/AI-Civic-Intelligence-Platform.git"))

                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))

                .externalDocs(
                        new ExternalDocumentation()
                                .description("AI Civic Intelligence Platform GitHub Repository")
                                .url("https://github.com/your-github/AI-Civic-Intelligence-Platform"))

                .components(
                        new Components()
                                .addSecuritySchemes(
                                        "Bearer Authentication",
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                )

                .addSecurityItem(
                        new SecurityRequirement()
                                .addList("Bearer Authentication")
                );
    }
}
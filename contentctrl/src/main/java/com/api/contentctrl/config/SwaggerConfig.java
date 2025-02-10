package com.api.contentctrl.config;
 
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi swaggerApi() {
        return GroupedOpenApi.builder()
            .group("swagger-api")
            .packagesToScan("com.api.contentctrl") // Ajuste o pacote para o seu
            .pathsToMatch("/**")
            .build();
    }
}



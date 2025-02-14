package com.api.contentctrl.config;
  
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info; 

@Configuration
public class OpenApiConfig {

	//Swagger UI: http://localhost:8082/swagger-ui/index.html
	//sOpenAPI JSON: http://localhost:8082/v3/api-docs/public-api
			
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
            .group("public-api")
            .packagesToScan("com.api.contentctrl")  
            .pathsToMatch("/**") 
            .build();
    }
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Controler Content ")
                        .version("v0")
                        .description("Documentation for the example api"));
    }
    
}


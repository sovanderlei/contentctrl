package com.api.contentctrl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@ComponentScan(basePackages = "com.api.contentctrl")
@RequestMapping("/contentctrl")     
public class ContentctrlApplication {

	// docker-compose up --build	
	// docker logs contentctrl-app 
	
	public static void main(String[] args) {
		SpringApplication.run(ContentctrlApplication.class, args);
	}

}

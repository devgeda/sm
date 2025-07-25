package com.devgeda.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({ "com.devgeda.api", "com.devgeda.domain", "com.devgeda.infrastructure", "com.devgeda.fx" })
@EntityScan("com.devgeda.domain.models")
@EnableJpaRepositories("com.devgeda.infrastructure.repositories")
public class EstoqueSpringbootApplication {
	public static void main(String[] args) {
		SpringApplication.run(EstoqueSpringbootApplication.class, args);
	}

	
}
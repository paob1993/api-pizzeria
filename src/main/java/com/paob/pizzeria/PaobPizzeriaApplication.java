package com.paob.pizzeria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class PaobPizzeriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaobPizzeriaApplication.class, args);
	}

}

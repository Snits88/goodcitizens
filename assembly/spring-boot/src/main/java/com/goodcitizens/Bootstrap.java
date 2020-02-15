package com.goodcitizens;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:parentApplicationContext.xml")
public class Bootstrap{

	public static void main(String[] args) {
		SpringApplication.run(Bootstrap.class, args);
	}
}

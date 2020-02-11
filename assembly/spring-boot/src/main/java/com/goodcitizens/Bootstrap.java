package com.goodcitizens;

import com.goodcitizens.service.config.CorrelationIdManagement;
import com.goodcitizens.service.config.CorrelationInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:parentApplicationContext.xml")
public class Bootstrap{

	public static void main(String[] args) {
		SpringApplication.run(Bootstrap.class, args);
	}
}

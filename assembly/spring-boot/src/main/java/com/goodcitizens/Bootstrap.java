package com.goodcitizens;

// import com.goodcitizens.streams.CitizenStreams;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
// @EnableBinding(CitizenStreams.class)
public class Bootstrap implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(Bootstrap.class, args);
	}

}

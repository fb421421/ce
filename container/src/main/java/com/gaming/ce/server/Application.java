package com.gaming.ce.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.gaming.ce")
@EnableAutoConfiguration(exclude = { ErrorMvcAutoConfiguration.class })
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
package com.authdemo.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@SpringBootApplication
@ComponentScan(basePackages = "com.authdemo.auth.repositories")
public class AuthenticationRestEndpointDemoApplication {

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		SpringApplication.run(AuthenticationRestEndpointDemoApplication.class, args);
	}

}

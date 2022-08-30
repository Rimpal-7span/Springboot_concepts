package com.example.restApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RestApiApplication {

	public static void main(String[] args) {
		//System.setProperty("server.servlet.context-path", "/userManagement");
		SpringApplication.run(RestApiApplication.class, args);
	}

}

package com.microservice.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistrationServer {

	public static void main(String[] args) {
		// Use registration-server.yml to configure Eureka server
		// this is because we have all the service in the same project
		System.setProperty("spring.config.name", "registration-server");
		SpringApplication.run(ServiceRegistrationServer.class, args);
	}

}

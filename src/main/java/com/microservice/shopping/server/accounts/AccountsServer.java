package com.microservice.shopping.server.accounts;

import com.microservice.shopping.server.accounts.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient
public class AccountsServer {

	@Autowired
	AccountRepository accountRepository;

	public static void main(String[] args) {
		// Use registration-server.yml to configure Eureka server
		// this is because we have all the service in the same project
		System.setProperty("spring.config.name", "accounts-server");
		SpringApplication.run(AccountsServer.class, args);
	}

}

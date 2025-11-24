package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.app")
@EnableReactiveMongoRepositories
public class Boot4FlighBookingWebFluxMongoDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(Boot4FlighBookingWebFluxMongoDbApplication.class, args);
	}

	
	

}

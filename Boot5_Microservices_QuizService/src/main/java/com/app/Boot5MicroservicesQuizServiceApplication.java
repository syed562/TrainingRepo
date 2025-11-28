package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Boot5MicroservicesQuizServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Boot5MicroservicesQuizServiceApplication.class, args);
	}

}

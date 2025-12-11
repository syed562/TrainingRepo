package com.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.app.storage.FilesStorageService;

@CrossOrigin
@SpringBootApplication
@EnableDiscoveryClient
public class Boot6MicroservicesFlightServiceApplication {
	@Bean
	CommandLineRunner init(FilesStorageService storageService) {
	    return args -> storageService.init();
	}

	public static void main(String[] args) {
		SpringApplication.run(Boot6MicroservicesFlightServiceApplication.class, args);
		 System.out.println(new BCryptPasswordEncoder().encode("1234"));
	}

}

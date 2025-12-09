package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@SpringBootApplication
@EnableDiscoveryClient
public class Boot6MicroservicesFlightServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Boot6MicroservicesFlightServiceApplication.class, args);
		 System.out.println(new BCryptPasswordEncoder().encode("1234"));
	}

}

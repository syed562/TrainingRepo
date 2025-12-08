package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class Boot6MicroservicesBookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Boot6MicroservicesBookingServiceApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("newadmin123"));
	}

}

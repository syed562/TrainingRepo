package com.cb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.cb")
public class BootLibraryMgmntSysApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootLibraryMgmntSysApplication.class, args);
	}

}

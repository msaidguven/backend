package com.project.soruCevap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SoruCevapApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoruCevapApplication.class, args);
	}

}

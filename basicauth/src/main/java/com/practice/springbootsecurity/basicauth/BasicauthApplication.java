package com.practice.springbootsecurity.basicauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
@EnableWebSecurity
public class BasicauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicauthApplication.class, args);
	}

}

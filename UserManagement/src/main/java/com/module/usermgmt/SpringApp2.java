package com.module.usermgmt;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@SpringBootApplication
public class SpringApp2 implements CommandLineRunner{
	public static void main(String[] args) {
		System.out.println("IN SPRING APP 2");
		SpringApplication app = new SpringApplication(SpringApp2 .class);
		//adding different port
		//app.setDefaultProperties(Collections.singletonMap("server.port", "8083"));
		
		app.run(args);
	}

	@Order(value=2)
	@Bean("bCryptPasswordEncoder2")
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("IN SPRING APP 34237482");
		
	}
}

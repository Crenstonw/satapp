package com.triana.salesianos.edu.satapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.triana.salesianos.edu.satapp")
@EntityScan(basePackages="com.triana.salesianos.edu.satapp")
@SpringBootApplication
public class SatappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SatappApplication.class, args);
	}

}

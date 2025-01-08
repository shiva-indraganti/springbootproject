package com.example.fooditemservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FooditemserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FooditemserviceApplication.class, args);
	}

}

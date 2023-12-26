package com.supp.employe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient 
public class MicroServiceEmployeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceEmployeApplication.class, args);
	}

}

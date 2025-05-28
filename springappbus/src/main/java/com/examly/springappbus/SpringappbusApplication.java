package com.examly.springappbus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringappbusApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringappbusApplication.class, args);
	}

}

package com.examly.springappuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringappuserApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringappuserApplication.class, args);
	}

}

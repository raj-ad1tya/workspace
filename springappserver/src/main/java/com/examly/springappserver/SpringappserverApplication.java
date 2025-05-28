package com.examly.springappserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SpringappserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringappserverApplication.class, args);
	}

}

package com.trsystems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BootServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootServiceApplication.class, args);
	}

}

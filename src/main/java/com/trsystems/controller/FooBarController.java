package com.trsystems.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("book-service")
public class FooBarController {
	
	private Logger logger = LoggerFactory.getLogger(FooBarController.class);
	
	int count = 0;
	
	@GetMapping("/foo-bar")
//	@Retry(name = "default")
	@Retry(name = "foo-bar", fallbackMethod = "fallbackMethod") // Caso der falha mesmo com o retyr, chama o método no fallbackMethod
	public String fooBar() {
		logger.info("Request to foo-bar is received: {}", ++count);
		var response = new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class);
//		return "Foo Bar!!!";
		return response.getBody();
	}
	
	public String fallbackMethod(Exception e) {
		return "fallbackMethod()";
	}
	

}

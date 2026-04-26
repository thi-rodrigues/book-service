package com.trsystems.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.trsystems.environment.InstanceInformationService;
import com.trsystems.model.Book;
import com.trsystems.model.dto.ExchangeDTO;
import com.trsystems.proxy.ExchangeProxy;
import com.trsystems.repository.BookRepository;

@RestController
@RequestMapping("book-service")
public class BookController {
	
	@Autowired
	private InstanceInformationService information;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private ExchangeProxy exchangeProxy;

	@GetMapping(value="/{id}/{currency}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Book findBook(
			@PathVariable Long id, 
			@PathVariable String currency) {
		
		String port = information.retrieveServerPort();
		
		var book = bookRepository.findById(id).orElseThrow();
		
		// CHAMADA AO MS EXCHANGE-SERVICE
//		HashMap<String, String> map = new HashMap<>();
//		map.put("amount", book.getPrice().toString());
//		map.put("from", "USD");
//		map.put("to", currency);
		
//		var respponse = new RestTemplate()
//				.getForEntity("http://localhost:8000/exchange-service/{amount}/{from}/{to}", 
//						ExchangeDTO.class, 
//						map);
//		ExchangeDTO exchangeDTO = respponse.getBody();
		
		// CHAMADA AO MS EXCHANGE-SERVICE
		ExchangeDTO exchange = exchangeProxy.getExchange(book.getPrice(), "USD", currency);
		
		
		book.setEnvironment(
				"BOOK PORT: " + port + 
				" EXCHANGE PORT: " + exchange.getEnvironment());
		book.setPrice(exchange.getConversionValue());
		book.setCurrency(currency);
		return book;
	}
}

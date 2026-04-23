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
import com.trsystems.repository.BookRepository;

@RestController
@RequestMapping("book-service")
public class BookController {
	
	@Autowired
	private InstanceInformationService information;
	@Autowired
	private BookRepository bookRepository;

	@GetMapping(value="/{id}/{currency}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Book findBook(
			@PathVariable Long id, 
			@PathVariable String currency) {
		
		String port = information.retrieveServerPort();
		
		var book = bookRepository.findById(id).orElseThrow();
		
		HashMap<String, String> map = new HashMap<>();
		map.put("amount", book.getPrice().toString());
		map.put("from", "USD");
		map.put("to", currency);
		
		// CHAMADA AO MS EXCHANGE-SERVICE
		var respponse = new RestTemplate()
				.getForEntity("http://localhost:8000/exchange-service/{amount}/{from}/{to}", 
						ExchangeDTO.class, 
						map);
		
		ExchangeDTO exchangeDTO = respponse.getBody();
		
		book.setEnvironment(port);
		book.setPrice(exchangeDTO.getConversionValue());
		book.setCurrency(currency);
		return book;
	}
}

package com.trsystems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trsystems.environment.InstanceInformationService;
import com.trsystems.model.Book;
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
		book.setEnvironment(port);
		book.setCurrency(currency);
		return book;
	}
}

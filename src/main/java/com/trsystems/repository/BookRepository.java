package com.trsystems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trsystems.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	
}

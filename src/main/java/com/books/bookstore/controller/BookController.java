package com.books.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.books.bookstore.service.BookService;

@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("query/{isbn}")
	public String getData(@PathVariable String isbn) {
		return bookService.retriveBooks(isbn);
	}
	
	@GetMapping("buy/{isbn}/{quantity}")
	public String buy(@PathVariable String isbn, @PathVariable Integer quantity) {
		return bookService.buy(isbn, quantity);
	}
	
	@GetMapping("return/{isbn}/{quantity}")
	public String returnBook(@PathVariable String isbn, @PathVariable Integer quantity) {
		return bookService.returnBook(isbn, quantity);
	}

}

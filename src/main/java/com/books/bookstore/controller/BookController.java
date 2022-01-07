package com.books.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.books.bookstore.dto.BookDTO;
import com.books.bookstore.service.BookService;

/**
 * @author Prasanna Belvalkar
 *
 */
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
	
	@PostMapping("/receive")
	public String receiveNewBook(@RequestBody BookDTO bookDto) {
		System.out.println("isbn: "+bookDto.getIsbn()+" Price: "+bookDto.getPrice()+ " Quantity: "+bookDto.getQuantity());
		return bookService.receiveNewBook(bookDto);
	}
	
	@PutMapping("/updateBookPrice")
	public String updateBookPrice(@RequestBody BookDTO bookDto) {
		return bookService.updateBookPrice(bookDto);
	}
	
	@PostMapping("/multiBuy")
	public String multiBuy(@RequestBody List<BookDTO> booksList) {
		StringBuilder str = new StringBuilder();
		booksList.stream().forEach(item ->{
			str.append(bookService.buy(item.getIsbn(), item.getQuantity()));
			str.append("\n");
		});
		return str.toString();
	}
	
	@PostMapping("/multiReturn")
	public String multiReturn(@RequestBody List<BookDTO> booksList) {
		StringBuilder str = new StringBuilder();
		booksList.stream().forEach(item ->{
			str.append(bookService.returnBook(item.getIsbn(), item.getQuantity()));
			str.append("\n");
		});
		return str.toString();
	}
	
	@GetMapping("/processBatch")
	public String processBatch() {
		return bookService.processBatch();
	}

}

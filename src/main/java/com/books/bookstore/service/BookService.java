package com.books.bookstore.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.bookstore.entity.Book;
import com.books.bookstore.repo.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository repo;
	
	public String retriveBooks(String isbn) {
		Book book = repo.getById(isbn);
		if(book != null) {
			return "There are "+book.getQuantity()+ " copies of "+ book.getIsbn()+ ". Price: "+book.getPrice()+ " each.";
		}
		return "No book found with isbn : "+isbn;
	}
	
	@Transactional
	public String buy(String isbn, Integer quantity) {
		Book book = repo.getById(isbn);
		if(book.getQuantity() >= quantity) {
			book.setQuantity(book.getQuantity()- quantity);
			return "Sold "+quantity+ " copies of "+book.getIsbn()+ " at "+book.getPrice()+ " each. Total: "+quantity*book.getPrice();
		}else {
			return "Less quantity available";
		}
	}
	
	@Transactional
	public String returnBook(String isbn, Integer quantity) {
		Book book = repo.getById(isbn);
		book.setQuantity(book.getQuantity() + quantity);
		return "Refund "+quantity+ " copies of "+book.getIsbn()+ " at "+book.getPrice()+ " each. Total: -"+quantity*book.getPrice();
	}
}

package com.books.bookstore.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookDTO implements Serializable{

	private static final long serialVersionUID = 489519461561772753L;
	
	@JsonProperty("isbn")
	private String isbn;
	
	@JsonProperty("price")
	private Double price;
	
	@JsonProperty("quantity")
	private Integer quantity;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}

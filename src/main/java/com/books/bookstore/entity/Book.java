package com.books.bookstore.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Proxy;

/**
 * @author Prasanna Belvalkar
 *
 */
@Entity
@Proxy(lazy = false)
public class Book implements Serializable{

	private static final long serialVersionUID = 996242158529312919L;

	@Id
	private String isbn;
	
	@Column(nullable = false)
	private Long price;
	
	@Column(nullable = false)
	private Integer quantity;
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}

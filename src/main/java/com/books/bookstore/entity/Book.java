package com.books.bookstore.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

/**
 * @author Prasanna Belvalkar
 *
 */
@Entity
@Proxy(lazy = false)
@Table(name = "BOOK")
public class Book implements Serializable{

	private static final long serialVersionUID = 996242158529312919L;

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
    private Long id;
	
	private String isbn;
	
	private Double price;
	
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

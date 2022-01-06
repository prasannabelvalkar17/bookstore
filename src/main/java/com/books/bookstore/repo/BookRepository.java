package com.books.bookstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.books.bookstore.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

	@Query("SELECT t FROM Book t WHERE t.isbn = ?1")
	Book retriveBooks(String isbn);
}

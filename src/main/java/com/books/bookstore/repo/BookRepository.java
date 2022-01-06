package com.books.bookstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.books.bookstore.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String>{

}

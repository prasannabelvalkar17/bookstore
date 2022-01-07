package com.books.bookstore.service;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.bookstore.dto.BookDTO;
import com.books.bookstore.entity.Book;
import com.books.bookstore.repo.BookRepository;
import com.opencsv.CSVReader;

/**
 * @author Prasanna Belvalkar
 *
 */
@Service
public class BookService {
	
	private static final Logger log = LoggerFactory.getLogger(BookService.class);

	private Integer booksSold = 0;
	private Double totalAmount = Double.valueOf("0");

	@Autowired
	private BookRepository repo;
	
	public String retriveBooks(String isbn) {
		Book book = repo.retriveBooks(isbn);
		if(book != null) {
			return "There are "+book.getQuantity()+ " copies of "+ book.getIsbn()+ ". Price: "+book.getPrice()+ " each.";
		}
		return "No book found with isbn : "+isbn+".";
	}
	
	@Transactional
	public String buy(String isbn, Integer quantity) {
		try {
			Book book = repo.retriveBooks(isbn);
			if(book.getQuantity() >= quantity) {
				book.setQuantity(book.getQuantity()- quantity);
				this.booksSold = this.booksSold + quantity;
				this.totalAmount = this.totalAmount+ (quantity*book.getPrice());
				log.info("Sold {} copies of {} at {} each. Total: {}.",quantity, book.getIsbn(), book.getPrice(), String.format("%.2f", quantity*book.getPrice()));
				return "Sold "+quantity+ " copies of "+book.getIsbn()+ " at "+book.getPrice()+ " each. Total: "+ String.format("%.2f", quantity*book.getPrice())+".";
			}else {
				log.info("Insufficient quantity of {}.", isbn);
				return "Insufficient quantity of "+isbn+ ".";
			}
		}catch(Exception e) {
			log.error("Book {} not available.", isbn);
			return "Book "+isbn+ " not available.";
		}
		
	}
	
	@Transactional
	public String returnBook(String isbn, Integer quantity) {
		Book book = repo.retriveBooks(isbn);
		book.setQuantity(book.getQuantity() + quantity);
		this.booksSold = this.booksSold - quantity;
		this.totalAmount = this.totalAmount - (quantity*book.getPrice());
		log.info("Refund {} copies of {} at {} each. Total: -{}", quantity, book.getIsbn(), book.getPrice(), String.format("%.2f", quantity*book.getPrice()));
		return "Refund "+quantity+ " copies of "+book.getIsbn()+ " at "+book.getPrice()+ " each. Total: -"+ String.format("%.2f", quantity*book.getPrice())+".";
	}
	
	@Transactional
	public String receiveNewBook(BookDTO bookDto) {
		try {
			Book book = repo.retriveBooks(bookDto.getIsbn());
			if(book != null) {
				book.setPrice(bookDto.getPrice());
				book.setQuantity(book.getQuantity() + bookDto.getQuantity());
			}else {
				book = new Book();
				BeanUtils.copyProperties(bookDto, book);
				repo.save(book);
			}
			
			log.info("Received {} copies of book {}.", book.getQuantity(), book.getIsbn());
			return "Received "+book.getQuantity()+ " copies of book "+book.getIsbn()+".";
		}catch(Exception e) {
			log.error("Error occured while receiving book {}", e.getMessage());
			return "Error occured while receiving book" + e.getMessage()+".";
		}
	}
	
	@Transactional
	public String updateBookPrice(BookDTO bookDto) {
		Book book = repo.retriveBooks(bookDto.getIsbn());
		book.setPrice(bookDto.getPrice());
		log.info("New price for {}: {}", book.getIsbn(), book.getPrice());
		return "New price for "+book.getIsbn()+ ": "+book.getPrice();
	}
	
	@Transactional
	public String processBatch() {
		booksSold = 0;
		totalAmount = Double.valueOf("0");
		StringBuilder result = new StringBuilder();
		try (FileReader filereader = new FileReader("src/main/resources/batch.csv"); 
				CSVReader csvReader = new CSVReader(filereader);
				FileWriter fw = new FileWriter("src/main/resources/ProcessedBatch.csv");
				){
	        
	        String[] nextRecord;
	 
	        while ((nextRecord = csvReader.readNext()) != null) {
	        	switch (nextRecord[0]) {
				case "query":
					result.append(retriveBooks(nextRecord[1]));
					result.append("\n");
					break;
					
				case "buy":
					result.append(buy(nextRecord[1], Integer.valueOf(nextRecord[2])));
					result.append("\n");
					break;
					
				case "return":
					result.append(returnBook(nextRecord[1], Integer.valueOf(nextRecord[2])));
					result.append("\n");
					break;
					
				case "receive":
					BookDTO receivedBook = new BookDTO();
					receivedBook.setIsbn(nextRecord[1]);
					receivedBook.setPrice(Double.valueOf(nextRecord[3]));
					receivedBook.setQuantity(Integer.valueOf(nextRecord[2]));
					result.append(receiveNewBook(receivedBook));
					result.append("\n");
					break;
					
				case "update":
					BookDTO updatedBook = new BookDTO();
					updatedBook.setIsbn(nextRecord[1]);
					updatedBook.setPrice(Double.valueOf(nextRecord[2]));
					result.append(updateBookPrice(updatedBook));
					result.append("\n");
					break;
					
				case "multibuy":
					for(int i = 1; i < nextRecord.length; i=i+2) {
						result.append(buy(nextRecord[i], Integer.valueOf(nextRecord[i+1])));
						result.append("\n");
					}
					
					break;
					
				case "multireturn":
					for(int i = 1; i < nextRecord.length; i=i+2) {
						result.append(returnBook(nextRecord[i], Integer.valueOf(nextRecord[i+1])));
						result.append("\n");
					}
					break;

				default:
					break;
				}
	        	
	        }
	        result.append("Total books sold: "+this.booksSold+"\n");
	        result.append("Total amount: "+this.totalAmount+"\n");
	        result.append("Inventory:\n");
	        List<Book> bookList = repo.findAll();
	        bookList.stream().forEach(item -> result.append(item.getIsbn()+ ":"+item.getQuantity()+"\n"));
	        fw.append(result);
	    }
	    catch (Exception e) {
	    	log.error("Error occurred while executing batch : {}", e.getMessage());
	        e.printStackTrace();
	    }
		return result.toString();
	}
}

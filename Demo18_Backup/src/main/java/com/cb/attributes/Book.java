package com.cb.attributes;

import java.time.LocalDate;
import java.util.List;

public class Book {

	public String isbn;
	public List<Author> author;
	public int no_of_pages;
	public double cost;
	public EDITION edition;
	public LocalDate publishedDate;
	public String publisher;
	public Book(String isbn, List<Author> author, int no_of_pages, double cost, EDITION edition,
			LocalDate publishedDate, String publisher) {
		
		this.isbn = isbn;
		this.author = author;
		this.no_of_pages = no_of_pages;
		this.cost = cost;
		this.edition = edition;
		this.publishedDate = publishedDate;
		this.publisher = publisher;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public List<Author> getAuthor() {
		return author;
	}
	public void setAuthor(List<Author> author) {
		this.author = author;
	}
	public int getNo_of_pages() {
		return no_of_pages;
	}
	public void setNo_of_pages(int no_of_pages) {
		this.no_of_pages = no_of_pages;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public EDITION getEdition() {
		return edition;
	}
	public void setEdition(EDITION edition) {
		this.edition = edition;
	}
	public LocalDate getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(LocalDate publishedDate) {
		this.publishedDate = publishedDate;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	
	
	
}

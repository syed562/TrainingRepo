package com.app.attributes;

public class Book {
private final  String isbn;
private final String author;
private final String title;
private boolean available=true;
public Book(String isbn,String title,String author) {
	
	this.isbn = isbn;
	this.author = author;
	this.title=title;
}
public String getIsbn() {
	return isbn;
}

public void setAvailable(boolean v) {
	available=v;
}
public boolean isAvailable() {
	return available;
}

public String getTitle() {
	return title;
}
@Override
public String toString() {
	
	return String.format("%s | %s | %s | %s", isbn, title, author, (available? "Available" : "Borrowed"));
}

}

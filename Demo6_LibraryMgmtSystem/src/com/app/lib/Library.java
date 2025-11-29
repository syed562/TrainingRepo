package com.app.lib;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import com.app.attributes.*;
public class Library {

	private Map<String,Book>books=new HashMap<>();
	private Map<String,Member>users=new HashMap<>();
	
	
	public void addBook(Book b) {
		books.put(b.getIsbn(), b);
	}
	public void addUser(Member m) {
		users.put(m.getId(), m);
	}
	public Collection<Book> displayBooks(){
		return books.values();
	}
	public Collection<Member>displayUser(){
		return users.values();
	}
	public String borrowBook(String isbn,String id) {
		if(!books.containsKey(id))return "Book not found";
		if(!users.containsKey(id))return "User not found";
		if(!books.get(isbn).isAvailable()) return "Book is already borrowed by others stock is over";
		if(users.get(id).getAllBorrowedNmaes().contains(isbn))return "You have already borrowed";
		if(!users.get(id).canBorrow())return "Borrow limit is reached";
		books.get(isbn).setAvailable(false);
		users.get(id).borrow(isbn);
		return "Book borrowed successfully";
	}
	
	
	public String returnBook(String isbn,String id) {
		Book b=books.get(isbn);
		Member m=users.get(id);
		if(b==null)return "Book not found";
		if(m==null)return "Mmeber not found";
		if(!m.getAllBorrowedNmaes().contains(isbn))return "You have not borrowed it";
		b.setAvailable(true);
		m.returned(isbn);
		return "Book is returned";
		
	}
}

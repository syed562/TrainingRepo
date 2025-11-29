package com.app.attributes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Member {
private final String id;
private final String name;
private final String phnum;
private final int maxAllowed = 3;
private Set<String>borrowedBooks=new HashSet<>();
public Member(String id, String name, String phnum) {

	this.id = id;
	this.name = name;
	this.phnum = phnum;
}
public String getId() {
	return id;
}
public String getName() {
	return name;
}
public boolean canBorrow() {
	return borrowedBooks.size()<maxAllowed;
}
public void borrow(String isbn) {
	borrowedBooks.add(isbn);
}

public void returned(String isbn) {
	borrowedBooks.remove(isbn);
}
public ArrayList<String>getAllBorrowedNmaes(){
	return  new ArrayList<>(borrowedBooks);
}
 @Override
public String toString() {
	 return String.format("%s | %s | Borrowed: %d", id, name, borrowedBooks.size());
}
}

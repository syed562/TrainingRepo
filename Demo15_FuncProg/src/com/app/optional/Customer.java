package com.app.optional;

import java.util.Optional;

public class Customer {
String name;
Optional<String>email;
public Customer(String name,String email) {
		this.name = name;
	this.email = Optional.ofNullable(email);
}
public String getName() {
	return name;
}
public Optional<String>getEmail(){
	return email;
}
}

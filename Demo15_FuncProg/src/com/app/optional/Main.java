package com.app.optional;

public class Main {
public static void main(String[] args) {
	Customer user1=new Customer("Aashish Choudhary", "aashish@gmail.com");
	 Customer u2 = new Customer("Sabiha Syed", null);  // email missing

    printEmail(user1);
     printEmail(u2);
}
public static void printEmail(Customer user ) {
    System.out.print(user.getName() + " → ");

    // ✅ Safe: if email present → print it, else print default
    String email = user.getEmail().orElse("No email provided");
    System.out.println(email);
}
}

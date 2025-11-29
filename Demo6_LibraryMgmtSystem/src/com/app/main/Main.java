package com.app.main;

import java.util.Scanner;

import com.app.attributes.*;
import com.app.lib.*;

public class Main {
	private static final Library lib=new Library();
public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	 lib.addBook(new Book("ISBN-001", "Intro to Java", "Alice"));
     lib.addBook(new Book("ISBN-002", "Data Structures", "Bob"));
     lib.addUser(new Member("M1","Sabi","372t47246"));
     lib.addUser(new Member("M2","Tom","74357453"));
     while(true) {
    	 System.out.println("\n 1)List Books  2)List Members   3)Borrow   4)Return  5)Add Book  6)Add Member 0)Exit");
    	 System.out.println("Enter your option: ");
    	 String c=sc.nextLine().trim();
    	 switch(c) {
    	 case "1":
    		 lib.displayBooks().forEach(System.out::println);
    		 break;
    	 case "2":
    		 lib.displayUser().forEach(System.out::println);
    		 break;
    	 case "3":
    		 System.out.println("Book Id: ");
    		 String bid=sc.nextLine().trim(); 
    		 System.out.println("Member Id: ");
    		 String mid=sc.nextLine().trim(); 
    		 System.out.println(lib.borrowBook(bid, mid));
    		 break;
    	 case "4":
    		 System.out.println("Book Id: ");
    		 String bbid=sc.nextLine().trim(); 
    		 System.out.println("Member Id: ");
    		 String mmid=sc.nextLine().trim(); 
    		 System.out.println(lib.returnBook(bbid, mmid));
    		 break;
    	  case "5":
              System.out.print("ISBN: "); String isbn = sc.nextLine().trim();
              System.out.print("Title: "); String t = sc.nextLine().trim();
              System.out.print("Author: "); String a = sc.nextLine().trim();
              lib.addBook(new Book(isbn, t, a));
              System.out.println("Book added.");
              break;
          case "6":
              System.out.print("MemberId: "); String id = sc.nextLine().trim();
              System.out.print("Name: "); String name = sc.nextLine().trim();
              System.out.print("Phone num: "); String phnum = sc.nextLine().trim();
              lib.addUser(new Member(id, name,phnum));
              System.out.println("Member added.");
              break;
          case "0": System.out.println("Bye"); return;
          default: System.out.println("Invalid");
    	 }
     }
     
	
}
}

package com.cb.main;

import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cb.attributes.Author;
import com.cb.attributes.Book;
import com.cb.attributes.EDITION;

public class Main {

	public static void main(String[] args) {
		System.out.println("Main.main()");
	final Logger logg=LogManager.getLogger(Main.class);
		Author a1=new Author();
		Author a2=new Author();
		logg.trace("Hello");
       Book b=new  Book("123",List.of(a1,a2), 12, 564.3,EDITION.BASIC,LocalDate.of(2004, 01,03),"XYZ");
	
      logg.debug(b.getNo_of_pages());
      
      try {
    	  int i=1/0;
    	  
      }catch(ArithmeticException ae) {
    	  logg.error(ae);
      }
	}
}

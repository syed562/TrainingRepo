package com.cb.functional;

public class LambdaDemo {
public static void main(String[] args) {
	Greet greet=name -> System.out.println("Hello "+name);
	greet.sayHello("Sabiha");
}
}

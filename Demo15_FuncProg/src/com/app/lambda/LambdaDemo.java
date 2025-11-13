package com.app.lambda;

import java.util.Optional;

interface Greet{
	void sayHello(String name);
	default void sayHello1(String name) {
		System.out.println("Greet.sayHello1()");
	}
}
public class LambdaDemo {
public static void main(String[] args) {
	Greet greet=(name)->System.out.println("Hello"+name+"!");
	greet.sayHello("Sabi");
	
	Calculation cal=(i,j)->{
		return i+j;
	};
	Calculation cal2=(i,j)->{
		return i*j;
	};
	
	LambdaDemo dob=new LambdaDemo();
	//dob.print(cal);
	dob.print(cal2);
	
Optional<String>sab=dob.getName();
System.out.println(sab.orElse("ntg"));
}

public void print(Calculation obj) {
	System.out.println("LambdaDemo.print()"+obj.add(2, 3));
}
public Optional<String> getName() {
	//return Optional.empty();
	return Optional.of("Sabiha");
}


public void checkOption() {
	Optional<String> name = Optional.ofNullable(null);

    System.out.println("Is Present: " + name.isPresent());

    String result = name.orElse("Default Name");
    System.out.println("Result: " + result);

    name.ifPresent(n -> System.out.println("Hello " + n));

    Optional<String> city = Optional.of("Mumbai");
    System.out.println(city.orElseGet(() -> "Unknown City"));
    System.out.println(city.orElseThrow(() -> new RuntimeException("No city found")));
    
    
}
}

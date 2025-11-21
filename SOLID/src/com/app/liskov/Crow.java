package com.app.liskov;

public class Crow  implements Flyable{

	public void fly() {
		System.out.println("Crow.fly()");
		
	}

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("Crow.eat()");
	}

}

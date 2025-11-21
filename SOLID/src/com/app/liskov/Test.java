package com.app.liskov;

public class Test {
public static void main(String[] args) {
	Flyable fb=new Crow();
	fb.fly();
	Bird b=new Ostrich();
	b.eat();
}
}

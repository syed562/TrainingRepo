package com.app.anony;

interface Greeting{
	void sayHelloo();
}
public class AnonymousOperations {
public static void main(String[] args) {
	Greeting greet=new Greeting() {

		@Override
		public void sayHelloo() {
			System.out.println("AnonymousOperations.main(...).new Greeting() {...}.sayHelloo()");
			
		}
		
	};
	greet.sayHelloo();
	Greeting greet2=new Greeting() {

		@Override
		public void sayHelloo() {
			System.out.println("AnonymousOperations.main(...).new Greeting() {...}.sayHelloo()2");
			
		}
		
	};
	greet2.sayHelloo();
	AnonymousOperations obj=new AnonymousOperations();
	
	
}
public void print() {
	
}
}

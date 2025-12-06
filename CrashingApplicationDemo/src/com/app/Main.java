package com.app;

public class Main {

	public static void main(String[] args) {
		Worker w1=new Worker();
		Thread t1=new Thread(w1);
		t1.run();
	}
}

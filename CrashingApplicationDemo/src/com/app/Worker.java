package com.app;

public class Worker implements Runnable {

	@Override
	public void run() {
		add(2,3);
		
	}
	int add(int a,int b) {
		return a+b;
	}

}

package com.app.work;

import java.io.FileReader;

public class JobThread  extends Thread{

//	public JobThread(String name) {
//		super(name);
//	}
	


	public void run() {
		System.out.println("JobThread.run()");
		System.out.println(isAlive());
	}
}

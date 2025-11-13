package com.app.work;

public class JobProcessing {
public static void main(String[] args) {
	JobThread t1=new JobThread();
	t1.start();
	t1.setName("Sabiha");
	System.out.println("Thread details :"+t1.getId());
	System.out.println("Thread details :"+t1.getName());
	System.out.println("Thread details :"+t1.getPriority());
	System.out.println("Thread details :"+t1.getState());
	
	
	WorkerThread wt=new WorkerThread();
	Thread t2=new Thread(wt);
	t2.start();
	
	}
	}
	



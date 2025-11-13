package com.app.main;

import java.util.HashMap;
import java.util.Scanner;

import com.app.person.Student;

public class Main {
	public static Scanner sc=new Scanner(System.in);
	private static final HashMap<String,Student>stud=new HashMap<>();
	public static void addStudent() {
		System.out.println("Enter the id of student");
		String id=sc.nextLine().trim();
		System.out.println("Enter the name of the student");
		String name=sc.nextLine().trim();
		
		if(stud.containsKey(id))System.out.println("Already exists");
		stud.put(id, new Student(id,name));
		System.out.println("Successfully added");
	}
	
    private static  void addGrade() {
        System.out.print("StudentId: "); String id = sc.nextLine().trim();
        Student s = stud.get(id);
        if (s == null) { System.out.println("No student"); return; }
        System.out.print("Course Code: "); String c = sc.nextLine().trim();
        System.out.print("Numeric Grade (0-100): "); double g = Double.parseDouble(sc.nextLine().trim());
        s.addGrade(c, g);
        System.out.println("Grade added. Current GPA: " + String.format("%.2f", s.computeGPA()));
    }

    private static void showStud() {
    	System.out.println("Enter student id");
    	String id=sc.nextLine().trim();
    	System.out.println(stud.get(id));
    }
public static void main(String[] args) {
	new Main().run();
}
private void run() {
stud.put("S1", new Student("S1","Sabiha"));
boolean running=true;
while(running) {
	System.out.println("1)Add Student 2)Add Grade 3)Show Student 4)List Students 0)exit");
	System.out.println("Enter your choice");
	String choice=sc.nextLine().trim();
	switch(choice) {
	case "1":
		addStudent();
		break;
	case "2":
		addGrade();
		break;
	case "3":
		showStud();
		break;
	case "4":
		stud.values().forEach(System.out::println);
		break;
	case "0": running=false;
	   break;
	default:
		System.out.println("invalid");
	}
		
	}
}
}

package com.app.person;

import java.util.ArrayList;
import java.util.HashMap;

public class Student extends Person {

	public Student(String id, String name) {
		super(id, name);
		
	}
private final HashMap<String,Double>hm=new HashMap<>();
public void addGrade(String subj,Double grade) {
	hm.put(subj, grade);
}

public ArrayList<Double> displayStudentGrades() {
	return new ArrayList<Double>(hm.values());
}

public double computeGPA() {
	if(hm.isEmpty())return 0;
	double sum=0;
	for(double g:hm.values())sum+=g;
	return sum/hm.size();
}

@Override
public String toString() {
    return String.format("%s | %s | GPA: %.2f", id, name, computeGPA());

}


}

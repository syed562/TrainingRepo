package com.example.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Student;
import com.example.exceptions.IllgealStateException;
import com.example.repo.StudentRepo;

@Service
public class StudentService {
	private final StudentRepo sr;
	
	
@Autowired
	public StudentService(StudentRepo sr) {
		
		this.sr = sr;
	}



	public List<Student>getStud(){
//		return List.of
//				(new Student(1l, "sabi",LocalDate.of(2004,03,01), "dhwjdwj@gmail.com",21));
		return sr.findAll();
		
	}
	
	
	public Student addStudent(Student s) throws IllgealStateException {
		Optional<Student>stdemail=sr.getStudentByEmail(s.getEmail());
		if(stdemail.isPresent())throw new IllgealStateException("email is taken");
		return sr.save(s);
	}
	

	public void deleteStudent(Long id) {
		boolean exists=sr.existsById(id);
		if(!exists) throw new IllegalStateException("student with id not exits");
		sr.deleteById(id);
	}


	@Transactional
	public void update(Long id, String name, String email) {
		// TODO Auto-generated method stub
		Optional<Student> st=sr.findById(id);
		boolean exists=sr.existsById(id);
		if(!exists) throw new IllegalStateException("student with id not exits");
		if(name!=null && name.length()>0 && !name.equalsIgnoreCase(st.get().getName())) {
			st.get().setName(name);
		}
		
		if(email!=null && email.length()>0 && !email.equalsIgnoreCase(st.get().getEmail())) {
			st.get().setEmail(email);
		}
		
		
		
	}
	
	
	 
}

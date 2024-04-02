package com.example.demojpa.studentController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demojpa.entity.Student;
import com.example.demojpa.repo.StudentRepo;

@RestController
public class StudentController {
	@Autowired
	StudentRepo studentRepo;
	
	
    @PostMapping("/app/students")
	public ResponseEntity<Student> saveStudnet(@RequestBody Student student) {
    	return new ResponseEntity<>(studentRepo.save(student), HttpStatus.CREATED);
	}
    @GetMapping("/app/students")
    public ResponseEntity<List<Student>> getStudent(){
    	return new	ResponseEntity<>(studentRepo.findAll(), HttpStatus.OK);
    }
    
    @GetMapping("/app/students/{id}")
    public ResponseEntity<Student> getStuden(@PathVariable long id){
    Optional<Student> student = studentRepo.findById(id);
    if(student.isPresent()) {
    	return new ResponseEntity<>(student.get() , HttpStatus.OK);
    }else {
    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    }
    
    
    
    @PutMapping("/app/students/{id}")
    public ResponseEntity<Student> updateStuden(@PathVariable long id , @RequestBody Student stud){
    Optional<Student> student = studentRepo.findById(id);
    student.get().setStudentName(stud.getStudentName());
    student.get().setStudentEmail(stud.getStudentEmail());
    student.get().setStudentAddress(stud.getStudentAddress());	
    if(student.isPresent()) {
    	return new ResponseEntity<>(studentRepo.save(student.get()) , HttpStatus.OK);
    }else {
    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    }
    
    
    @DeleteMapping("/app/students/{id}")
    public ResponseEntity<Void> DeleteStudent(@PathVariable long id){
    Optional<Student> student = studentRepo.findById(id);
    if(student.isPresent()) {
    	studentRepo.deleteById(id);
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }else {
    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    }
}

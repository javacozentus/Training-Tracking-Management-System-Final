package com.cozentus.TrainingTracking.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cozentus.TrainingTracking.Model.Student;
import com.cozentus.TrainingTracking.Service.StudentService;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "*",allowedHeaders ="*") 
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class StudentRestController {
	
	@Autowired
	private StudentService studentService;
    @GetMapping("/show/all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
    @GetMapping("/show/{studentId}")
    public Student getStudentById(@PathVariable Integer studentId) {
        return studentService.getStudentById(studentId);
    }
    @PostMapping("/add")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }
    @PostMapping("/update/{studentId}")
    public Student updateStudent(@PathVariable Integer studentId, @RequestBody Student updatedStudent) {
        return studentService.updateStudent(studentId, updatedStudent);
    }
    @PostMapping("delete/{studentId}")
    public void deleteStudent(@PathVariable Integer studentId) {
        studentService.deleteStudent(studentId);
    }
    //Stuent-Batch Update
    
    
    
    @GetMapping("/show/uniqueByStudentCode")
    public List<Student> getUniqueStudentsByStudentCode() {
        return studentService.getUniqueStudentsByStudentCode();
    }
}

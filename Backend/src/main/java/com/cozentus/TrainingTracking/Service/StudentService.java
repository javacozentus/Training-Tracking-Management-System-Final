package com.cozentus.TrainingTracking.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cozentus.TrainingTracking.Model.Student;
import com.cozentus.TrainingTracking.Repository.BatchRepository;
import com.cozentus.TrainingTracking.Repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private BatchService batchServices;
	
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    public Student getStudentById(Integer studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }
    public Student updateStudent(Integer studentId, Student updatedStudent) {
        updatedStudent.setStudentId(studentId);
        return studentRepository.save(updatedStudent);
    }
    public void deleteStudent(Integer studentId) {
        studentRepository.deleteById(studentId);
    }


    //Student-batch Upadte

    public Student updateStudentProgramAndBatch(int studentId, int programId, int batchId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        student.setProgramId(programId);
        student.setBatchId(batchId);
        return studentRepository.save(student);
    }

    public List<Student> assignProgramAndBatchToStudents(List<Integer> studentIds, int programId) {
        int latestBatchId = batchServices.findLatestBatch().getBatchId();
        List<Student> updatedStudents = new ArrayList<>();
 
        for (Integer studentId : studentIds) {
            Student existingStudent = studentRepository.findById(studentId).orElse(null);
 
            if (existingStudent != null) {
                // Create a new student record with the latest batch and program
                Student newStudent = new Student();
                newStudent.setProgramId(programId);
                newStudent.setBatchId(latestBatchId);
 
                // Copy the additional properties from the existing student
                newStudent.setStudentCode(existingStudent.getStudentCode());
                newStudent.setStudentName(existingStudent.getStudentName());
                newStudent.setStudentEmail(existingStudent.getStudentEmail());
                // ...
 
                // Save the new student record
                updatedStudents.add(studentRepository.save(newStudent));
            }
        }
 
        return updatedStudents;
    } 
    
    
    public List<Student> getUniqueStudentsByStudentCode() {
        return studentRepository.findUniqueStudentsByStudentCode();
    }
    
    
  //Updating the second form of batch table
 // Update program ID for students in a specific batch
    public List<Student> updateProgramForStudentsInBatch(Integer batchId, Integer programId) {
        List<Student> updatedStudents = new ArrayList<>();
 
        // Get students in the specified batch
        List<Student> studentsInBatch = studentRepository.findStudentsByBatchId(batchId);
 
        for (Student student : studentsInBatch) {
            student.setProgramId(programId);
            updatedStudents.add(studentRepository.save(student));
        }
 
        return updatedStudents;
    }
    
    
    // new topic
    
    public List<Integer> getProgramIdsByBatchId(Integer batchId) {
        return studentRepository.findProgramIdsByBatchId(batchId);
    }

    
}

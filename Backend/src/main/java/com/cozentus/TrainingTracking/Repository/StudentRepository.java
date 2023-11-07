package com.cozentus.TrainingTracking.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cozentus.TrainingTracking.Model.Student;

public interface StudentRepository extends JpaRepository <Student, Integer> {
	
	@Query("SELECT s FROM Student s WHERE s.studentId IN " +
	           "(SELECT MIN(s2.studentId) FROM Student s2 GROUP BY s2.studentCode)")
	    List<Student> findUniqueStudentsByStudentCode();
	
	@Query("SELECT s FROM Student s WHERE s.batchId = :batchId")
    List<Student> findStudentsByBatchId(Integer batchId);
	
	
	// Define a custom query to fetch program IDs by batch ID
    @Query("SELECT s.programId FROM Student s WHERE s.batchId = :batchId")
    List<Integer> findProgramIdsByBatchId(@Param("batchId") Integer batchId);

	
}

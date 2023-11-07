package com.cozentus.TrainingTracking.Repository;

import java.util.Date;
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.cozentus.TrainingTracking.Model.EvaluationName;
 
public interface ExamRepository extends JpaRepository<EvaluationName, Integer>{
	
	@Modifying
	@Query("UPDATE EvaluationName e SET e.marksSecured = :marksSecured WHERE e.totalMarks = :totalMarks AND e.submissionDate = :submissionDate AND e.evaluationName = :evaluationName AND e.studentId = :studentId AND e.batchId = :batchId AND e.courseId = :courseId AND e.teacherId = :teacherId")
	void updateMarks(@Param("marksSecured") Integer marksSecured, @Param("totalMarks") Integer totalMarks, @Param("submissionDate") Date submissionDate, @Param("evaluationName") String evaluationName, @Param("studentId") Integer studentId, @Param("batchId") Integer batchId, @Param("courseId") Integer courseId, @Param("teacherId") Integer teacherId);
 
//	List<EvaluationName> getAllExams(String string);
	
	List<EvaluationName> findAll();
	
	List<EvaluationName> findByType(String type);

}

package com.cozentus.TrainingTracking.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cozentus.TrainingTracking.Model.Course;
import com.cozentus.TrainingTracking.Model.EvaluationName;
import com.cozentus.TrainingTracking.Model.Topic;
import com.cozentus.TrainingTracking.Service.CourseService;
import com.cozentus.TrainingTracking.Service.ExamService;
import com.cozentus.TrainingTracking.Service.TopicService;
import jakarta.transaction.Transactional;
 
@RestController
@RequestMapping("/exam")
@CrossOrigin(origins = "*",allowedHeaders ="*") 
//@PreAuthorize("hasAuthority('ROLE_TEACHER')")
public class ExamRestController {
 
	@Autowired
	private ExamService examService;
	
	@Autowired
	private CourseService  courseService;
	
	@Autowired
	private TopicService topicService;
	
	@GetMapping("/allExams")
	public ResponseEntity<List<EvaluationName>> getAllExams(){
		List<EvaluationName> all_attendances=examService.listOfExams();
		return ResponseEntity.ok(all_attendances);
	}
		
	@PostMapping("/addExam")
	public ResponseEntity<EvaluationName> addExams(@RequestBody EvaluationName evaluationName) {
		EvaluationName added_attendance=examService.addExam(evaluationName);
		return ResponseEntity.ok(added_attendance);
	}
	
	@PutMapping("/update-exam/{evaluationId}")
	public ResponseEntity<EvaluationName> updateExmams(@RequestBody EvaluationName evaluationName,@PathVariable("evaluationId")Integer evaluationId) {
		EvaluationName updated_exam =examService.updateExam(evaluationName,evaluationId);
		return ResponseEntity.ok(updated_exam);	
	}
	
	@DeleteMapping("/delete-exam/{evaluationId}")
	public ResponseEntity<Void> deleteExamByIds(@PathVariable("evaluationId") Integer evaluationId) {
		Optional<EvaluationName> check_exam=examService.getByEvaluationId(evaluationId);
		if(check_exam.isPresent()) {
			examService.deleteExamById(evaluationId);
		        return ResponseEntity.ok().build();
		}
		else {
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/{evaluationId}")
	public ResponseEntity<Optional<EvaluationName>> getByEvaluationId(@PathVariable("evaluationId") Integer evaluationId) {
		return ResponseEntity.ok(examService.getByEvaluationId(evaluationId));
	}
	
	@GetMapping("/all-courses/{programId}")
	public ResponseEntity<List<Course>> getCoursesByProgramId(@PathVariable("programId") Integer programId){
		return ResponseEntity.ok(courseService.getCoursesByProgramId(programId));
	}
	
	@GetMapping("/all-topics/{courseId}")
	public ResponseEntity<List<Topic>> getTopicsByCourseId(@PathVariable("courseId") Integer courseId){
		return ResponseEntity.ok(topicService.getTopicByCourseId(courseId));
	}
	
//	@PostMapping("/add-marks")
//	public ResponseEntity<Void> addMarks(@RequestBody List<EvaluationName> marks) {
//		examService.addMarks(marks);
//		return ResponseEntity.ok().build();
//	}
	
	@Transactional
	@PostMapping("/update-marks")
	public ResponseEntity<Void> updateMarks(@RequestBody List<EvaluationName> marks) {
		examService.updateMarks(marks);
		return ResponseEntity.ok().build();
	}
	
	
	@GetMapping("/getExams")
	public ResponseEntity<List<EvaluationName>> getExams() {
	    List<EvaluationName> exams = examService.getExamsByType("Exam");
	    return ResponseEntity.ok(exams);
	}
	
	@PostMapping("/add-marks")
    public ResponseEntity<Void> addMarks(@RequestBody List<EvaluationName> marks) {
        List<Integer> studentIds = marks.stream()
            .map(EvaluationName::getStudentId)
            .collect(Collectors.toList());
        examService.addMarks(marks, studentIds);
        return ResponseEntity.ok().build();
    }

}

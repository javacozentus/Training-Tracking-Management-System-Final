package com.cozentus.TrainingTracking.RestController;

import java.util.List;
import java.util.Optional;
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
import com.cozentus.TrainingTracking.Service.AssignmentService;
import com.cozentus.TrainingTracking.Service.CourseService;
import com.cozentus.TrainingTracking.Service.TopicService;
 
@RestController
@RequestMapping("/assignment")
@CrossOrigin(origins = "*",allowedHeaders ="*") 
//@PreAuthorize("hasAuthority('ROLE_TEACHER')")
public class AssignmentRestController {
	@Autowired 
	private AssignmentService assignemntService;
	@Autowired
	private CourseService courseService;
 
	@Autowired
	private TopicService topicService;
	@GetMapping("/allAssignments")
	public ResponseEntity<List<EvaluationName>> getAllAssignments(){
		List<EvaluationName> all_assignments=assignemntService.listOfAssignments();
		return ResponseEntity.ok(all_assignments);	
	}
	@PostMapping("/addAssignment")
	public ResponseEntity<EvaluationName> addAssignment(@RequestBody EvaluationName evaluationName) {
		EvaluationName added_assignment=assignemntService.addAssignment(evaluationName);
		return ResponseEntity.ok(added_assignment);
	}
	@PutMapping("/update-assignment/{evaluationId}")
	public ResponseEntity<EvaluationName> updateAssignment(@RequestBody EvaluationName evaluationName,@PathVariable("evaluationId")Integer evaluationId) {
		EvaluationName updated_assignment =assignemntService.updateAssignment(evaluationName,evaluationId);
		return ResponseEntity.ok(updated_assignment);
	}
	@DeleteMapping("/delete-assignment/{evaluationId}")
	public ResponseEntity<Void> deleteAssignmentByIds(@PathVariable("evaluationId") Integer evaluationId) {
		Optional<EvaluationName> check_assignment=assignemntService.findByEvaluationId(evaluationId);
		if(check_assignment.isPresent()) {
			assignemntService.deleteAssignmentById(evaluationId);
		        return ResponseEntity.ok().build();
		}
		else {
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} 
	}
	
	
	
	@GetMapping("/getAssignments")
	public ResponseEntity<List<EvaluationName>> getAssignments() {
	    List<EvaluationName> assignments = assignemntService.getAssignmentsByType("Assignment");
	    return ResponseEntity.ok(assignments);
	}

	
	
	
	
	
	
	@GetMapping("/{evaluationId}")
	public ResponseEntity<Optional<EvaluationName>> getAssignmentByEvaluationId(@PathVariable("evaluationId") Integer evaluationId) {
		return ResponseEntity.ok(assignemntService.findByEvaluationId(evaluationId));
	}
	@GetMapping("/all-courses/{programId}")
	public ResponseEntity<List<Course>> getCoursesByProgramId(@PathVariable("programId") Integer programId) {
		return ResponseEntity.ok(courseService.getCoursesByProgramId(programId));
	}
 
	@GetMapping("/all-topics/{courseId}")
	public ResponseEntity<List<Topic>> getTopicsByCourseId(@PathVariable("courseId") Integer courseId) {
		return ResponseEntity.ok(topicService.getTopicByCourseId(courseId));
	}
}

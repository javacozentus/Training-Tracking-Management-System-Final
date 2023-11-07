package com.cozentus.TrainingTracking.RestController;
 
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cozentus.TrainingTracking.DTO.AttendanceBulkRequestDTO;
import com.cozentus.TrainingTracking.Model.Attendance;
import com.cozentus.TrainingTracking.Model.Batch;
import com.cozentus.TrainingTracking.Model.Course;
import com.cozentus.TrainingTracking.Model.Program;
import com.cozentus.TrainingTracking.Model.Topic;
import com.cozentus.TrainingTracking.Service.AttendanceService;
import com.cozentus.TrainingTracking.Service.BatchService;
import com.cozentus.TrainingTracking.Service.CourseService;
import com.cozentus.TrainingTracking.Service.ProgramService;
import com.cozentus.TrainingTracking.Service.StudentService;
import com.cozentus.TrainingTracking.Service.TopicService;
 
@RestController
@RequestMapping("/attendance")
@CrossOrigin(origins = "*",allowedHeaders ="*") 
//@PreAuthorize("hasAuthority('ROLE_TEACHER')")
public class AttendanceRestController {
 
	@Autowired
	private AttendanceService attendanceService;
 
	@Autowired
	private CourseService courseService;
 
	@Autowired
	private TopicService topicService;
	
	@Autowired
	private StudentService studentServices;
	
	@Autowired
	private ProgramService programService;
	
	@Autowired
	private BatchService batchService;
	
	
	@GetMapping("/batches/show/all")
	public ResponseEntity<List<Batch>> getAllBatches() {
		return ResponseEntity.ok(batchService.getAllBatches());
	}
	
	@GetMapping("/allAttendances")
	public ResponseEntity<List<Attendance>> getAllAttendances() {
		List<Attendance> all_attendances = attendanceService.listOfAttendances();
		return ResponseEntity.ok(all_attendances);
	}
 
//	@PostMapping("/addAttendance")
//	public ResponseEntity<Attendance> addAttendances(@RequestBody Attendance attendance) {
//		Attendance added_attendance = attendanceService.addAttendance(attendance);
//		return ResponseEntity.ok(added_attendance);
//	}
 
	@PutMapping("/update/{attendanceId}")
	public ResponseEntity<Attendance> updateAttendances(@RequestBody Attendance attendance,
			@PathVariable("attendanceId") Integer attendanceId) {
		Attendance updated_attendance = attendanceService.updateAttendance(attendance, attendanceId);
		return ResponseEntity.ok(updated_attendance);
	}
 
	@DeleteMapping("/delete/{attendanceId}")
	public ResponseEntity<Void> deleteAttendanceByIds(@PathVariable("attendanceId") Integer attendanceId) {
 
		Optional<Attendance> check_attendance = attendanceService.findByAttendanceId(attendanceId);
		if (check_attendance.isPresent()) {
			attendanceService.deleteAttendanceById(attendanceId);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
 
	@GetMapping("/{attendanceId}")
	public ResponseEntity<Optional<Attendance>> getByAttendanceId(@PathVariable("attendanceId") Integer attendanceId) {
		return ResponseEntity.ok(attendanceService.findByAttendanceId(attendanceId));
	}
 
	@GetMapping("/allCourses/{programId}")
	public ResponseEntity<List<Course>> getCoursesByProgramId(@PathVariable("programId") Integer programId) {
		return ResponseEntity.ok(courseService.getCoursesByProgramId(programId));
	}
 
	@GetMapping("/allTopics/{courseId}")
	public ResponseEntity<List<Topic>> getTopicsByCourseId(@PathVariable("courseId") Integer courseId) {
		return ResponseEntity.ok(topicService.getTopicByCourseId(courseId));
	}
	
	@PostMapping("/update/topic/{topicId}")
	public ResponseEntity<Void> updateTopicPercentage(@RequestBody Integer topicPercentage,
			@PathVariable("topicId") Integer topicId) {
		topicService.updateTopicPercentageCompleted(topicPercentage, topicId);
		return ResponseEntity.ok().build();
	}
 
//	@PostMapping("/addAttendances")
//	public ResponseEntity<Void> addAttendances(@RequestBody List<Attendance> attendances) {
//		attendanceService.addAttendances(attendances);
//		return ResponseEntity.ok().build();
//	}
	
//	@Transactional
//	@PostMapping("/updateAttendances")
//	public ResponseEntity<Void> updateAttendances(@RequestBody List<Attendance> attendances) {
//		attendanceService.updateAttendances(attendances);	
//		return ResponseEntity.ok().build();
//	}
	
	// For Program
	@GetMapping("/programsByBatch/{batchId}")
	public ResponseEntity<List<Program>> getProgramsByBatch(@PathVariable("batchId") Integer batchId) {
	    // Retrieve program IDs associated with the batch from the Student table
	    List<Integer> programIds = studentServices.getProgramIdsByBatchId(batchId);
	    // Fetch program data for the retrieved program IDs
	    List<Program> programs = programService.getProgramsByProgramIds(programIds);
	    return ResponseEntity.ok(programs);
	}
	
	
//	@PostMapping("/addAttendance")
//    public ResponseEntity<List<Attendance>> addAttendances(@RequestBody AttendanceRequestDTO attendanceRequest) {
//        List<Attendance> added_attendance = attendanceService.addAttendance(attendanceRequest);
//        return ResponseEntity.ok(added_attendance);
//    }
	
	@PostMapping("/addAttendances")
	public ResponseEntity<Void> addBulkAttendances(@RequestBody AttendanceBulkRequestDTO request) {
	    Integer topicId = request.getTopicId();
	    List<Integer> studentIds = request.getStudentIds();
	    Date date = request.getDate();
	    Boolean attendance = request.getAttendance();
	    Integer batchId = request.getBatchId();
 
	    for (Integer studentId : studentIds) {
	        Attendance attendanceRecord = new Attendance();
	        attendanceRecord.setStudentId(studentId);
	        attendanceRecord.setTopicId(topicId);
	        attendanceRecord.setDate(date);
	        attendanceRecord.setAttendance(attendance);
	        attendanceRecord.setBatchId(batchId); // Set the batchId
	        // Set other fields like createdDate, createdBy, etc. if needed
	        attendanceService.addAttendance(attendanceRecord);
	    }
	    return ResponseEntity.ok().build();
	}
}
package com.cozentus.TrainingTracking.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cozentus.TrainingTracking.DTO.TeacherCourseRequestDTO;
import com.cozentus.TrainingTracking.Model.Batch;
import com.cozentus.TrainingTracking.Model.BatchTeacherCourse;
import com.cozentus.TrainingTracking.Model.Student;
import com.cozentus.TrainingTracking.Repository.BatchTeacherCourseRepository;
import com.cozentus.TrainingTracking.Service.BatchService;
import com.cozentus.TrainingTracking.Service.StudentService;
import com.cozentus.TrainingTracking.Service.TeacherCourseService;

@RestController
@RequestMapping("/batch")
@CrossOrigin(origins = "*",allowedHeaders ="*") 

//@PreAuthorize("hasAuthority('ROLE_TEACHER') ")

public class BatchRestController {
	@Autowired
	private BatchService batchService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private TeacherCourseService teacherCourseService;
	@Autowired
	private BatchTeacherCourseRepository  batchTeacherCourseRepo;
 
	@GetMapping("/show/all")
	public ResponseEntity<List<Batch>> getAllBatches() {
		return ResponseEntity.ok(batchService.getAllBatches());
	}
 
	@GetMapping("/show/{id}")
	public ResponseEntity<Optional<Batch>> getBatchById(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(batchService.getBatchById(id));
	}
 
	@PostMapping("/add")
	public ResponseEntity<Batch> addBatch(@RequestBody Batch program) {
		return ResponseEntity.ok(batchService.addBatch(program));
	}
 
	@PostMapping("/update/{id}")
	public ResponseEntity<Batch> updateBatch(@RequestBody Batch program, @PathVariable("id") Integer id) {
		return ResponseEntity.ok(batchService.updateBatch(program, id));
	}
	@PostMapping("/delete/{id}")
	public ResponseEntity<Void> deleteBatchById(@PathVariable("id") Integer id) {
		batchService.deleteBatchById(id);
		return ResponseEntity.ok().build();
	}
	// Student Update
//	@PostMapping("/updateStudentBatch/{studentId}")
//	public ResponseEntity<Student> updateStudentProgramAndBatch(
//	    @PathVariable("studentId") Integer studentId,
//	    @RequestBody Map<String, Integer> requestData
//	) {
//	    int programId = requestData.get("program_id");
//	    int latestBatchId = batchService.findLatestBatch().getBatchId();
//	    
//	    Student updatedStudent = studentService.updateStudentProgramAndBatch(studentId, programId, latestBatchId);
//	    return ResponseEntity.ok(updatedStudent);
//	}

 
	
	// Program_id & Batch_id on Student table
	@PostMapping("/assignProgramAndBatch")
	public ResponseEntity<List<Student>> assignProgramAndBatchToStudents(
	    @RequestBody Map<String, Object> requestData
	) {
	    Integer programId = (Integer) requestData.get("program_id");
	    List<Integer> studentIds = (List<Integer>) requestData.get("student_ids");
 
	    List<Student> updatedStudents = studentService.assignProgramAndBatchToStudents(studentIds, programId);
	    return ResponseEntity.ok(updatedStudents);
	}

	@PostMapping("/storeTeacherCourse")
	public ResponseEntity<Void> storeTeacherCourseInBatchTeacherCourse(@RequestBody TeacherCourseRequestDTO request) {
	    Integer courseId = request.getCourse_id();
	    Integer teacherId = request.getTeacher_id();
 
	    // Use your service to get the teacherCourse_id
	    Integer teacherCourseId = teacherCourseService.findTeacherCourseIdByCourseAndTeacher(courseId, teacherId);
 
	    if (teacherCourseId != null) {
	        // Update the batch with the teacherCourse_id
	        Batch batch = batchService.findLatestBatch();
	        batch.setTeacherCourseId(teacherCourseId);
	        batchService.updateBatch(batch, batch.getBatchId());
 
	        // Create a new entry in BatchTeacherCourse
	        BatchTeacherCourse batchTeacherCourse = new BatchTeacherCourse();
	        batchTeacherCourse.setBatchId(batch.getBatchId());
	        batchTeacherCourse.setTeacherCourseId(teacherCourseId);
	        batchTeacherCourseRepo.save(batchTeacherCourse);
 
	        return ResponseEntity.ok().build();
	    } else {
	        // Handle the case where teacherCourse_id is not found
	        // You can return an error response or handle it based on your requirements.
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }
	}	
	
	
	// Update program ID for students in a specific batch
    @PostMapping("/updateProgramForStudentsInBatch")
    public ResponseEntity<List<Student>> updateProgramForStudentsInBatch(
        @RequestBody Map<String, Integer> requestData
    ) {
        Integer batchId = requestData.get("batchId");
        Integer programId = requestData.get("programId");
 
        if (batchId == null || programId == null) {
            return ResponseEntity.badRequest().build();
        }
 
        List<Student> updatedStudents = studentService.updateProgramForStudentsInBatch(batchId, programId);
 
        if (!updatedStudents.isEmpty()) {
            return ResponseEntity.ok(updatedStudents);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

package com.cozentus.TrainingTracking.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cozentus.TrainingTracking.Model.Teacher;
import com.cozentus.TrainingTracking.Service.TeacherCourseService;
import com.cozentus.TrainingTracking.Service.TeacherService;

@RestController
@RequestMapping("/teacher")
@CrossOrigin(origins = "*",allowedHeaders ="*") 
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class TeacherCourseRestController {
	
	@Autowired
    private TeacherCourseService teacherCourseService;
	
	
	
	@GetMapping("/courses/{teacherId}")
    public ResponseEntity<List<Integer>> getCourseIdsByTeacherId(@PathVariable Integer teacherId) {
        List<Integer> courseIds = teacherCourseService.getCourseIdsByTeacherId(teacherId);
        if (courseIds.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(courseIds, HttpStatus.OK);
    }
	//Get Teachers through Course Id
	@GetMapping("/teachersByCourseId/{courseId}")
	public ResponseEntity<List<Teacher>> getTeachersByCourseId(@PathVariable Integer courseId) {
	    List<Teacher> teachers = teacherCourseService.getTeachersByCourseId(courseId);

	    if (teachers.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

	    return new ResponseEntity<>(teachers, HttpStatus.OK);
	}

//	@PostMapping("/updateBatchTeacherCourse")
//    public ResponseEntity<String> updateBatchTeacherCourse(
//        @RequestParam("courseId") Integer courseId,
//        @RequestParam("teacherId") Integer teacherId
//    ) {
//        // Get the teacherCourseId
//        Integer teacherCourseId = teacherCourseService.getTeacherCourseId(courseId, teacherId);
//
//        if (teacherCourseId != null) {
//            // Update the teacherCourseId in the batch table
//        	batchServices.updateBatchTeacherCourse(courseId, teacherCourseId);
//            return ResponseEntity.ok("Batch teacherCourseId updated successfully.");
//        } else {
//            return ResponseEntity.badRequest().body("TeacherCourse not found for the given courseId and teacherId.");
//        }
//    }
}

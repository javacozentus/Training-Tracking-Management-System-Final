package com.cozentus.TrainingTracking.RestController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cozentus.TrainingTracking.DTO.TeacherCourseDTO;
import com.cozentus.TrainingTracking.Model.Teacher;
import com.cozentus.TrainingTracking.Service.TeacherService;

@RestController
@RequestMapping("/teacher")
@CrossOrigin(origins = "*",allowedHeaders ="*") 
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class TeacherRestController {
	
	@Autowired
	private TeacherService teacherService;
 
	@GetMapping("/show/{id}")
	public ResponseEntity<Optional<Teacher>> getTeacherById(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(teacherService.getTeacherById(id));
	}
//	@PostMapping("/add")
//	public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher) {
//		teacherService.addTeacher(teacher);
//		return ResponseEntity.ok(teacherService.addTeacher(teacher));
//	}
	
	@PostMapping("/addTeacherWithCourses")
    public ResponseEntity<Teacher> addTeacherWithCourses(
        @RequestBody TeacherCourseDTO request
    ) {
        Teacher addedTeacher = teacherService.addTeacherWithCourses(request.getTeacher(), request.getCourseIds());
        return ResponseEntity.ok(addedTeacher);
    }
//	@PostMapping("/update/{id}")
//	public ResponseEntity<Teacher> updateTeacher(@RequestBody Teacher teacher, @PathVariable("id") Integer id) {
//		return ResponseEntity.ok(teacherService.updateTeacher(teacher, id));
//	}
	@PostMapping("/delete/{id}")
	public ResponseEntity<Void> deleteTeacherById(@PathVariable("id") Integer id) {
		teacherService.deleteTeacherById(id);
		return ResponseEntity.ok().build();
	}
	
//	@GetMapping("/course/{courseId}")
//	public ResponseEntity<List<Teacher>> getTeachersByCourseId(@PathVariable("courseId") Integer courseId) {
//		return ResponseEntity.ok(teacherService.getTeachersByCourseId(courseId));
//	}
	
	// GET API for Teacher + Courses for that teacher
	@GetMapping("/show/all")
	public ResponseEntity<List<TeacherCourseDTO>> getAllTeachersWithCoursesAndCourseNames() {
		List<TeacherCourseDTO> teachers = teacherService.getAllTeachersWithCoursesAndCourseNames();
		return ResponseEntity.ok(teachers);
	}
	
	@PostMapping("/update/{id}")
	public ResponseEntity<Teacher> updateTeacher(
	    @PathVariable("id") Integer teacherId,
	    @RequestBody TeacherCourseDTO teacherCourseDTO
	) {
	    // Extract teacher and course data from the request body
	    Teacher teacher = teacherCourseDTO.getTeacher();
	    List<Integer> updatedCourseIds = teacherCourseDTO.getCourseIds();

	    // Call the service method to update the teacher and associated courses
	    Teacher updatedTeacher = teacherService.updateTeacherWithCourses(teacher, teacherId, updatedCourseIds);
	    return ResponseEntity.ok(updatedTeacher);
	}
}

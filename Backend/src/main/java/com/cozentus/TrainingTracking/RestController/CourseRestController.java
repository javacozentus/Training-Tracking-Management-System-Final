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

import com.cozentus.TrainingTracking.DTO.CourseWithIdDTO;
import com.cozentus.TrainingTracking.Model.Course;
import com.cozentus.TrainingTracking.Service.CourseService;

@RestController
@RequestMapping("/course")
@CrossOrigin(origins = "*",allowedHeaders ="*") 
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class CourseRestController {
	
	@Autowired
    private CourseService courseService;
    @GetMapping("/show/all")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }
    @GetMapping("/show/{id}")
    public ResponseEntity<Optional<Course>> getCourseById(@PathVariable Integer id) {
        Optional<Course> course = courseService.findCourseById(id);
        return ResponseEntity.ok(course);
    }
    @PostMapping("/add")
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        Course createdCourse = courseService.addCourse(course);
        return ResponseEntity.ok(createdCourse);
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<Course> updateCourse(@RequestBody Course course, @PathVariable Integer id) {
        Course updatedCourse = courseService.updateCourse(course, id);
        return ResponseEntity.ok(updatedCourse);
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourseById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/show/program/{id}")
	public ResponseEntity<List<Course>> getCoursesByProgramId(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(courseService.getCoursesByProgramId(id));
	}
//    
//     Modify the endpoint to retrieve the latest programId from the Program Service
//    @PutMapping("/updateCourseProgramId/{courseId}")
//    public ResponseEntity<Course> updateCourseProgramId(@PathVariable Integer courseId) {
//        Course updatedCourse = courseService.updateCourseProgramId(courseId);
//        if (updatedCourse != null) {
//            return ResponseEntity.ok(updatedCourse);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
    @PostMapping("/updateCourseProgramId/{courseId}/{programId}")
    public ResponseEntity<Course> updateCourseProgramId(
            @PathVariable Integer courseId,
            @PathVariable Integer programId) {
        Course updatedCourse = courseService.updateCourseProgramId(courseId, programId);
        if (updatedCourse != null) {
            return ResponseEntity.ok(updatedCourse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/distinctCourseNames")
    public ResponseEntity<List<String>> getDistinctCourseNames() {
        List<String> distinctCourseNames = courseService.getDistinctCourseNames();
        return ResponseEntity.ok(distinctCourseNames);
    }
    //CourseIDByProgramID
    @GetMapping("/courseDetailsByProgramId/{programId}")
    public ResponseEntity<List<CourseWithIdDTO>> getCourseDetailsByProgramId(@PathVariable Integer programId) {
        List<CourseWithIdDTO> courseDetails = courseService.getCourseDetailsByProgramId(programId);
        return ResponseEntity.ok(courseDetails);
    }
//    
//    @GetMapping("/courseDetailsByProgramId")
//    public ResponseEntity<List<CourseWithIdDTO>> getCourseDetailsByProgramId(@RequestBody Map<String, Object> requestBody) {
//    	Integer programId = (Integer) requestBody.get("programId");
//        List<CourseWithIdDTO> courseDetails = courseService.getCourseDetailsByProgramId(programId);
//        return ResponseEntity.ok(courseDetails);
//    }

    
   

    
    // distinct Course dropdown
    
    @GetMapping("/coursesWithNullProgramId")
    public ResponseEntity<List<Course>> getCoursesWithNullProgramId() {
        List<Course> coursesWithNullProgramId = courseService.getCoursesWithNullProgramId();
        if (coursesWithNullProgramId.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(coursesWithNullProgramId);
    }
    
    
    
    @GetMapping("/distinctCoursesByCourseCode")
    public ResponseEntity<List<Course>> getDistinctCoursesByCourseCode() {
        List<Course> distinctCourses = courseService.getDistinctCoursesByCourseCode();
        return ResponseEntity.ok(distinctCourses);
    }
    
    
    // null course id
    
    @PostMapping("/findCourseIdsWithNullProgram")
    public List<Integer> findCourseIdsWithNullProgram(@RequestBody Map<String, String> requestBody) {
        String courseCode = requestBody.get("courseCode");
        return courseService.findCourseIdsWithNullProgram(courseCode);
    }


}
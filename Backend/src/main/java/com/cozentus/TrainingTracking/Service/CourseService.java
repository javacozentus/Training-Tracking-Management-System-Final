package com.cozentus.TrainingTracking.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cozentus.TrainingTracking.DTO.CourseWithIdDTO;
import com.cozentus.TrainingTracking.Model.Course;
import com.cozentus.TrainingTracking.Repository.CourseRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;


@Service
public class CourseService {
	@Autowired
    private CourseRepository courseRepository;
//	@Autowired
//	private ProgramService programService;
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
    public Optional<Course> findCourseById(Integer id) {
        return courseRepository.findById(id);
    }
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }
    public Course updateCourse(Course course, Integer id) {
        course.setCourseId(id);
        return courseRepository.save(course);
    }
    public void deleteCourseById(Integer id) {
        courseRepository.deleteById(id);
    }
    public List<Course> getCoursesByProgramId(Integer programId) {
        return courseRepository.findByProgramId(programId);
    }
//  @Transactional
//  public Course updateCourseProgramId(Integer courseId) {
//      Integer latestProgramId = programService.findLatestProgramId();
//      courseRepository.updateCourseProgramId(courseId, latestProgramId);
//      return courseRepository.findById(courseId).orElse(null);
//  }
    @Transactional
    public Course updateCourseProgramId(Integer courseId, Integer latestProgramId) {
        courseRepository.updateCourseProgramId(courseId, latestProgramId);
        // Update the programId for the course entity
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            course.setProgramId(latestProgramId);
            return courseRepository.save(course);
        }
        return null; // Handle the case where the course is not found
    }
    @Transactional
    public void updateCoursesProgramId(Integer courseId, Integer newProgramId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
        course.setProgramId(newProgramId);
        courseRepository.save(course);
    }

    public List<String> getDistinctCourseNames() {
        return courseRepository.findDistinctCourseNames();
    }
    //
    public List<CourseWithIdDTO> getCourseDetailsByProgramId(Integer programId) {
        List<Course> courses = courseRepository.findByProgramId(programId);
        List<CourseWithIdDTO> courseDetails = courses.stream()
            .map(course -> new CourseWithIdDTO(course.getCourseId(), course))
            .collect(Collectors.toList());
        return courseDetails;
    }
    
    
    
    public List<Course> getCoursesWithNullProgramId() {
        return courseRepository.findByProgramIdIsNull();
    }
    
    
    
    public List<String> getDistinctCourseCodes() {
        return courseRepository.findDistinctCourseCodes();
    }
    
    public List<Course> findCoursesByCourseCode(String courseCode) {
        return courseRepository.findByCourseCode(courseCode);
    }
    
    public List<Course> getDistinctCoursesByCourseCode() {
        List<String> distinctCourseCodes = getDistinctCourseCodes();
        List<Course> distinctCourses = new ArrayList<>();
 
        for (String courseCode : distinctCourseCodes) {
            List<Course> courses = findCoursesByCourseCode(courseCode);
            if (!courses.isEmpty()) {
                distinctCourses.add(courses.get(0)); // Add the first course with the distinct course code
            }
        }
        return distinctCourses;
    }
    
    
    //NUll course_id
    
    @Transactional
    public List<Integer> findCourseIdsWithNullProgram(String courseCode) {
        List<Course> matchingCourses = courseRepository.findByCourseCode(courseCode);
        List<Integer> courseIdsWithNullProgram = new ArrayList<>();

        for (Course course : matchingCourses) {
            if (course.getProgramId() == null) {
                courseIdsWithNullProgram.add(course.getCourseId());
            }
        }

        return courseIdsWithNullProgram;
    }
    
    
    public List<Course> getCoursesByIds(List<Integer> courseIds) {
        return courseRepository.findAllById(courseIds);
    }



}
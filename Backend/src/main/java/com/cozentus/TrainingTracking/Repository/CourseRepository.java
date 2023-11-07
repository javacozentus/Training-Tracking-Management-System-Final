package com.cozentus.TrainingTracking.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cozentus.TrainingTracking.Model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{
	
	@Modifying
    @Query("UPDATE Course c SET c.programId = :programId WHERE c.courseId = :courseId")
    void updateCourseProgramId(@Param("courseId") Integer courseId, @Param("programId") Integer programId);

    List<Course> findByProgramId(Integer programId);
    
    @Query("SELECT DISTINCT c.courseName FROM Course c")
    List<String> findDistinctCourseNames();
    
    List<Course> findByProgramIdIsNull();
    
    @Query("SELECT DISTINCT c.courseCode FROM Course c")
    List<String> findDistinctCourseCodes();
    
    List<Course> findByCourseCode(String courseCode);
    
    
    Course findByCourseCodeAndProgramIdIsNull(String courseCode);



}

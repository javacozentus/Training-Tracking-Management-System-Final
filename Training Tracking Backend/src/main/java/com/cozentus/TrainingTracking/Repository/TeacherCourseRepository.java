package com.cozentus.TrainingTracking.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cozentus.TrainingTracking.Model.TeacherCourse;

public interface TeacherCourseRepository extends JpaRepository<TeacherCourse, Integer>{
	@Query("SELECT tc.courseId FROM TeacherCourse tc WHERE tc.teacherId = :teacherId")
    List<Integer> findCourseIdsByTeacherId(@Param("teacherId") Integer teacherId);
	// Query to find teacher IDs by course ID
    @Query("SELECT tc.teacherId FROM TeacherCourse tc WHERE tc.courseId = :courseId")
    List<Integer> findTeacherIdsByCourseId(@Param("courseId") Integer courseId);

    @Query("SELECT tc FROM TeacherCourse tc WHERE tc.courseId = :courseId AND tc.teacherId = :teacherId")
    TeacherCourse findByCourseIdAndTeacherId(@Param("courseId") Integer courseId, @Param("teacherId") Integer teacherId);

    @Query("SELECT tc.teacherCourseId FROM TeacherCourse tc WHERE tc.courseId = :courseId AND tc.teacherId = :teacherId")
    Integer findTeacherCourseIdByCourseAndTeacher(
        @Param("courseId") Integer courseId,
        @Param("teacherId") Integer teacherId
    );
    
    @Query("SELECT tc FROM TeacherCourse tc WHERE tc.teacherId = :teacherId")
    List<TeacherCourse> findAllByTeacherId(@Param("teacherId") Integer teacherId);
}

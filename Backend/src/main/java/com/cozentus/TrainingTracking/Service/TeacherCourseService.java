package com.cozentus.TrainingTracking.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cozentus.TrainingTracking.Model.Teacher;
import com.cozentus.TrainingTracking.Model.TeacherCourse;
import com.cozentus.TrainingTracking.Repository.TeacherCourseRepository;
import com.cozentus.TrainingTracking.Repository.TeacherRepository;

@Service
public class TeacherCourseService {
	@Autowired
	private TeacherCourseRepository teacherCourseRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	public void addTeacherCourses(Integer teacherId, List<Integer> courseIds) {
        for (Integer courseId : courseIds) {
            TeacherCourse teacherCourse = new TeacherCourse();
            teacherCourse.setTeacherId(teacherId);
            teacherCourse.setCourseId(courseId);
            teacherCourseRepository.save(teacherCourse);
        }
    }
	public List<Integer> getCourseIdsByTeacherId(Integer teacherId) {
        List<Integer> courseIds = teacherCourseRepository.findCourseIdsByTeacherId(teacherId);
        return courseIds;
    }
	public List<Teacher> getTeachersByCourseId(Integer courseId) {
	    List<Integer> teacherIds = teacherCourseRepository.findTeacherIdsByCourseId(courseId);
	    List<Teacher> teachers = new ArrayList<>();

	    for (Integer teacherId : teacherIds) {
	        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
	        if (teacher != null) {
	            teachers.add(teacher);
	        }
	    }

	    return teachers;
	}


	 public Integer getTeacherCourseId(Integer courseId, Integer teacherId) {
	        TeacherCourse teacherCourse = teacherCourseRepository.findByCourseIdAndTeacherId(courseId, teacherId);
	        if (teacherCourse != null) {
	            return teacherCourse.getTeacherCourseId();
	        }
	        return null;
	    }

	 public Integer findTeacherCourseIdByCourseAndTeacher(Integer courseId, Integer teacherId) {
	        return teacherCourseRepository.findTeacherCourseIdByCourseAndTeacher(courseId, teacherId);
	    }
	 
	 
	 public void updateTeacherCourses(Integer teacherId, List<Integer> updatedCourseIds) {
	        // First, remove the existing associations between the teacher and courses
	        List<TeacherCourse> existingAssociations = teacherCourseRepository.findAllByTeacherId(teacherId);
	        teacherCourseRepository.deleteAll(existingAssociations);

	        // Then, add new associations with the updated course IDs
	        for (Integer courseId : updatedCourseIds) {
	            TeacherCourse teacherCourse = new TeacherCourse();
	            teacherCourse.setTeacherId(teacherId);
	            teacherCourse.setCourseId(courseId);
	            teacherCourseRepository.save(teacherCourse);
	        }
	    }
}

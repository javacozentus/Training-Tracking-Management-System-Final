package com.cozentus.TrainingTracking.DTO;

import org.springframework.stereotype.Component;

@Component
public class TeacherCourseRequestDTO {
	
	private Integer course_id;
    private Integer teacher_id;
    
	public TeacherCourseRequestDTO() {}
 
	public TeacherCourseRequestDTO(Integer course_id, Integer teacher_id) {
		super();
		this.course_id = course_id;
		this.teacher_id = teacher_id;
	}
 
	public Integer getCourse_id() {
		return course_id;
	}
 
	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}
 
	public Integer getTeacher_id() {
		return teacher_id;
	}
 
	public void setTeacher_id(Integer teacher_id) {
		this.teacher_id = teacher_id;
	}
 
	@Override
	public String toString() {
		return "TeacherCourseRequestDTO [course_id=" + course_id + ", teacher_id=" + teacher_id + "]";
	}  
}
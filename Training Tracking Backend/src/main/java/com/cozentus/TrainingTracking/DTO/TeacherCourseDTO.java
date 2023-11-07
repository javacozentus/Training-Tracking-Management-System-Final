package com.cozentus.TrainingTracking.DTO;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cozentus.TrainingTracking.Model.Teacher;

@Component
public class TeacherCourseDTO {
	
	private Teacher teacher;
    private List<Integer> courseIds;
    private List<String> courseNames;
    
	public TeacherCourseDTO() {
		
	}

	public TeacherCourseDTO(Teacher teacher, List<Integer> courseIds, List<String> courseNames) {
		super();
		this.teacher = teacher;
		this.courseIds = courseIds;
		this.courseNames = courseNames;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public List<Integer> getCourseIds() {
		return courseIds;
	}

	public void setCourseIds(List<Integer> courseIds) {
		this.courseIds = courseIds;
	}

	public List<String> getCourseNames() {
		return courseNames;
	}

	public void setCourseNames(List<String> courseNames) {
		this.courseNames = courseNames;
	}

	@Override
	public String toString() {
		return "TeacherCourseDTO [teacher=" + teacher + ", courseIds=" + courseIds + ", courseNames=" + courseNames
				+ ", getTeacher()=" + getTeacher() + ", getCourseIds()=" + getCourseIds() + ", getCourseNames()="
				+ getCourseNames() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
    
    
    
   
}

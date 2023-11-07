package com.cozentus.TrainingTracking.DTO;

import com.cozentus.TrainingTracking.Model.Course;

public class CourseWithIdDTO {
	
	 private Integer courseId;
	  private Course courseDetails;
	  
	public CourseWithIdDTO() {
		
	}

	public CourseWithIdDTO(Integer courseId, Course courseDetails) {
		super();
		this.courseId = courseId;
		this.courseDetails = courseDetails;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Course getCourseDetails() {
		return courseDetails;
	}

	public void setCourseDetails(Course courseDetails) {
		this.courseDetails = courseDetails;
	}

	@Override
	public String toString() {
		return "CourseWithIdDTO [courseId=" + courseId + ", courseDetails=" + courseDetails + "]";
	}
	
	
	  
	  

}

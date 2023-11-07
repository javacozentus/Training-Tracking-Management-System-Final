package com.cozentus.TrainingTracking.DTO;

import java.util.List;

import com.cozentus.TrainingTracking.Model.Course;
import com.cozentus.TrainingTracking.Model.Topic;

public class ProgramDataDTO {
	
	private List<Course> courses;
    private Double courseCompletion;
    private List<Topic> completedTopics;
    private List<Topic> incompletedTopics;
	
    public ProgramDataDTO() {}
 
	public ProgramDataDTO(List<Course> courses, Double courseCompletion, List<Topic> completedTopics,
			List<Topic> incompletedTopics) {
		super();
		this.courses = courses;
		this.courseCompletion = courseCompletion;
		this.completedTopics = completedTopics;
		this.incompletedTopics = incompletedTopics;
	}
 
	public List<Course> getCourses() {
		return courses;
	}
 
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
 
	public Double getCourseCompletion() {
		return courseCompletion;
	}
 
	public void setCourseCompletion(Double courseCompletion) {
		this.courseCompletion = courseCompletion;
	}
 
	public List<Topic> getCompletedTopics() {
		return completedTopics;
	}
 
	public void setCompletedTopics(List<Topic> completedTopics) {
		this.completedTopics = completedTopics;
	}
 
	public List<Topic> getIncompletedTopics() {
		return incompletedTopics;
	}
 
	public void setIncompletedTopics(List<Topic> incompletedTopics) {
		this.incompletedTopics = incompletedTopics;
	}

	@Override
	public String toString() {
		return "ProgramDataDTO [courses=" + courses + ", courseCompletion=" + courseCompletion + ", completedTopics="
				+ completedTopics + ", incompletedTopics=" + incompletedTopics + "]";
	}
 
	

}

package com.cozentus.TrainingTracking.DTO;

import java.util.List;

import com.cozentus.TrainingTracking.Model.Course;
import com.cozentus.TrainingTracking.Model.Topic;

public class CourseWithTopics {
	
	private Course course;
    private List<Topic> completeTopics;
    private List<Topic> incompleteTopics;
    private double averageTotalPercentage;
	
    public CourseWithTopics() {}
    
	public CourseWithTopics(Course course, List<Topic> completeTopics, List<Topic> incompleteTopics,
			double averageTotalPercentage) {
		super();
		this.course = course;
		this.completeTopics = completeTopics;
		this.incompleteTopics = incompleteTopics;
		this.averageTotalPercentage = averageTotalPercentage;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Topic> getCompleteTopics() {
		return completeTopics;
	}

	public void setCompleteTopics(List<Topic> completeTopics) {
		this.completeTopics = completeTopics;
	}

	public List<Topic> getIncompleteTopics() {
		return incompleteTopics;
	}

	public void setIncompleteTopics(List<Topic> incompleteTopics) {
		this.incompleteTopics = incompleteTopics;
	}

	public double getAverageTotalPercentage() {
		return averageTotalPercentage;
	}

	public void setAverageTotalPercentage(double averageTotalPercentage) {
		this.averageTotalPercentage = averageTotalPercentage;
	}

	@Override
	public String toString() {
		return "CourseWithTopics [course=" + course + ", completeTopics=" + completeTopics + ", incompleteTopics="
				+ incompleteTopics + ", averageTotalPercentage=" + averageTotalPercentage + "]";
	}
}

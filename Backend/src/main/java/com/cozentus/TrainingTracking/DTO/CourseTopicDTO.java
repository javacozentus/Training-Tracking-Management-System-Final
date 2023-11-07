package com.cozentus.TrainingTracking.DTO;

import java.util.List;
import java.util.Map;

import com.cozentus.TrainingTracking.Model.Course;
import com.cozentus.TrainingTracking.Model.Topic;

public class CourseTopicDTO {
	
	 private Course course;
	    private Map<String, List<Topic>> topicsWithStatus;
	    
	    
		public CourseTopicDTO() {
			super();
			// TODO Auto-generated constructor stub
		}


		public CourseTopicDTO(Course course, Map<String, List<Topic>> topicsWithStatus) {
			super();
			this.course = course;
			this.topicsWithStatus = topicsWithStatus;
		}


		public Course getCourse() {
			return course;
		}


		public void setCourse(Course course) {
			this.course = course;
		}


		public Map<String, List<Topic>> getTopicsWithStatus() {
			return topicsWithStatus;
		}


		public void setTopicsWithStatus(Map<String, List<Topic>> topicsWithStatus) {
			this.topicsWithStatus = topicsWithStatus;
		}


		@Override
		public String toString() {
			return "CourseTopicDTO [course=" + course + ", topicsWithStatus=" + topicsWithStatus + "]";
		}
	    
	    

}

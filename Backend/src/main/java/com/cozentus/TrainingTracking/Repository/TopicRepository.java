package com.cozentus.TrainingTracking.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cozentus.TrainingTracking.Model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Integer>{
	
	List<Topic> findByCourseId(int courseId);
	
	@Modifying
	@Query("UPDATE Topic SET topicPercentageCompleted=:topicPercentageCompleted where topicId=:topicId")
	void updateTopicPercentageCompleted(Integer topicPercentageCompleted, Integer topicId);
	
	@Query("SELECT t FROM Topic t WHERE t.topicPercentageCompleted IS NOT NULL")
    List<Topic> findTopicsWithPercentageCompletedNotNull();
	
	@Query("FROM Topic where courseId=:courseId AND topicPercentageCompleted=:number")
	List<Topic> findByCourseIdAndCompletedTopics(@Param("courseId") Integer courseId, @Param("number") Integer number);
 
	@Query("FROM Topic where courseId=:courseId AND topicPercentageCompleted!=:number")
	List<Topic> findByCourseIdAndInprogressTopics(@Param("courseId") Integer courseId, @Param("number") Integer number);
	
	
	    List<Topic> findByCourseIdIn(List<Integer> courseIds);
	    
	   
}

package com.cozentus.TrainingTracking.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cozentus.TrainingTracking.Model.Topic;
import com.cozentus.TrainingTracking.Repository.TopicRepository;

@Service
public class TopicService {
	
	@Autowired
	private TopicRepository topicRepository;
	
	
	public Optional<Topic> getTopicById(Integer topicId) {
		return topicRepository.findById(topicId);
	}
	
	public List<Topic> getAllTopics() {
		return topicRepository.findAll();
	}
	
	public Topic addTopic(Topic topic) {
		return topicRepository.save(topic);
	}
	
	public Topic updateTopic(Topic topic, Integer id) {
		topic.setTopicId(id);
		return topicRepository.save(topic);
	}
	
	public void deleteTopic(Integer id) {
		topicRepository.deleteById(id);
	}
	
	public List<Topic> getTopicByCourseId(Integer id) {
		return topicRepository.findByCourseId(id);
	}
	
	public void updateTopicPercentageCompleted(int topicPercentage, int topicId) {
		topicRepository.updateTopicPercentageCompleted(topicPercentage, topicId);
	}
	
	
	// add topic percentage
	
//	public Topic addTopicPercentage(int topicId, int topicPercentage) throws Exception {
//        Optional<Topic> optionalTopic = topicRepository.findById(topicId);
// 
//        if (optionalTopic.isPresent()) {
//            Topic topic = optionalTopic.get();
//            topic.setTopicPercentageCompleted(topicPercentage);
//            return topicRepository.save(topic);
//        } else {
//            throw new Exception("Topic not found with id: " + topicId);
//        }
//    }
	
	
	public Topic addTopicPercentage(int topicId, int topicPercentage) throws Exception {
	    Optional<Topic> optionalTopic = topicRepository.findById(topicId);

	    if (optionalTopic.isPresent()) {
	        Topic topic = optionalTopic.get();
	        topic.setTopicPercentageCompleted(topicPercentage);
	        return topicRepository.save(topic);
	    } else {
	        throw new Exception("Topic not found with id: " + topicId);
	    }
	}
	
	
	/// topic where percentage is not null
	
	public List<Topic> getTopicsWithPercentageCompletedNotNull() {
	    return topicRepository.findTopicsWithPercentageCompletedNotNull();
	}

	public Double getCourseCompletion(Integer courseId) {
		List<Topic> topics = topicRepository.findByCourseId(courseId);
		Double percent = 0.0;
		for(Topic topic: topics) {
			percent = percent + topic.getTopicPercentageCompleted();
		}
		Double courseCompletion = percent / topics.size();
		return courseCompletion;
	}
	
	public List<Topic> getCompletedTopics(Integer courseId) {
		return topicRepository.findByCourseIdAndCompletedTopics(courseId, 100);
	}
	
	public List<Topic> getInCompletedTopics(Integer courseId) {
		return topicRepository.findByCourseIdAndInprogressTopics(courseId, 100);
	}
	
	// Define a method to get topics by a list of course IDs
    public List<Topic> getTopicsByCourseIds(List<Integer> courseIds) {
        // Use the JpaRepository to find topics by course IDs
        return topicRepository.findByCourseIdIn(courseIds);
    }

}
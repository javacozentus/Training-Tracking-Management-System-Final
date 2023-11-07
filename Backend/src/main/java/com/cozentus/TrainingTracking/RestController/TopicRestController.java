package com.cozentus.TrainingTracking.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cozentus.TrainingTracking.Model.Topic;
import com.cozentus.TrainingTracking.Service.TopicService;
import com.cozentus.TrainingTracking.Utility.FileUtility;

@RestController
@RequestMapping("/topic")
@CrossOrigin(origins = "*",allowedHeaders ="*") 
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class TopicRestController {
	
	@Autowired
	private TopicService topicService;
	
	@Autowired
	private FileUtility fileUtility;
	
	@GetMapping("/show/all")
	public ResponseEntity<List<Topic>> getAllTopics() {
		return ResponseEntity.ok(topicService.getAllTopics());
	}
 
	@GetMapping("/show/{id}")
	public ResponseEntity<Optional<Topic>> getTopicById(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(topicService.getTopicById(id));
	}
 
	@PostMapping("/add")
	public ResponseEntity<Topic> addTopic(@RequestBody Topic course) {
		return ResponseEntity.ok(topicService.addTopic(course));
	}
 
	@PostMapping("/update/{id}")
	public ResponseEntity<Topic> updateTopic(@RequestBody Topic course, @PathVariable("id") Integer id) {
		return ResponseEntity.ok(topicService.updateTopic(course, id));
	}
 
	@PostMapping("/delete/{id}")
	public ResponseEntity<Void> deleteTopicById(@PathVariable("id") Integer id) {
		topicService.deleteTopic(id);
		return ResponseEntity.ok().build();
	}
 
	@GetMapping("/show/course/{id}")
	public ResponseEntity<List<Topic>> getTopicByCourseId(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(topicService.getTopicByCourseId(id));
	}
	/*
	 * @PostMapping("/uploadfile") public ResponseEntity<String>
	 * fileUpload(@RequestParam("file") MultipartFile file) {
	 * System.out.println(file.getContentType());
	 * System.out.println(file.getName());
	 * System.out.println(file.getOriginalFilename()); if (file.isEmpty()) { return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
	 * body("file is empty !!"); } if (fileUtility.uploadFile(file)) { return
	 * ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path(
	 * "/fileUpload/") .path(file.getOriginalFilename()).toUriString()); } return
	 * ResponseEntity.status(HttpStatus.NOT_FOUND).build(); }
	 */
	
	@PostMapping("/uploadfile")
	public ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile file) {
	    if (file.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File is empty !!");
	    }

	    if (fileUtility.uploadFile(file)) {
	        return ResponseEntity.ok("File uploaded successfully");
	    }

	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload the file");
	}

	//ADd percentage to topic
	
//	@PostMapping("/addTopicPercentage/{topicId}")
//    public ResponseEntity<Topic> addTopicPercentage(
//        @PathVariable int topicId,
//        @RequestBody int topicPercentage
//    ) {
//        Topic updatedTopic = null;
//        try {
//            updatedTopic = topicService.addTopicPercentage(topicId, topicPercentage);
//        } catch (Exception e) {
//            // Handle the exception as needed
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//        return ResponseEntity.ok(updatedTopic);
//    }
	
	
	@PostMapping("/addTopicPercentage/{topicId}")
	public ResponseEntity<Topic> addTopicPercentage(
	    @PathVariable int topicId,
	    @RequestBody Map<String, Integer> request
	) {
	    int topicPercentage = request.get("topicPercentage");

	    Topic updatedTopic = null;
	    try {
	        updatedTopic = topicService.addTopicPercentage(topicId, topicPercentage);
	    } catch (Exception e) {
	        // Handle the exception as needed
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	    return ResponseEntity.ok(updatedTopic);
	}
	
	
	
	// topic where per is not null
	
	@GetMapping("/percentage-not-null")
	public ResponseEntity<List<Topic>> getTopicsWithPercentageCompletedNotNull() {
	    List<Topic> topics = topicService.getTopicsWithPercentageCompletedNotNull();
	    return ResponseEntity.ok(topics);
	}
	
	@GetMapping("/courseCompletion/{courseId}")
    public ResponseEntity<Double> getCourseCompletion(@PathVariable Integer courseId) {
        Double courseCompletion = topicService.getCourseCompletion(courseId);
        return ResponseEntity.ok(courseCompletion);
    }
 
    @GetMapping("/completedTopics/{courseId}")
    public ResponseEntity<List<Topic>> getCompletedTopics(@PathVariable Integer courseId) {
        List<Topic> completedTopics = topicService.getCompletedTopics(courseId);
        return ResponseEntity.ok(completedTopics);
    }
 
    @GetMapping("/incompletedTopics/{courseId}")
    public ResponseEntity<List<Topic>> getIncompletedTopics(@PathVariable Integer courseId) {
        List<Topic> incompletedTopics = topicService.getInCompletedTopics(courseId);
        return ResponseEntity.ok(incompletedTopics);
    }

}

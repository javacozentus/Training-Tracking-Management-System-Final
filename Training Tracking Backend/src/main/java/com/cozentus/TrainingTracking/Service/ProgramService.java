package com.cozentus.TrainingTracking.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cozentus.TrainingTracking.DTO.CourseTopicDTO;
import com.cozentus.TrainingTracking.DTO.ProgramCourseDTO;
import com.cozentus.TrainingTracking.Model.Course;
import com.cozentus.TrainingTracking.Model.Program;
import com.cozentus.TrainingTracking.Model.Topic;
import com.cozentus.TrainingTracking.Repository.CourseRepository;
import com.cozentus.TrainingTracking.Repository.ProgramRepository;
import com.cozentus.TrainingTracking.Repository.TopicRepository;

@Service
public class ProgramService {
	
//	@Autowired
//	    private ProgramRepository programRepository;
//		
//		 @Autowired
//		    private CourseService courseService;
//		
//	 
//	    public List<Program> getAllPrograms() {
//	        return programRepository.findAll();
//	    }
//	 
//	    public Optional<Program> findProgramById(Integer id) {
//	        return programRepository.findById(id);
//	    }
//	 
//	    public Program addProgram(Program program) {
//	        return programRepository.save(program);
//	    }
//	 
//	    public Program updateProgram(Program program, Integer id) {
//	        program.setProgramId(id);
//	        return programRepository.save(program);
//	    }
//	 
//	    public void deleteProgramById(Integer id) {
//	        programRepository.deleteById(id);
//	    }
//	    
//	    // Your existing methods
//	 
//	    public Integer findLatestProgramId() {
//	        return programRepository.findMaxProgramId();
//	    }
//	    
//	    
//	    public Program addProgramAndUpdateCourse(Program program, Integer courseId) {
//	        // Retrieve the latest programId
//	        Integer latestProgramId = findLatestProgramId();
//	 
//	        // Increment the program ID to get the next one
//	        program.setProgramId(latestProgramId + 1);
//	 
//	        // Save the program
//	        Program savedProgram = programRepository.save(program);
//	 
//	        // Update the Course with the new program.id
//	        courseService.updateCourseProgramId(courseId, savedProgram.getProgramId());
//	 
//	        return savedProgram;
//	    }
//	    
	    
	@Autowired
    private ProgramRepository programRepository;
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private TopicService topicService;
	
	@Autowired
	private TopicRepository topicRepository;
	
	 @Autowired
	    private CourseRepository courseRepository; // Assuming you have a CourseRepository

	
	
    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }
    public Optional<Program> findProgramById(Integer id) {
        return programRepository.findById(id);
    }
    public Program addProgram(Program program) {
        return programRepository.save(program);
    }
    public Program updateProgram(Program program, Integer id) {
        program.setProgramId(id);
        return programRepository.save(program);
    }
    public void deleteProgramById(Integer id) {
        programRepository.deleteById(id);
    }
    public Integer findLatestProgramId() {
        return programRepository.findMaxProgramId();
    }
    @Transactional
    public Program addProgramAndCourses(ProgramCourseDTO programCourseDTO) {
        Program program = programCourseDTO.getProgram();
        List<Course> courses = programCourseDTO.getCourses();
 
        // Save the program
        Program savedProgram = programRepository.save(program);
 
        // Associate courses with the new program
        for (Course course : courses) {
            course.setProgramId(savedProgram.getProgramId());
            courseService.addCourse(course);
        }
        return savedProgram;
    }
 
    
//    @Transactional
//    public Program updateProgramAndCourses(ProgramCourseDTO programCourseDTO) {
//        Program program = programCourseDTO.getProgram();
//        List<Integer> courseIds = programCourseDTO.getCourseIds();
//        // Update program details
//        Program existingProgram = programRepository.findById(program.getProgramId())
//                .orElseThrow(() -> new EntityNotFoundException("Program not found"));
//        // Update program details as needed
//        existingProgram.setProgramName(program.getProgramName());
//        // Add more fields as needed
//        Program updatedProgram = programRepository.save(existingProgram);
//        // Update associated courses' program IDs
//        for (Integer courseId : courseIds) {
//            courseService.updateCoursesProgramId(courseId, updatedProgram.getProgramId());
//        }
//        return updatedProgram;
//    }
	  
    //new topic 
    
    public List<Program> getProgramsByProgramIds(List<Integer> programIds) {
        return programRepository.findProgramsByProgramIds(programIds);
    }
    
//    
//    public ProgramDataDTO getProgramData(Integer programId) {
//        ProgramDataDTO programData = new ProgramDataDTO();
//        List<Course> courses = courseService.getCoursesByProgramId(programId);
//        programData.setCourses(courses);
// 
//        // Calculate course completion
//        Double courseCompletion = topicService.getCourseCompletion(programId);
//        programData.setCourseCompletion(courseCompletion);
// 
//        // Get completed topics
//        List<Topic> completedTopics = topicService.getCompletedTopics(programId);
//        programData.setCompletedTopics(completedTopics);
// 
//        // Get incompleted topics
//        List<Topic> incompletedTopics = topicService.getInCompletedTopics(programId);
//        programData.setIncompletedTopics(incompletedTopics);
//        return programData;
//    }

//    public ProgramDataDTO getProgramData(Integer programId, String programCode) {
//        ProgramDataDTO programData = new ProgramDataDTO();
// 
//        // Get course IDs with null program from CourseService
//        List<Integer> courseIdsWithNullProgram = courseService.findCourseIdsWithNullProgram(programCode);
// 
//        // Fetch course data for the specified program
//        List<Course> courses = courseService.getCoursesByProgramId(programId);
// 
//        // Fetch courses with null program using courseIdsWithNullProgram
//        List<Course> coursesWithNullProgram = courseService.getCoursesByIds(courseIdsWithNullProgram);
//        courses.addAll(coursesWithNullProgram);
// 
//        programData.setCourses(courses);
// 
//        // Calculate course completion
//        Double courseCompletion = topicService.getCourseCompletion(programId);
//        programData.setCourseCompletion(courseCompletion);
// 
//        // Get completed topics
//        List<Topic> completedTopics = topicService.getCompletedTopics(programId);
//        programData.setCompletedTopics(completedTopics);
// 
//        // Get incompleted topics
//        List<Topic> incompletedTopics = topicService.getInCompletedTopics(programId);
//        programData.setIncompletedTopics(incompletedTopics);
// 
//        return programData;
//    }
    
//    public ProgramDataDTO getProgramData(Integer programId) {
//        ProgramDataDTO programData = new ProgramDataDTO();
//
//        // Get courses based on the program_id
//        List<Course> courses = courseService.getCoursesByProgramId(programId);
//        programData.setCourses(courses);
//
//        // Calculate course completion
//        Double courseCompletion = topicService.getCourseCompletion(programId);
//        programData.setCourseCompletion(courseCompletion);
//
//        // Get completed topics
//        List<Topic> completedTopics = topicService.getCompletedTopics(programId);
//        programData.setCompletedTopics(completedTopics);
//
//        // Get incompleted topics
//        List<Topic> incompletedTopics = topicService.getInCompletedTopics(programId);
//        programData.setIncompletedTopics(incompletedTopics);
//
//        // Retrieve course_id for courses where program_id is NULL
//        List<Integer> courseIdsToRetrieve = new ArrayList<>();
//        for (Course course : courses) {
//            if (course.getProgramId() == null) {
//                // If program_id is NULL, retrieve course_id
//                courseIdsToRetrieve.add(course.getCourseId());
//            }
//        }
//
//        // Pass courseIdsToRetrieve to topicService to retrieve relevant topics
//        List<Topic> additionalTopics = topicService.getTopicsByCourseIds(courseIdsToRetrieve);
//        // Merge the additionalTopics with incompletedTopics
//        incompletedTopics.addAll(additionalTopics);
//
//        return programData;
//    }
    
    
  
//    public List<Topic> getTopicsByProgramId(Integer programId) {
//        // Step 1: Retrieve courses based on the program_id
//        List<Course> courses = courseRepository.findByProgramId(programId);
//
//        List<Integer> courseIds = new ArrayList<>();
//
//        // Step 2: Get course_code from the retrieved courses and find course_id
//        for (Course course : courses) {
//            String courseCode = course.getCourseCode();
//            // Step 3: Find course_id where program_id is NULL
//            Course nullProgramCourse = courseRepository.findByCourseCodeAndProgramIdIsNull(courseCode);
//            if (nullProgramCourse != null) {
//                courseIds.add(nullProgramCourse.getCourseId());
//            }
//        }
//
//        // Step 4: Get topics for the retrieved course_ids
//        List<Topic> topics = topicRepository.findByCourseIdIn(courseIds);
//
//        return topics;
//    }
    
    
    //-------------------------------------------------------//
//    public List<CourseWithTopics> getCoursesWithTopicsByProgramId(Integer programId) {
//        List<Course> courses = courseRepository.findByProgramId(programId);
//
//        List<CourseWithTopics> coursesWithTopics = new ArrayList<>();
//        
//        for (Course course : courses) {
//            CourseWithTopics courseWithTopics = new CourseWithTopics();
//            courseWithTopics.setCourse(course);
//            
//            List<Topic> allTopics = topicRepository.findByCourseId(course.getCourseId());
//
//            // Filter topics into complete and incomplete based on topicPercentageCompleted
//            List<Topic> completeTopics = allTopics.stream()
//                .filter(topic -> topic.getTopicPercentageCompleted() == 100)
//                .collect(Collectors.toList());
//
//            List<Topic> incompleteTopics = allTopics.stream()
//                .filter(topic -> topic.getTopicPercentageCompleted() < 100)
//                .collect(Collectors.toList());
//
//            courseWithTopics.setCompleteTopics(completeTopics);
//            courseWithTopics.setIncompleteTopics(incompleteTopics);
//
//            // Calculate average total percentage for all topics
//            double averageTotalPercentage = allTopics.stream()
//                .mapToDouble(Topic::getTopicPercentageCompleted)
//                .average()
//                .orElse(0.0);
//
//            courseWithTopics.setAverageTotalPercentage(averageTotalPercentage);
//
//            coursesWithTopics.add(courseWithTopics);
//        }
//
//        return coursesWithTopics;
//    } 
//    
//    public Map<Course, Map<String, List<Topic>>> getTopicsWithStatusAndAveragePercentage(Integer programId) {
//        List<Course> courses = courseRepository.findByProgramId(programId);
//
//        Map<Course, Map<String, List<Topic>>> courseTopicsMap = new HashMap<>();
//
//        for (Course course : courses) {
//            if (course.getProgramId() != null) {
//                String courseCode = course.getCourseCode();
//                Integer courseId = course.getCourseId();
//
//                Course nullProgramCourse = courseRepository.findByCourseCodeAndProgramIdIsNull(courseCode);
//
//                if (nullProgramCourse != null) {
//                    Integer nullProgramCourseId = nullProgramCourse.getCourseId();
//
//                    List<Topic> topics = topicRepository.findByCourseId(nullProgramCourseId);
//                    courseTopicsMap.put(course, filterTopicsByStatus(topics));
//                }
//            }
//        }
//
//        return courseTopicsMap;
//    }
//
//    private Map<String, List<Topic>> filterTopicsByStatus(List<Topic> topics) {
//        Map<String, List<Topic>> topicsWithStatus = new HashMap<>();
//
//        List<Topic> completeTopics = new ArrayList<>();
//        List<Topic> incompleteTopics = new ArrayList<>();
//
//        for (Topic topic : topics) {
//            if (topic.getTopicPercentageCompleted() == 100) {
//                completeTopics.add(topic);
//            } else {
//                incompleteTopics.add(topic);
//            }
//        }
//
//        topicsWithStatus.put("completeTopics", completeTopics);
//        topicsWithStatus.put("incompleteTopics", incompleteTopics);
//
//        return topicsWithStatus;
//    }
  //------------------------------------------------FInal-------//
    
//    public Map<Course, Map<String, List<Topic>>> getTopicsWithStatusAndAveragePercentage(Integer programId) {
//        List<Course> courses = courseRepository.findByProgramId(programId);
//
//        Map<Course, Map<String, List<Topic>>> courseTopicsMap = new HashMap<>();
//
//        for (Course course : courses) {
//            if (course.getProgramId() != null) {
//                String courseCode = course.getCourseCode();
//                Integer courseId = course.getCourseId();
//
//                Course nullProgramCourse = courseRepository.findByCourseCodeAndProgramIdIsNull(courseCode);
//
//                if (nullProgramCourse != null) {
//                    Integer nullProgramCourseId = nullProgramCourse.getCourseId();
//
//                    List<Topic> topics = topicRepository.findByCourseId(nullProgramCourseId);
//                    Map<String, List<Topic>> topicsWithStatus = filterTopicsByStatus(topics);
//
//                    double courseCompletionPercentage = calculateCourseCompletionPercentage(topics);
//                    topicsWithStatus.put("courseCompletionPercentage", createCourseCompletionPercentage(courseCompletionPercentage));
//                    
//                    courseTopicsMap.put(course, topicsWithStatus);
//                }
//            }
//        }
//
//        return courseTopicsMap;
//    }
//    
//    private Map<String, List<Topic>> filterTopicsByStatus(List<Topic> topics) {
//      Map<String, List<Topic>> topicsWithStatus = new HashMap<>();
//
//      List<Topic> completeTopics = new ArrayList<>();
//      List<Topic> incompleteTopics = new ArrayList<>();
//
//      for (Topic topic : topics) {
//          if (topic.getTopicPercentageCompleted() == 100) {
//              completeTopics.add(topic);
//          } else {
//              incompleteTopics.add(topic);
//          }
//      }
//
//      topicsWithStatus.put("completeTopics", completeTopics);
//      topicsWithStatus.put("incompleteTopics", incompleteTopics);
//
//      return topicsWithStatus;
//  }
//    private double calculateCourseCompletionPercentage(List<Topic> topics) {
//        if (topics.isEmpty()) {
//            return 0.0;
//        }
//
//        double totalPercentage = topics.stream().mapToDouble(Topic::getTopicPercentageCompleted).sum();
//        return totalPercentage / topics.size();
//    }
//
//    private List<Topic> createCourseCompletionPercentage(double courseCompletionPercentage) {
//        List<Topic> courseCompletionPercentageList = new ArrayList<>();
//        Topic topic = new Topic();
//        topic.setTopicPercentageCompleted((int) Math.round(courseCompletionPercentage)); // Convert double to Integer
//        courseCompletionPercentageList.add(topic);
//        return courseCompletionPercentageList;
//    }
//    
    //------------------------------------------------FInal-------//
    
    
  
    public List<CourseTopicDTO> getTopicsWithStatusAndAveragePercentage(Integer programId) {
        List<Course> courses = courseRepository.findByProgramId(programId);
        List<CourseTopicDTO> courseTopicDTOList = new ArrayList<>();

        for (Course course : courses) {
            if (course.getProgramId() != null) {
                String courseCode = course.getCourseCode();
                Integer courseId = course.getCourseId();

                Course nullProgramCourse = courseRepository.findByCourseCodeAndProgramIdIsNull(courseCode);

                if (nullProgramCourse != null) {
                    Integer nullProgramCourseId = nullProgramCourse.getCourseId();

                    List<Topic> topics = topicRepository.findByCourseId(nullProgramCourseId);
                    Map<String, List<Topic>> topicsWithStatus = filterTopicsByStatus(topics);

                    double courseCompletionPercentage = calculateCourseCompletionPercentage(topics);
                    topicsWithStatus.put("courseCompletionPercentage", createCourseCompletionPercentage(courseCompletionPercentage));

                    CourseTopicDTO courseTopicDTO = new CourseTopicDTO(course, topicsWithStatus);
                    courseTopicDTOList.add(courseTopicDTO);
                }
            }
        }

        return courseTopicDTOList;
    }

    private Map<String, List<Topic>> filterTopicsByStatus(List<Topic> topics) {
        Map<String, List<Topic>> topicsWithStatus = new HashMap<>();

        List<Topic> completeTopics = new ArrayList<>();
        List<Topic> incompleteTopics = new ArrayList<>();

        for (Topic topic : topics) {
            if (topic.getTopicPercentageCompleted() == 100) {
                completeTopics.add(topic);
            } else {
                incompleteTopics.add(topic);
            }
        }

        topicsWithStatus.put("completeTopics", completeTopics);
        topicsWithStatus.put("incompleteTopics", incompleteTopics);

        return topicsWithStatus;
    }

    private double calculateCourseCompletionPercentage(List<Topic> topics) {
        if (topics.isEmpty()) {
            return 0.0;
        }

        double totalPercentage = topics.stream().mapToDouble(Topic::getTopicPercentageCompleted).sum();
        return totalPercentage / topics.size();
    }

    private List<Topic> createCourseCompletionPercentage(double courseCompletionPercentage) {
        List<Topic> courseCompletionPercentageList = new ArrayList<>();
        Topic topic = new Topic();
        topic.setTopicPercentageCompleted((int) Math.round(courseCompletionPercentage)); // Convert double to Integer
        courseCompletionPercentageList.add(topic);
        return courseCompletionPercentageList;
    }


}
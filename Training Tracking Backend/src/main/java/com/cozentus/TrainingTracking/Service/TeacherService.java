package com.cozentus.TrainingTracking.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cozentus.TrainingTracking.DTO.TeacherCourseDTO;
import com.cozentus.TrainingTracking.Model.Course;
import com.cozentus.TrainingTracking.Model.Credential;
import com.cozentus.TrainingTracking.Model.Teacher;
import com.cozentus.TrainingTracking.Repository.CredentialRepository;
import com.cozentus.TrainingTracking.Repository.TeacherRepository;

@Service
public class TeacherService {
	
	@Autowired
    private TeacherCourseService teacherCourseService;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private CredentialRepository credentialRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<Teacher> getAllTeachers () {
		return teacherRepository.findAll();
	}
	public Optional<Teacher> getTeacherById(Integer id) {
		return teacherRepository.findById(id);
	}
	
	public String getRole(String username) {
        return credentialRepository.findUserRole(username);
    }
	
//	public Teacher addTeacherWithCourses(Teacher teacher, List<Integer> courseIds) {
//        // Save the teacher to get the generated teacher_id
//        teacher = teacherRepository.save(teacher);
//        // Generate a random password for the teacher (you can implement your logic)
//        String password = generateRandomPassword();
//        
//        // Encode the password
//        String encodedPassword = passwordEncoder.encode(password);
//        
//        // Store the encoded password in the database
//        teacher.setEncodedPassword(encodedPassword);
//        
//        // Associate the teacher with multiple courses
//        teacherCourseService.addTeacherCourses(teacher.getTeacherId(), courseIds);
//        // Send the welcome email
//        emailService.sendTeacherWelcomeEmail(teacher.getTeacherEmail(), password);
//        // Create a Credential entry for the teacher
//        Credential credential = new Credential();
//        credential.setUserId(teacher.getTeacherEmail());
//        credential.setPassword(password);
//        credential.setRole("ROLE_TEACHER");
//        credentialRepository.save(credential);
//        return teacher;
//    }
	
	public Teacher addTeacherWithCourses(Teacher teacher, List<Integer> courseIds) {
	    // Save the teacher to get the generated teacher_id
	    teacher = teacherRepository.save(teacher);
	    
	    // Generate a random password for the teacher (you can implement your logic)
	    String password = generateRandomPassword();
	    
	    // Encode the password using BCrypt
	    String encodedPassword = passwordEncoder.encode(password);
	    
	    // Store the encoded password in the teacher object
	    teacher.setEncodedPassword(encodedPassword);
	    
	    // Associate the teacher with multiple courses
	    teacherCourseService.addTeacherCourses(teacher.getTeacherId(), courseIds);
	    
	    // Send the welcome email
	    emailService.sendTeacherWelcomeEmail(teacher.getTeacherEmail(), password);
	    
	    // Create a Credential entry for the teacher with the encoded password
	    Credential credential = new Credential();
	    credential.setUserId(teacher.getTeacherEmail());
	    credential.setPassword(encodedPassword); // Store the encoded password
	    credential.setRole("ROLE_TEACHER");
	    credentialRepository.save(credential);
	    
	    return teacher;
	}

	
	private String generateRandomPassword() {
	    int passwordLength = 8;
	    return RandomStringUtils.random(passwordLength, true, true);
	}
	public Teacher updateTeacher(Teacher teacher, Integer id) {
		teacher.setTeacherId(id);
		return teacherRepository.save(teacher);
	}
	public void deleteTeacherById (Integer id) {
		teacherRepository.deleteById(id);
	}
//	public List<Teacher> getTeachersByCourseId(Integer courseId) {
//		return teacherRepository.findByCourseId(courseId);
//	}	
	public List<TeacherCourseDTO> getAllTeachersWithCoursesAndCourseNames() {
        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherCourseDTO> teachersWithCourses = new ArrayList<>();
 
        for (Teacher teacher : teachers) {
            List<Integer> courseIds = teacherCourseService.getCourseIdsByTeacherId(teacher.getTeacherId());
            List<String> courseNames = new ArrayList<>();
 
            for (Integer courseId : courseIds) {
                Optional<Course> course = courseService.findCourseById(courseId);
                course.ifPresent(c -> courseNames.add(c.getCourseName()));
            }
 
            TeacherCourseDTO teacherCourseDTO = new TeacherCourseDTO(teacher, courseIds, courseNames);
            teachersWithCourses.add(teacherCourseDTO);
        }
        return teachersWithCourses;
    }
	
	// Modify the method to accept the teacher object and course IDs
	public Teacher updateTeacherWithCourses(Teacher teacher, Integer teacherId, List<Integer> updatedCourseIds) {
	    // Set the teacher's ID if it's not already set
	    if (teacherId != null) {
	        teacher.setTeacherId(teacherId);
	    }
	    
	    // Update teacher information in the Teacher table
	    teacherRepository.save(teacher);
	    
	    // Update associated courses in the TeacherCourse table
	    teacherCourseService.updateTeacherCourses(teacher.getTeacherId(), updatedCourseIds);
	    
	    return teacher;
	}
}

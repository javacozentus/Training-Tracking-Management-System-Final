package com.cozentus.TrainingTracking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cozentus.TrainingTracking.Model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer>{
	
//	List<Teacher> findByCourseId(int courseId);

}

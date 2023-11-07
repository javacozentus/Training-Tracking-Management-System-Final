package com.cozentus.TrainingTracking.DTO;

import java.util.List;

import com.cozentus.TrainingTracking.Model.Course;
import com.cozentus.TrainingTracking.Model.Program;

public class ProgramCourseDTO {
	
	private Program program;
    private List<Course> courses;
    
	public ProgramCourseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProgramCourseDTO(Program program, List<Course> courses) {
		super();
		this.program = program;
		this.courses = courses;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "ProgramCourseDTO [program=" + program + ", courses=" + courses + "]";
	}
	
	
   
}

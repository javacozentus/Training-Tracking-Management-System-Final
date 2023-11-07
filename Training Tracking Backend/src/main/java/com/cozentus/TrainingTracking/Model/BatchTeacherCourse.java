package com.cozentus.TrainingTracking.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="batch_teacher_course")
public class BatchTeacherCourse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "batch_teacher_course_id")
	private Integer batchTeacherCourseId;
 
	@Column(name = "batch_id")
	private Integer batchId;
 
	@Column(name = "teacher_course_id")
	private Integer teacherCourseId;
 
	public BatchTeacherCourse() {}
 
	public BatchTeacherCourse(Integer batchTeacherCourseId, Integer batchId, Integer teacherCourseId) {
		super();
		this.batchTeacherCourseId = batchTeacherCourseId;
		this.batchId = batchId;
		this.teacherCourseId = teacherCourseId;
	}
 
	public Integer getBatchTeacherCourseId() {
		return batchTeacherCourseId;
	}
 
	public void setBatchTeacherCourseId(Integer batchTeacherCourseId) {
		this.batchTeacherCourseId = batchTeacherCourseId;
	}
 
	public Integer getBatchId() {
		return batchId;
	}
 
	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}
 
	public Integer getTeacherCourseId() {
		return teacherCourseId;
	}
 
	public void setTeacherCourseId(Integer teacherCourseId) {
		this.teacherCourseId = teacherCourseId;
	}
 
	@Override
	public String toString() {
		return "BatchTeacherCourse [batchTeacherCourseId=" + batchTeacherCourseId + ", batchId=" + batchId
				+ ", teacherCourseId=" + teacherCourseId + "]";
	}
}

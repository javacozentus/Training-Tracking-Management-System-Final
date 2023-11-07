package com.cozentus.TrainingTracking.Model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "batch")
public class Batch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "batch_id")
	private Integer batchId;
 
	@Column(name = "batch_code")
	private String batchCode;
 
	@Column(name = "batch_name")
	private String batchName;
 
	@Column(name = "batch_startdate")
	private Date batchStartdate;
	@Column(name = "teacher_course_id")
	private Integer teacherCourseId;
 
	@Column(name = "created_date")
	private Date createdDate;
 
	@Column(name = "created_by")
	private String createdBy;
 
	@Column(name = "updated_date")
	private Date updatedDate;
 
	@Column(name = "updated_by")
	private String updatedBy;
 
	public Batch() {
		super();
		// TODO Auto-generated constructor stub
	}
 
	public Batch(Integer batchId, String batchCode, String batchName, Date batchStartdate, Integer teacherCourseId,
			Date createdDate, String createdBy, Date updatedDate, String updatedBy) {
		super();
		this.batchId = batchId;
		this.batchCode = batchCode;
		this.batchName = batchName;
		this.batchStartdate = batchStartdate;
		this.teacherCourseId = teacherCourseId;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
	}
 
	public Integer getBatchId() {
		return batchId;
	}
 
	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}
 
	public String getBatchCode() {
		return batchCode;
	}
 
	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}
 
	public String getBatchName() {
		return batchName;
	}
 
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
 
	public Date getBatchStartdate() {
		return batchStartdate;
	}
 
	public void setBatchStartdate(Date batchStartdate) {
		this.batchStartdate = batchStartdate;
	}
 
	public Integer getTeacherCourseId() {
		return teacherCourseId;
	}
 
	public void setTeacherCourseId(Integer teacherCourseId) {
		this.teacherCourseId = teacherCourseId;
	}
 
	public Date getCreatedDate() {
		return createdDate;
	}
 
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
 
	public String getCreatedBy() {
		return createdBy;
	}
 
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
 
	public Date getUpdatedDate() {
		return updatedDate;
	}
 
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
 
	public String getUpdatedBy() {
		return updatedBy;
	}
 
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
 
	@Override
	public String toString() {
		return "Batch [batchId=" + batchId + ", batchCode=" + batchCode + ", batchName=" + batchName
				+ ", batchStartdate=" + batchStartdate + ", teacherCourseId=" + teacherCourseId + ", createdDate="
				+ createdDate + ", createdBy=" + createdBy + ", updatedDate=" + updatedDate + ", updatedBy=" + updatedBy
				+ "]";
	}	
}

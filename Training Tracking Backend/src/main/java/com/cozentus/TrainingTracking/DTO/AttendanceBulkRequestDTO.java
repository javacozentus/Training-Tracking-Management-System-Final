package com.cozentus.TrainingTracking.DTO;

import java.sql.Date;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class AttendanceBulkRequestDTO {
    
	private Integer topicId;
    private List<Integer> studentIds;
    private Date date;
    private Boolean attendance;
    private Integer batchId;
    
	public AttendanceBulkRequestDTO() {}
 
	public AttendanceBulkRequestDTO(Integer topicId, List<Integer> studentIds, Date date, Boolean attendance,
			Integer batchId) {
		super();
		this.topicId = topicId;
		this.studentIds = studentIds;
		this.date = date;
		this.attendance = attendance;
		this.batchId = batchId;
	}
 
	public Integer getTopicId() {
		return topicId;
	}
 
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
 
	public List<Integer> getStudentIds() {
		return studentIds;
	}
 
	public void setStudentIds(List<Integer> studentIds) {
		this.studentIds = studentIds;
	}
 
	public Date getDate() {
		return date;
	}
 
	public void setDate(Date date) {
		this.date = date;
	}
 
	public Boolean getAttendance() {
		return attendance;
	}
 
	public void setAttendance(Boolean attendance) {
		this.attendance = attendance;
	}
 
	public Integer getBatchId() {
		return batchId;
	}
 
	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}
 
	@Override
	public String toString() {
		return "AttendanceBulkRequestDTO [topicId=" + topicId + ", studentIds=" + studentIds + ", date=" + date
				+ ", attendance=" + attendance + ", batchId=" + batchId + "]";
	}
}
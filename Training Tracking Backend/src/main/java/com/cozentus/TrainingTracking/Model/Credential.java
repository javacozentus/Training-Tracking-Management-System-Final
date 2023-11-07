package com.cozentus.TrainingTracking.Model;
 
import java.util.Date;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "credentials")
public class Credential {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "credential_id")
	private Integer credentialId;
 
	@Column(name = "user_id")
	private String userId;
 
	@Column(name = "password")
	private String password;
 
	@Column(name = "role")
	private String role;
 
	@Column(name = "updated_date")
	private Date updatedDate;
 
	@Column(name = "updated_by")
	private String updatedBy;
 
	@Column(name = "created_date")
	private Date createdDate;
 
	@Column(name = "created_by")
	private String createdBy;
 
	public Credential() {}
 
	public Credential(Integer credentialId, String userId, String password, String role, Date updatedDate,
			String updatedBy, Date createdDate, String createdBy) {
		super();
		this.credentialId = credentialId;
		this.userId = userId;
		this.password = password;
		this.role = role;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
	}
 
	public Integer getCredentialId() {
		return credentialId;
	}
 
	public void setCredentialId(Integer credentialId) {
		this.credentialId = credentialId;
	}
 
	public String getUserId() {
		return userId;
	}
 
	public void setUserId(String userId) {
		this.userId = userId;
	}
 
	public String getPassword() {
		return password;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}
 
	public String getRole() {
		return role;
	}
 
	public void setRole(String role) {
		this.role = role;
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
 
	@Override
	public String toString() {
		return "Credential [credentialId=" + credentialId + ", userId=" + userId + ", password=" + password + ", role="
				+ role + ", updatedDate=" + updatedDate + ", updatedBy=" + updatedBy + ", createdDate=" + createdDate
				+ ", createdBy=" + createdBy + "]";
	}
}
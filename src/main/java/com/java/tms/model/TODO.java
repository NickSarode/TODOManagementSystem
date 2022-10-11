package com.java.tms.model;


import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="TODO")
public class TODO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tId;
	private String taskName;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime date;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime lastUpdateddate;
	private boolean taskStatus;
	private String description;
	private String userName;
	
	public TODO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TODO(Long tId, String taskName, LocalDateTime date, LocalDateTime lastUpdateddate, boolean taskStatus,
			String description, String userName) {
		super();
		this.tId = tId;
		this.taskName = taskName;
		this.date = date;
		this.lastUpdateddate = lastUpdateddate;
		this.taskStatus = taskStatus;
		this.description = description;
		this.userName = userName;
	}

	public Long gettId() {
		return tId;
	}

	public void settId(Long tId) {
		this.tId = tId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public LocalDateTime getLastUpdateddate() {
		return lastUpdateddate;
	}

	public void setLastUpdateddate(LocalDateTime lastUpdateddate) {
		this.lastUpdateddate = lastUpdateddate;
	}

	public boolean isTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(boolean taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}

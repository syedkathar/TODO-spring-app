package com.deltaa.todo.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="task")
@Where(clause = "markForDelete='false'")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name="title", nullable = false)
	private String title;
	
	@NotNull
	@Column(name = "description", length = 100)
	private String description;
	
	@Column(name="tag", length = 50)
	private String tag;
	
	@Column(name="date_completed")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date completedAt;
	
	@Column(name="date_remind")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date remindMeAt;
	
	@Column(name="date_created")
	@JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
	private Date createdAt;
	
	@Column(name="date_modified")
	@JsonFormat(pattern="yyyy-MM-dd'T'hh:mm:ss")
	private Date modifiedAt;

	@Column(name="markfordelete")
	private boolean markForDelete;
	
	@Column(name="status")
	private TaskStatus status;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="tasklist_id")
	private TaskList taskList;
	
	public Task() {
		
	}
	
	@PrePersist
	protected void onCreate() {
		this.status = TaskStatus.OPEN;
		this.markForDelete = false;
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		if(this.status.equals(TaskStatus.COMPLETED)) {
			this.completedAt=new Date();
		}
		this.modifiedAt = new Date();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public TaskList getTaskList() {
		return taskList;
	}

	public void setTaskList(TaskList taskList) {
		this.taskList = taskList;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public Date getRemindMeAt() {
		return remindMeAt;
	}

	public void setRemindMeAt(Date remindMeAt) {
		this.remindMeAt = remindMeAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public boolean isMarkForDelete() {
		return markForDelete;
	}

	public void setMarkForDelete(boolean markForDelete) {
		this.markForDelete = markForDelete;
	}
	

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public Date getCompletedAt() {
		return completedAt;
	}

	public void setCompletedAt(Date completedAt) {
		this.completedAt = completedAt;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description=" + description + ", tag=" + tag + ", completed="
				+ completedAt + ", remindMeAt=" + remindMeAt + ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt
				+ ", markForDelete=" + markForDelete + ", status=" + status + "]";
	}	
}

package com.deltaa.todo.domain;

import java.util.Date;

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

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="task")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name="title", nullable = false, unique = true)
	private String title;
	
	@NotNull
	@Column(name = "description", length = 100)
	private String description;
	
	@Column(name="tag", length = 50)
	private String tag;
	
	@Column(name="date_completed")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date completed;
	
	@Column(name="date_due")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date due;
	
	@Column(name="date_created")
	@JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
	private Date created;
	
	@Column(name="date_modified")
	@JsonFormat(pattern="yyyy-MM-dd'T'hh:mm:ss")
	private Date modified;

	@Column(name="markfordelete")
	private boolean markForDelete = false;
	
	@ManyToOne
	@JoinColumn(name="tasklist_id", nullable=false)
	private TaskList taskList;
	
	public Task() {
		
	}
	
	@PrePersist
	protected void onCreate() {
		this.created = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.modified = new Date();
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

	public Date getCompleted() {
		return completed;
	}

	public void setCompleted(Date completed) {
		this.completed = completed;
	}

	public Date getDue() {
		return due;
	}

	public void setDue(Date due) {
		this.due = due;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
	
	public boolean isMarkForDelete() {
		return markForDelete;
	}

	public void setMarkForDelete(boolean markForDelete) {
		this.markForDelete = markForDelete;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description=" + description + ", tag=" + tag + ", completed="
				+ completed + ", due=" + due + ", created=" + created + ", modified=" + modified + ", markForDelete="
				+ markForDelete + ", taskList=" + taskList + "]";
	}	
}

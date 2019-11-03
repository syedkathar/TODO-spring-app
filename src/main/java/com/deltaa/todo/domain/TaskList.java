package com.deltaa.todo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="tasklist")
public class TaskList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private String id;
	
	@Column(name="title", length=50)
	@Size(max=50)
	private String title;
	
	@OneToMany(mappedBy = "taskList", cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Task.class)
	private List<Task> tasks;
	
	@Column(name="date_created")
	@JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
	private Date createdAt;
	
	@Column(name="date_modified")
	@JsonFormat(pattern="yyyy-MM-dd'T'hh:mm:ss")
	private Date modifiedAt;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.modifiedAt = new Date();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		for(Task task:tasks) {
			System.out.println("for loop setTask called::::"+task);
			addTask(task);
		}
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

	public void addTask(Task newTask) {
	    System.out.println("addTask called....."+newTask);
		if(this.tasks == null) {
			this.tasks = new ArrayList<Task>();
		}
		this.tasks.add(newTask);
		newTask.setTaskList(this);
		System.out.println("addToTask completed...."+newTask);
	}
	
	@Override
	public String toString() {
		return "TaskList [id=" + id + ", title=" + title + ", tasks=" + tasks + ", created=" + createdAt + ", modified="
				+ modifiedAt + "]";
	}
}

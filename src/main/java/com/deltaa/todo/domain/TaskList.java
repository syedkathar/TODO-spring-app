package com.deltaa.todo.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="task_list")
public class TaskList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private String id;
	
	@Column(name="title", length=50)
	@NotNull
	@Size(max=50)
	private String title;
	
	@OneToMany (mappedBy = "taskList", cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Task.class)
	private List<Task> tasks;
	
	@Column(name="date_created")
	@JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
	private Date created;
	
	@Column(name="date_modified")
	@JsonFormat(pattern="yyyy-MM-dd'T'hh:mm:ss")
	private Date modified;

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
		this.tasks = tasks;
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
	
	public void addTask(Task task) {
		if(this.tasks == null) {
			this.tasks = new ArrayList<Task>();
		}
		this.tasks.add(task);
	}
	
	@Override
	public String toString() {
		return "TaskList [id=" + id + ", title=" + title + ", tasks=" + tasks + ", created=" + created + ", modified="
				+ modified + "]";
	}
	
	
	
}

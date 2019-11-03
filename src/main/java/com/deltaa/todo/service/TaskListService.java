package com.deltaa.todo.service;

import java.util.List;
import java.util.Optional;

import com.deltaa.todo.service.dto.TaskListDTO;

public interface TaskListService {
	Optional<List<TaskListDTO>> getAllTaskList();
	Optional<TaskListDTO> getTasksByList(String id);
	TaskListDTO createTaskList(TaskListDTO taskListDTO);
	Optional<TaskListDTO> addOrUpdateTasks(TaskListDTO taskListDTO);
}

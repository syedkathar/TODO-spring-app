package com.deltaa.todo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deltaa.todo.domain.TaskList;
import com.deltaa.todo.repository.TaskListRepository;
import com.deltaa.todo.rest.exception.TaskListdAlreadyExistsException;
import com.deltaa.todo.rest.mapper.v1.TaskListMapper;
import com.deltaa.todo.service.dto.TaskListDTO;

@Service
@Transactional
public class TaskListServiceImpl implements TaskListService {
	
 	@Autowired
	private TaskListRepository taskListRepository;
	
	@Autowired
	private TaskListMapper taskListMapper;
	

	@Override
	public TaskListDTO createTaskList(TaskListDTO taskListDTO) {
		TaskList newTaskList = null;
		if(null != taskListDTO.getId()) {
			throw new TaskListdAlreadyExistsException("Task List already exists, can't create same again with the same id :["+taskListDTO.getId()+"]");
		}
		else {
			TaskList taskListObj = taskListMapper.taskListDTOtoTaskList(taskListDTO);
			newTaskList = taskListRepository.save(taskListObj);
		}
		return taskListMapper.taskListtoTaskListDTO(newTaskList);
	}


	@Override
	public Optional<TaskListDTO> addOrUpdateTasks(TaskListDTO taskListDTO) {
		 return Optional.of(taskListRepository.findById(taskListDTO.getId()))
	                .filter(Optional::isPresent)
	                .map(Optional::get)
	                .map(taskList -> {
	                    taskList = taskListMapper.taskListDTOtoTaskList(taskListDTO);
	                    taskListRepository.save(taskList);
	                    return taskList;
	                })
	                .map(taskList -> {
	                    return taskListMapper.taskListtoTaskListDTO(taskList);
	                });
	}

	@Override
	public Optional<List<TaskListDTO>> getAllTaskList() {
		return Optional.of(taskListRepository.findAll()
				.stream()
				.map(taskList -> {
					return taskListMapper.taskListtoTaskListDTO(taskList);
				}).collect(Collectors.toList()));
	}	

	@Override
	public Optional<TaskListDTO> getTasksByList(String id) {
		return Optional.of(taskListRepository.findById(id))
				.filter(Optional::isPresent)
				.map(Optional::get)
				.map(taskList -> {
					return taskListMapper.taskListtoTaskListDTO(taskList);
				});
	}

}

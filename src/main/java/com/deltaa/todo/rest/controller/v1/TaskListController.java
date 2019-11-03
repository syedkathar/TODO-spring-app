package com.deltaa.todo.rest.controller.v1;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deltaa.todo.service.TaskListService;
import com.deltaa.todo.service.ValidationErrorService;
import com.deltaa.todo.service.dto.TaskListDTO;

@RestController
@RequestMapping("/api/tasklist")
public class TaskListController {
	
	private final Logger logger = LoggerFactory.getLogger(TaskListController.class);
	
	@Autowired
	private TaskListService taskListService;
	
	@Autowired
	private ValidationErrorService validationErrorService;
	
	
	@PostMapping
	public ResponseEntity<?> createTaskList(@RequestBody @Valid TaskListDTO taskListDTO, BindingResult result) {
		logger.info("REST API request for new task list: {}"+taskListDTO);
		ResponseEntity<?> fieldErrorsMap = validationErrorService.mapValidationService(result);
		if(null != fieldErrorsMap) {
			return fieldErrorsMap;
		}
		TaskListDTO newTaskList = taskListService.createTaskList(taskListDTO);
		return new ResponseEntity<>(newTaskList, HttpStatus.CREATED);
	}
	
	@PutMapping(path="{id}")
	public ResponseEntity<?> addOrUpdateTasks(@PathVariable String id, @RequestBody @Valid TaskListDTO taskListDTO, BindingResult result) {
		logger.info("REST API request for add/update task : {}"+taskListDTO);
		if(id != null) {
			taskListDTO.setId(id);
		}
		ResponseEntity<?> fieldErrorsMap = validationErrorService.mapValidationService(result);
		if(null != fieldErrorsMap) {
			logger.info("There are validation errors.");
			return fieldErrorsMap;
		}
		Optional<TaskListDTO> newTaskList = taskListService.addOrUpdateTasks(taskListDTO);
		return new ResponseEntity<>(newTaskList, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllTaskList() {
		Optional<List<TaskListDTO>> allTaskList = taskListService.getAllTaskList();
	    return new ResponseEntity<>(allTaskList, HttpStatus.OK);
	}
	
	@GetMapping(path="{id}")
	public ResponseEntity<?> getTasksByTasksList(@PathVariable String id) {
		Optional<TaskListDTO> taskListDTO = taskListService.getTasksByList(id);
	    return new ResponseEntity<>(taskListDTO, HttpStatus.OK);
	}
}

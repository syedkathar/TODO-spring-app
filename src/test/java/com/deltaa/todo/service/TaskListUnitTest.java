package com.deltaa.todo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.deltaa.todo.domain.TaskStatus;
import com.deltaa.todo.rest.exception.TaskListNotFoundException;
import com.deltaa.todo.rest.exception.TaskListdAlreadyExistsException;
import com.deltaa.todo.service.dto.TaskDTO;
import com.deltaa.todo.service.dto.TaskListDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TaskListUnitTest {
	private TaskDTO taskDTO, taskDTO1;
	
	private TaskListDTO taskListDTO;
	
	private List<TaskDTO> tasks;
	
	@Autowired
	private TaskListService taskListService;
	
	@Before
    public void init() {
    	taskListDTO = new TaskListDTO();
    	taskListDTO.setTitle("House Work-TaskList");
    	
		taskDTO = new TaskDTO();
    	taskDTO.setDescription("Cleaning");
    	taskDTO.setTitle("Dish Washing");
    	
    	taskDTO1 = new TaskDTO();
    	taskDTO1.setDescription("Cooking");
    	taskDTO1.setTitle("Dinner");
    	
	}
	
	
	
	@Test
	public void assertThatTaskListAlongWtihTaskCanBeCreated() {
		tasks = new ArrayList<TaskDTO>(1);
		tasks.add(taskDTO);
		taskListDTO.setTasks(tasks);
		TaskListDTO taskListDTOCreated = taskListService.createTaskList(taskListDTO);
		assertThat(taskListDTOCreated).isNotNull();
		assertThat(taskListDTOCreated.getTasks()).hasSize(1);
	}
	
	@Test
	public void assertThatTaskCanBeAddedToList() {
		tasks = new ArrayList<TaskDTO>(1);
		tasks.add(taskDTO1);
		taskListDTO.setTasks(tasks);
		//set Id as 1 to update
		taskDTO.setId(1L);
		
		TaskListDTO taskListDTOCreated = taskListService.createTaskList(taskListDTO);
		assertThat(taskListDTOCreated).isNotNull();
		assertThat(taskListDTOCreated.getTasks()).hasSize(1);
		
	}
	
	@Test
	public void assertThatMultipleTaskListCanBeCreated()
	{
		tasks = new ArrayList<TaskDTO>(1);
		tasks.add(taskDTO);
		taskListDTO.setTasks(tasks);
		TaskListDTO taskListDTOCreated = taskListService.createTaskList(taskListDTO);
		TaskListDTO taskListDTOCreated_1 = taskListService.createTaskList(taskListDTO);
		assertThat(taskListDTOCreated).isNotNull();
		assertThat(taskListDTOCreated_1).isNotNull();
		//
		Optional<List<TaskListDTO>> taskLists = taskListService.getAllTaskList();
		assertThat(taskLists).isNotNull();
		assertThat(taskLists).get().asList().hasSize(2);
	}
	
	@Test
	public void assertThatTaskAnItemCanMarkAsCompleted() {
		tasks = new ArrayList<TaskDTO>(1);
		tasks.add(taskDTO);
		
		taskListDTO = new TaskListDTO();
		taskListDTO.setTasks(tasks);
		TaskListDTO taskListDTOCreated = taskListService.createTaskList(taskListDTO);
		assertThat(taskListDTOCreated).isNotNull();
		
		// get Task by Id
		Optional<List<TaskListDTO>> taskListsRetrieved = taskListService.getAllTaskList();
		assertThat(taskListsRetrieved).isNotNull();
		assertThat(taskListsRetrieved.get().get(0).getId()).isNotNull();
		
		taskListsRetrieved.get().get(0).getTasks().get(0).setStatus(TaskStatus.COMPLETED);
		Optional<TaskListDTO> taskListDTOToUdate = taskListService.addOrUpdateTasks(taskListsRetrieved.get().get(0));
		assertThat(taskListDTOToUdate).isNotNull();
		assertThat(taskListDTOToUdate.get().getTasks().get(0).getStatus().equals(TaskStatus.COMPLETED));
	}
	
	@Test
	public void assertThatTasksCanBeDeleted() {
		tasks = new ArrayList<TaskDTO>(2);
		tasks.add(taskDTO);
		tasks.add(taskDTO1);
		
		taskListDTO = new TaskListDTO();
		taskListDTO.setTasks(tasks);
		TaskListDTO taskListDTOCreated = taskListService.createTaskList(taskListDTO);
		
		assertThat(taskListDTOCreated).isNotNull();
		
		// get Task by Id
		Optional<List<TaskListDTO>> taskListsRetrieved = taskListService.getAllTaskList();
		assertThat(taskListsRetrieved).isNotNull();
		assertThat(taskListsRetrieved.get().get(0).getTasks()).hasSize(2);
		assertThat(taskListsRetrieved.get().get(0).getId()).isNotNull();
		
		
		taskListsRetrieved.get().get(0).getTasks().get(0).setMarkForDelete(true);
		Optional<TaskListDTO> taskListDTOToUdate = taskListService.addOrUpdateTasks(taskListsRetrieved.get().get(0));
		assertThat(taskListDTOToUdate).isNotNull();
		
		Optional<List<TaskListDTO>> taskListsRetrievedAfterUpdate = taskListService.getAllTaskList();
		assertThat(taskListsRetrievedAfterUpdate).isNotNull();
		assertThat(taskListsRetrievedAfterUpdate.get().get(0).getId()).isNotNull();
		assertThat(taskListsRetrievedAfterUpdate.get().get(0).getTasks().get(0).isMarkForDelete()==true);
	}
	
	@Test
	public void assertThatTasksCanBeRestored() {
		tasks = new ArrayList<TaskDTO>(2);
		tasks.add(taskDTO);
		tasks.add(taskDTO1);
		
		taskListDTO = new TaskListDTO();
		taskListDTO.setTasks(tasks);
		TaskListDTO taskListDTOCreated = taskListService.createTaskList(taskListDTO);
		
		assertThat(taskListDTOCreated).isNotNull();
		
		// get Task by Id
		Optional<List<TaskListDTO>> taskListsRetrieved = taskListService.getAllTaskList();
		assertThat(taskListsRetrieved).isNotNull();
		assertThat(taskListsRetrieved.get().get(0).getTasks()).hasSize(2);
		assertThat(taskListsRetrieved.get().get(0).getId()).isNotNull();
		
		
		taskListsRetrieved.get().get(0).getTasks().get(0).setMarkForDelete(false);
		Optional<TaskListDTO> taskListDTOToUdate = taskListService.addOrUpdateTasks(taskListsRetrieved.get().get(0));
		assertThat(taskListDTOToUdate).isNotNull();
		
		Optional<List<TaskListDTO>> taskListsRetrievedAfterUpdate = taskListService.getAllTaskList();
		assertThat(taskListsRetrievedAfterUpdate).isNotNull();
		assertThat(taskListsRetrievedAfterUpdate.get().get(0).getId()).isNotNull();
		assertThat(taskListsRetrievedAfterUpdate.get().get(0).getTasks().get(0).isMarkForDelete()==false);
	}
	
	
	@Test
	public void assertThatTasksCanBeTagged() {
		tasks = new ArrayList<TaskDTO>(2);
		tasks.add(taskDTO);
		tasks.add(taskDTO1);
		
		taskListDTO = new TaskListDTO();
		taskListDTO.setTasks(tasks);
		TaskListDTO taskListDTOCreated = taskListService.createTaskList(taskListDTO);
		
		assertThat(taskListDTOCreated).isNotNull();
		
		// get Task by Id
		Optional<List<TaskListDTO>> taskListsRetrieved = taskListService.getAllTaskList();
		assertThat(taskListsRetrieved).isNotNull();
		assertThat(taskListsRetrieved.get().get(0).getTasks()).hasSize(2);
		assertThat(taskListsRetrieved.get().get(0).getId()).isNotNull();
		
		
		taskListsRetrieved.get().get(0).getTasks().get(0).setTag("ITEM TAGGED");
		Optional<TaskListDTO> taskListDTOToUdate = taskListService.addOrUpdateTasks(taskListsRetrieved.get().get(0));
		assertThat(taskListDTOToUdate).isNotNull();
		
		Optional<List<TaskListDTO>> taskListsRetrievedAfterUpdate = taskListService.getAllTaskList();
		assertThat(taskListsRetrievedAfterUpdate).isNotNull();
		assertThat(taskListsRetrievedAfterUpdate.get().get(0).getId()).isNotNull();
		assertThat(taskListsRetrievedAfterUpdate.get().get(0).getTasks().get(0).getTag().equals("ITEM TAGGED"));
	}
	
	@Test
	public void assertThatReminderCanbeAddedToTask() {
		tasks = new ArrayList<TaskDTO>(2);
		tasks.add(taskDTO);
		tasks.add(taskDTO1);
		
		taskListDTO = new TaskListDTO();
		taskListDTO.setTasks(tasks);
		TaskListDTO taskListDTOCreated = taskListService.createTaskList(taskListDTO);
		
		assertThat(taskListDTOCreated).isNotNull();
		
		// get Task by Id
		Optional<List<TaskListDTO>> taskListsRetrieved = taskListService.getAllTaskList();
		assertThat(taskListsRetrieved).isNotNull();
		assertThat(taskListsRetrieved.get().get(0).getTasks()).hasSize(2);
		assertThat(taskListsRetrieved.get().get(0).getId()).isNotNull();
		
		
		taskListsRetrieved.get().get(0).getTasks().get(0).setRemindMeAt(new Date());
		Optional<TaskListDTO> taskListDTOToUdate = taskListService.addOrUpdateTasks(taskListsRetrieved.get().get(0));
		assertThat(taskListDTOToUdate).isNotNull();
		
		Optional<List<TaskListDTO>> taskListsRetrievedAfterUpdate = taskListService.getAllTaskList();
		assertThat(taskListsRetrievedAfterUpdate).isNotNull();
		assertThat(taskListsRetrievedAfterUpdate.get().get(0).getId()).isNotNull();
		assertThat(taskListsRetrievedAfterUpdate.get().get(0).getTasks().get(0).getRemindMeAt());
	}
	
	
	
	@Test(expected = TaskListdAlreadyExistsException.class)
	public void assertThatExceptionThrownWhenListIdExists() {
		tasks = new ArrayList<TaskDTO>(2);
		tasks.add(taskDTO);
		tasks.add(taskDTO1);
		
		taskListDTO = new TaskListDTO();
		taskListDTO.setTasks(tasks);
		taskListDTO.setId("1");
		taskListService.createTaskList(taskListDTO);
		
	}
	
	@Test(expected = TaskListNotFoundException.class)
	public void assertThatExceptionThrownWhenIdNotExistsDuringAddOrUpdate() {
		tasks = new ArrayList<TaskDTO>(2);
		tasks.add(taskDTO);
		tasks.add(taskDTO1);
		
		taskListDTO = new TaskListDTO();
		taskListDTO.setTasks(tasks);
		taskListDTO.setId("165");
		taskListService.addOrUpdateTasks(taskListDTO);
		
	}
	
	@Test
	public void assertThatTaskListRetrievedBasedOnId() {
		tasks = new ArrayList<TaskDTO>(1);
		tasks.add(taskDTO1);
		taskListDTO.setTasks(tasks);
		
		TaskListDTO taskListDTOCreated = taskListService.createTaskList(taskListDTO);
		assertThat(taskListDTOCreated).isNotNull();
		assertThat(taskListDTOCreated.getTasks()).hasSize(1);
		
		Optional<List<TaskListDTO>> taskListsRetrieved = taskListService.getAllTaskList();
		assertThat(taskListsRetrieved).isNotNull();
		assertThat(taskListsRetrieved.get()).isNotNull();
		
		//get task list
		Optional<TaskListDTO> taskListDTORetrievedBasedOnId = taskListService.getTasksByList(taskListsRetrieved.get().get(0).getId());
		assertThat(taskListDTORetrievedBasedOnId).isNotNull();
		
	}

}

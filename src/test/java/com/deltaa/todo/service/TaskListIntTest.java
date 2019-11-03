package com.deltaa.todo.service;


import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.deltaa.todo.domain.TaskStatus;
import com.deltaa.todo.service.dto.TaskDTO;
import com.deltaa.todo.service.dto.TaskListDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TaskListIntTest {

//    @MockBean
//    private TaskListService mockTaskListService;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    private TaskListDTO mockTaskListDTO;
//
//    private TaskListDTO mockTaskListDTO() {
//    	
//    	TaskDTO taskDTO = new TaskDTO();
//    	taskDTO.setDescription("Cleaning");
//    	taskDTO.setTitle("Dish Washing");
//    	List<TaskDTO> taskList = new ArrayList<TaskDTO>();
//    	mockTaskListDTO = new TaskListDTO();
//    	mockTaskListDTO.setTitle("House Work");
//    	taskList.add(taskDTO);
//    	mockTaskListDTO.setTasks(taskList);
//        return mockTaskListDTO;
//    }
// 
//
//    @Test
//    @DisplayName("POST /tasklist - success")
//    void testCreateTaskList() throws Exception {
//        // setup Mock Service
//        TaskListDTO postTaskListDTO = mockTaskListDTO();
//        postTaskListDTO.setId("1");
//        doReturn(mockTaskListDTO).when(mockTaskListService).createTaskList(any());
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/todo/tasklist")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(asJsonString(postTaskListDTO)))
//
//                // validate the response and content type
//        .andExpect(status().isCreated())
//        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//
//        //validate the return fields
//        .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.title", is("Syed")))
//                        .andExpect(jsonPath("$.creditRating", is(2)))
//        .andExpect(jsonPath("$.id", is(1)));
//    }
//
//    @Test
//    @DisplayName("PUT /TaskList - success")
//    void testAddUpdateDeleteRestoreTask() throws Exception {
//        // setup Mocked service
//    	TaskListDTO postTaskListDTO = mockTaskListDTO();
//        postTaskListDTO.setId("1");
//        postTaskListDTO.getTasks().get(1).setMarkForDelete(true); // delete task
//        postTaskListDTO.getTasks().get(1).setRemindMeAt(new Date()); // add reminder to task
//        postTaskListDTO.getTasks().get(1).setStatus(TaskStatus.COMPLETED); // add task to COMPLETED
//        postTaskListDTO.getTasks().get(1).setTag("PERSONAL-TAG"); // add tag to the task
//        doReturn(Optional.of(postTaskListDTO)).when(mockTaskListService).addOrUpdateTasks((any()));
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/todo/customers/1")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(asJsonString(postTaskListDTO)))
//
//                // validate the response code and return type
//                .andExpect(status().isCreated())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//
//                //validate the returned fields
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.[*].markForDelete").value(Matchers.hasItem(("true"))));
//                
//    }
//
//    @Test
//    @DisplayName("GET /TaskList - Success")
//    void testGetAllTaskLists() throws Exception {
//        // setup Mocked service
//    	TaskListDTO postTaskListDTO = mockTaskListDTO();
//    	postTaskListDTO.setId("1");
//        doReturn(postTaskListDTO).when(mockTaskListService).getAllTaskList();
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo/tasklist"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//
//                //validate the returned fields
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.[*].markForDelete").value(Matchers.hasItem(("true"))));
//    }
//
//    static String asJsonString(final Object object){
//        try {
//            return new ObjectMapper().writeValueAsString(object);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }


}

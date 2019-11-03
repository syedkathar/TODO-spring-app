package com.deltaa.todo.rest.mapper.v1;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.deltaa.todo.domain.TaskList;
import com.deltaa.todo.service.dto.TaskListDTO;

@Mapper
@Component
public interface TaskListMapper {
    TaskListMapper INSTANCE = Mappers.getMapper(TaskListMapper.class);

    TaskListDTO taskListtoTaskListDTO(TaskList taskList);

    TaskList taskListDTOtoTaskList(TaskListDTO taskListDTO);
} 

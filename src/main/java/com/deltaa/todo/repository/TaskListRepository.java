package com.deltaa.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deltaa.todo.domain.TaskList;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList,String>{
}

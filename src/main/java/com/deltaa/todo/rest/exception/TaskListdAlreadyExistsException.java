package com.deltaa.todo.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TaskListdAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TaskListdAlreadyExistsException() {
    }

    public TaskListdAlreadyExistsException(String message) {
        super(message);
    }

    public TaskListdAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}

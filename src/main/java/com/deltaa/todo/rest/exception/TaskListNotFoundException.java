package com.deltaa.todo.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaskListNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TaskListNotFoundException() {
        super();
    }

    public TaskListNotFoundException(String message) {
        super(message);
    }

    public TaskListNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskListNotFoundException(Throwable cause) {
        super(cause);
    }

    protected TaskListNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

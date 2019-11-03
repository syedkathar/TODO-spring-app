package com.deltaa.todo.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ValidationErrorService {

    public ResponseEntity<?> mapValidationService(BindingResult result) {
        if(result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            Map<String, String> fieldErrorsMap = new HashMap<>();
            fieldErrors.forEach(fieldError -> {
                fieldErrorsMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            });
            return new ResponseEntity<Map>(fieldErrorsMap, HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}

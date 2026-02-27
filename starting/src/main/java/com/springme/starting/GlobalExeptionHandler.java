package com.springme.starting;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class GlobalExeptionHandler
{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> HandleFailedValidation(MethodArgumentNotValidException ex)
    {
        Map<String, List<String>> errors = new HashMap<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        Iterator<FieldError> fieldErrorIterator = fieldErrors.iterator();
        for (;fieldErrorIterator.hasNext();)
        {
            FieldError fieldError = fieldErrorIterator.next();
            errors.computeIfAbsent(fieldError.getField(), inittializeWith -> new ArrayList<>()).add(fieldError.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(TaskNotFoundExp.class)
    public ResponseEntity<String> onTaskNotFoundExp(TaskNotFoundExp ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }


}

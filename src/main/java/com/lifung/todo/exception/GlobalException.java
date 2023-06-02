package com.lifung.todo.exception;

import com.lifung.todo.dto.ErrorReadDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler
    public ResponseEntity<ErrorReadDTO> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorReadDTO error = new ErrorReadDTO();
        error.setMessage(ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}

package vn.su.bookauthor.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import vn.su.bookauthor.validate.NotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class ) 
    public ResponseEntity<String> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

 
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        e.printStackTrace(); 
        return ResponseEntity.status(500).body("Unknow error");
    }
}

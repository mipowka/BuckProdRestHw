package org.example.buckprodresthw.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }
    @ExceptionHandler(BucketNotFoundException.class)
    ResponseEntity<String> handleBucketNotFoundException(BucketNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }
}

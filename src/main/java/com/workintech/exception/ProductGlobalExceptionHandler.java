package com.workintech.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ProductGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(ProductException productException) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(productException.getMessage(), productException.getHttpStatus().value(),
                LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, productException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(CategoryException categoryException) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(categoryException.getMessage(), categoryException.getHttpStatus().value(),
                LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, categoryException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(Exception exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

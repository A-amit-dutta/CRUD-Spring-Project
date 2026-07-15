package com.example.CRUDbyFM.App.Exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

// EXCEPTION HANDLING METHOD (CUSTOM EXCEPTION )
//If anywhere this exception triggers then the below defined method will be used

//@ComponentScan("com.example.CRUDbyFM.Exceptions")
//@ControllerAdvice
@RestControllerAdvice // return JSON format
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({UserNotFoundException.class,IllegalArgumentException.class,NullPointerException.class})
    public ResponseEntity<Map<String,Object>> handleIllegalArgumentException(
//            IllegalArgumentException exception
            Exception exception
    ){
        logger.info("Error finding the user : ",exception);
        Map<String,Object> errorResponse = new HashMap<>();
        errorResponse.put("TIME STAMP", LocalDateTime.now());
        errorResponse.put("Status", HttpStatus.NOT_FOUND);
        errorResponse.put("Error","Bad Request");
        errorResponse.put("Message",exception.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Map<String,Object>> handleHttpRequestMethodNotSupported(
            Exception exception
    ){
        Map<String,Object> errorResponse = new HashMap<>();
        errorResponse.put("TIME STAMP", LocalDateTime.now());
        errorResponse.put("Status", HttpStatus.METHOD_NOT_ALLOWED.value());
        errorResponse.put("Error","Method not Found.... on this endpoint.");
        errorResponse.put("Message",exception.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.METHOD_NOT_ALLOWED);
    }
}

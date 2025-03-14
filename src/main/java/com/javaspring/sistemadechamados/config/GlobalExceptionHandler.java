package com.javaspring.sistemadechamados.config;

import java.time.LocalDateTime;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.javaspring.sistemadechamados.application.dto.response.ErrorResponseDTO;
import com.javaspring.sistemadechamados.web.exception.BusinessException;
import com.javaspring.sistemadechamados.web.exception.ResourceNotFoundException;
import com.javaspring.sistemadechamados.web.exception.ValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException e){
        logger.info("Resource not found: " + e.getMessage());
        return new ResponseEntity<>(new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);     }

    
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationException(ValidationException e) {
        logger.info("Validation error" + e.getMessage());
        return new ResponseEntity<>(new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDTO> handleBusinessException(BusinessException e) {
        logger.info("business error " + e.getMessage());
        return new ResponseEntity<>(new ErrorResponseDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage(), LocalDateTime.now()), HttpStatus.UNPROCESSABLE_ENTITY);

    }

    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));
                 logger.info("Validation input error" + errorMessage);

        return new ResponseEntity<>(new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception e) {
        logger.info("Internal error: " + e.getMessage());
        return new ResponseEntity<>(new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), LocalDateTime.now()), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    }

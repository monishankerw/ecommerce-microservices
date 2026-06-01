package com.ecommerce.common.exception;

import com.ecommerce.common.dto.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handleProductNotFound(
            ProductNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ApiResponseDto.builder()
                                .success(false)
                                .message(ex.getMessage())
                                .data(null)
                                .build()
                );
    }

    @ExceptionHandler(
            MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationException(
            MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        return ResponseEntity.badRequest()
                .body(
                        ApiResponseDto.builder()
                                .success(false)
                                .message(message)
                                .data(null)
                                .build()
                );
    }
}
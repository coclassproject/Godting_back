package com.gts.godting.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { CustomExcepition.class })
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomExcepition e) {
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler(value = { ConstraintViolationException.class, DataIntegrityViolationException.class })
    protected ResponseEntity<ErrorResponse> handleDataException() {
        return ErrorResponse.toResponseEntity(ErrorCode.DUPLICATE_RESOURCE);
    }

}

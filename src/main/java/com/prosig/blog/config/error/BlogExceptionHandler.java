package com.prosig.blog.config.error;

import com.prosig.blog.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
@Slf4j
public class BlogExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException re) {
        log.error(re.getMessage(), re);
        ErrorResponse response = ErrorResponse.builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).message("Unknown internal server error.")
                .dateTime(Instant.now()).build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(response);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException nfe) {
        ErrorResponse response = ErrorResponse.builder().statusCode(HttpStatus.NOT_FOUND.value()).message(nfe.getMessage()).dateTime(Instant.now()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(response);
    }
}

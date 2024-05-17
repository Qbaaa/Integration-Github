package com.qbaaa.exception.handler;

import com.qbaaa.exception.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ExceptionResponse> handleHttpClientErrorException(HttpClientErrorException exception,
                                                                            HttpServletRequest request) {
        log.error("URI: {}", request.getRequestURI(), exception);

        return ResponseEntity
                .status(exception.getStatusCode())
                .body(new ExceptionResponse(LocalDateTime.now(), exception.getStatusText(), exception.getMessage()) );
    }
}

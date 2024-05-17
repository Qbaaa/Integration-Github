package com.qbaaa.exception;

import java.time.LocalDateTime;

public record ExceptionResponse(
        LocalDateTime timestamp,
        String status,
        String message
        ) {
}

package com.codingplayground.propertyrental.util;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    private final String message;
    private final LocalDateTime timestamp;

    public ErrorResponse(String message, LocalDateTime now) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}

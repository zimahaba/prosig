package com.prosig.blog.config.error;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class ErrorResponse {

    private Integer statusCode;
    private String message;
    private Instant dateTime;
}

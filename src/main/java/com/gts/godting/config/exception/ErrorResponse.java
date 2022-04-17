package com.gts.godting.config.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {
    private final LocalDateTime time = LocalDateTime.now();
    private final String code;
    private final String error;
    private final String message;

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorcode) {
        return ResponseEntity
                .status(errorcode.getHttpStatus().value())
                .body(ErrorResponse.builder()
                        .error(errorcode.getHttpStatus().name())
                        .code(errorcode.name())
                        .message(errorcode.getDetail())
                        .build());
    }
}

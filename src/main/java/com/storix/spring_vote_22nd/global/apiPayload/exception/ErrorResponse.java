package com.storix.spring_vote_22nd.global.apiPayload.exception;

import com.storix.spring_vote_22nd.global.apiPayload.code.ErrorCode;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse (
        Boolean isSuccess,
        String code,
        String message,
        LocalDateTime timestamp
) {

    public ErrorResponse(ErrorCode errorCode) {
        this(false, errorCode.getCode(), errorCode.getMessage(), LocalDateTime.now());
    }

}
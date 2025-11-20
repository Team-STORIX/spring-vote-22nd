package com.storix.spring_vote_22nd.global.apiPayload.exception;

import com.storix.spring_vote_22nd.global.apiPayload.code.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(STORIXCodeException.class)
    public ResponseEntity<ErrorResponse> STORIXCodeExceptionHandler (STORIXCodeException ex) {

        ErrorCode errorCode = ex.getErrorCode();
        log.warn("[STORIX_CODE_EXCEPTION] code={}, message={}",
                errorCode.getCode(), errorCode.getMessage(), ex);
        ErrorResponse response = new ErrorResponse(errorCode);

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        log.error("[UNHANDLED_EXCEPTION] 발생", ex);

        ErrorResponse response = new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }


}
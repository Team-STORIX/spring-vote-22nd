package com.storix.spring_vote_22nd.global.apiPayload.exception;

import com.storix.spring_vote_22nd.global.apiPayload.code.ErrorCode;

public class InvalidTokenException extends STORIXCodeException {

    public static final STORIXCodeException EXCEPTION = new InvalidTokenException();

    private InvalidTokenException() { super(ErrorCode.INVALID_TOKEN); }
}

package com.storix.spring_vote_22nd.global.apiPayload.exception;

import com.storix.spring_vote_22nd.global.apiPayload.code.ErrorCode;

public class ExpiredTokenException extends STORIXCodeException {

    public static final STORIXCodeException EXCEPTION = new ExpiredTokenException();

    private ExpiredTokenException() { super(ErrorCode.TOKEN_EXPIRED); }
}
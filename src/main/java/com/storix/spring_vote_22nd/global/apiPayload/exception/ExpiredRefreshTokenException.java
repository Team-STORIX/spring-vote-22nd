package com.storix.spring_vote_22nd.global.apiPayload.exception;

import com.storix.spring_vote_22nd.global.apiPayload.code.ErrorCode;

public class ExpiredRefreshTokenException extends STORIXCodeException {

    public static final STORIXCodeException EXCEPTION = new ExpiredRefreshTokenException();

    private ExpiredRefreshTokenException() { super(ErrorCode.TOKEN_EXPIRED); }
}
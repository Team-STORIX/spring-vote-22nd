package com.storix.spring_vote_22nd.global.apiPayload.exception;

import com.storix.spring_vote_22nd.global.apiPayload.code.ErrorCode;

public class LoginException extends STORIXCodeException {

    public static final STORIXCodeException EXCEPTION = new LoginException();

    private LoginException() { super(ErrorCode.FAILED_LOGIN); }
}
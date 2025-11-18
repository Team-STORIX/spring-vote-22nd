package com.storix.spring_vote_22nd.global.apiPayload.exception;

import com.storix.spring_vote_22nd.global.apiPayload.code.ErrorCode;

public class UnknownUserException extends STORIXCodeException {

    public static final STORIXCodeException EXCEPTION = new UnknownUserException();

    private UnknownUserException() { super(ErrorCode.NOT_FOUND); }
}
package com.storix.spring_vote_22nd.global.apiPayload.exception;

import com.storix.spring_vote_22nd.global.apiPayload.code.ErrorCode;

public class DuplicateEmailException extends STORIXCodeException {

    public static final STORIXCodeException EXCEPTION = new DuplicateEmailException();

    private DuplicateEmailException() { super(ErrorCode.DUPLICATE_EMIAL_SIGNUP); }
}
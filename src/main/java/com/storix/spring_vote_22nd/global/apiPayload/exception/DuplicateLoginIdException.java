package com.storix.spring_vote_22nd.global.apiPayload.exception;

import com.storix.spring_vote_22nd.global.apiPayload.code.ErrorCode;

public class DuplicateLoginIdException extends STORIXCodeException {

    public static final STORIXCodeException EXCEPTION = new DuplicateLoginIdException();

    private DuplicateLoginIdException() { super(ErrorCode.DUPLICATE_LOGINID_SIGNUP); }
}
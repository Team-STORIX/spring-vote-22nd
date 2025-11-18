package com.storix.spring_vote_22nd.global.apiPayload.exception;

import com.storix.spring_vote_22nd.global.apiPayload.code.ErrorCode;

public class ArtistLoginException extends STORIXCodeException {

    public static final STORIXCodeException EXCEPTION = new ArtistLoginException();

    private ArtistLoginException() { super(ErrorCode.FAILED_LOGIN); }
}
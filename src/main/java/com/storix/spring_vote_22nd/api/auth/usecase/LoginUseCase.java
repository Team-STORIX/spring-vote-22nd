package com.storix.spring_vote_22nd.api.auth.usecase;

import com.storix.spring_vote_22nd.UseCase;
import com.storix.spring_vote_22nd.api.auth.dto.LoginRequest;
import com.storix.spring_vote_22nd.api.auth.dto.LoginWithTokenResponse;
import com.storix.spring_vote_22nd.api.auth.dto.LogoutRequest;
import com.storix.spring_vote_22nd.domains.user.adaptor.AuthUserDetails;
import com.storix.spring_vote_22nd.domains.user.adaptor.TokenGenerateHelper;
import com.storix.spring_vote_22nd.domains.user.service.LoginService;
import com.storix.spring_vote_22nd.global.apiPayload.CustomResponse;
import com.storix.spring_vote_22nd.global.apiPayload.code.SuccessCode;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class LoginUseCase {

    private final LoginService loginService;
    private final TokenGenerateHelper tokenGenerateHelper;

    public CustomResponse<LoginWithTokenResponse> userLoginWithLoginId(LoginRequest req) {
        loginService.validateUserLogin(req.loginId(), req.password());
        AuthUserDetails userDetails = loginService.loadUserByUsername(req.loginId());
        LoginWithTokenResponse loginWithTokenResponse = tokenGenerateHelper.generateLoginWithToken(userDetails);
        return CustomResponse.onSuccess(SuccessCode.VALID_LOGIN, loginWithTokenResponse);
    }

    public CustomResponse userLogoutWithRefreshToken(LogoutRequest req) {
        loginService.logoutByRefreshToken(req.refreshToken());
        return CustomResponse.onSuccess(SuccessCode.VALID_LOGOUT);
    }
}

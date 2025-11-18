package com.storix.spring_vote_22nd.api.auth;

import com.storix.spring_vote_22nd.api.auth.dto.LoginRequest;
import com.storix.spring_vote_22nd.api.auth.dto.LogoutRequest;
import com.storix.spring_vote_22nd.api.auth.dto.SignupRequest;
import com.storix.spring_vote_22nd.api.auth.usecase.AuthUseCase;
import com.storix.spring_vote_22nd.api.auth.usecase.LoginUseCase;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthUseCase authUseCase;
    private final LoginUseCase loginUseCase;

    @Operation(summary = "회원 가입용 api 입니다.")
    @PostMapping("/user/signup")
    public ResponseEntity signup(@RequestBody SignupRequest req){
        return ResponseEntity.ok()
                .body(authUseCase.signup(req));
    }

    @Operation(summary = "로그인용 api 입니다.")
    @PostMapping("/user/login")
    public ResponseEntity login(@RequestBody LoginRequest req){
        return ResponseEntity.ok()
                .body(loginUseCase.userLoginWithLoginId(req));
    }

    @Operation(summary = "로그아웃용 api 입니다.")
    @PostMapping("/user/logout")
    public ResponseEntity logout(@RequestBody LogoutRequest req) {
        return ResponseEntity.ok()
                .body(loginUseCase.userLogoutWithRefreshToken(req));
    }
}

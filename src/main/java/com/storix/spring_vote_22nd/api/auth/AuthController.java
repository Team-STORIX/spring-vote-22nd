package com.storix.spring_vote_22nd.api.auth;

import com.storix.spring_vote_22nd.api.auth.dto.*;
import com.storix.spring_vote_22nd.api.auth.usecase.AuthUseCase;
import com.storix.spring_vote_22nd.api.auth.usecase.AuthorizationUseCase;
import com.storix.spring_vote_22nd.api.auth.usecase.DropDownUseCase;
import com.storix.spring_vote_22nd.api.auth.usecase.LoginUseCase;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final DropDownUseCase dropDownUseCase;

    private final AuthUseCase authUseCase;
    private final LoginUseCase loginUseCase;

    private final AuthorizationUseCase authorizationUseCase;

    // DropDownUseCase
    @Operation(summary = "팀 조회용 api 입니다.")
    @GetMapping("/user/team")
    public ResponseEntity viewTeams() {
        return ResponseEntity.ok()
                .body(dropDownUseCase.getTeams());
    }

    @Operation(summary = "파트 조회용 api 입니다.")
    @GetMapping("/user/part")
    public ResponseEntity viewParts() {
        return ResponseEntity.ok()
                .body(dropDownUseCase.getParts());
    }

    // AuthUseCase
    @Operation(summary = "회원 가입용 api 입니다.")
    @PostMapping("/user/signup")
    public ResponseEntity signup(@RequestBody SignupRequest req){
        return ResponseEntity.ok()
                .body(authUseCase.signup(req));
    }

    // LoginUseCase
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

    // AuthorizationUseCase
    @Operation(summary = "만료된 accessToken을 재발급해주기 위해서 refreshToken을 받는 api 입니다.")
    @PostMapping("/refresh_token")
    public ResponseEntity reissueAccessToken(@RequestBody RefreshTokenRequest req){
        return ResponseEntity.ok()
                .body(authorizationUseCase.getAccessTokenWithRefreshToken(req));
    }
}

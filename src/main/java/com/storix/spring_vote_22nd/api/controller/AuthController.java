package com.storix.spring_vote_22nd.api.controller;

import com.storix.spring_vote_22nd.api.dto.LoginRequest;
import com.storix.spring_vote_22nd.api.dto.LoginResponse;
import com.storix.spring_vote_22nd.api.dto.SignUpRequest;
import com.storix.spring_vote_22nd.api.dto.LogoutRequest;
import com.storix.spring_vote_22nd.domains.member.service.AuthService;
import com.storix.spring_vote_22nd.domains.member.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final LoginService loginService;

    @PostMapping("/signup")
    public void signUp(@RequestBody SignUpRequest req) {
        authService.signUp(req);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {
        return loginService.login(req);
    }

    @PostMapping("/logout")
    public void logout(@RequestBody LogoutRequest req) {
        loginService.logout(req.refreshToken());
    }
}

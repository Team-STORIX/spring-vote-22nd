package com.storix.spring_vote_22nd.domains.user.service;


import com.storix.spring_vote_22nd.api.auth.dto.SignupRequest;
import com.storix.spring_vote_22nd.domains.user.adaptor.UserAdaptor;
import com.storix.spring_vote_22nd.domains.user.dto.CreateUserCommand;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserAdaptor userAdaptor;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long signUp(SignupRequest req) {
        userAdaptor.isLoginIdDuplicate(req.loginId());

        CreateUserCommand m = new CreateUserCommand(
                req.loginId(),
                passwordEncoder.encode(req.password()),
                req.email(),
                req.part(),
                req.name(),
                req.team()
        );

        userAdaptor.saveUser(m);
        Long userId = userAdaptor.findUserIdByLoginId(req.loginId());

        return userId;
    }
}

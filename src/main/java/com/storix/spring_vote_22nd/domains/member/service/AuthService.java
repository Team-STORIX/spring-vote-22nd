package com.storix.spring_vote_22nd.domains.member.service;

import com.storix.spring_vote_22nd.api.dto.SignUpRequest;
import com.storix.spring_vote_22nd.domains.member.adaptor.MemberReader;
import com.storix.spring_vote_22nd.domains.member.adaptor.MemberSaver;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberReader memberReader;
    private final MemberSaver memberSaver;
    private final PasswordEncoder passwordEncoder; // BCryptPasswordEncoder

    @Transactional
    public void signUp(SignUpRequest req) {

        CreateMemberCommand m = new CreateMemberCommand(
                req.loginId(),
                passwordEncoder.encode(req.password()),
                req.email(),
                req.part(),
                req.name(),
                req.team()
        );

        memberSaver.execute(m);

    }
}
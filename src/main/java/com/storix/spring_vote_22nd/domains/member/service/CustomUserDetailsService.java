package com.storix.spring_vote_22nd.domains.member.service;

import com.storix.spring_vote_22nd.domains.member.adaptor.MemberReader;
import com.storix.spring_vote_22nd.domains.member.dto.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberReader memberReader;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Member member = memberReader.getByLoginIdOrThrow(loginId);

        return User.builder()
                .username(member.loginId())   // username = loginId
                .build();
    }
}
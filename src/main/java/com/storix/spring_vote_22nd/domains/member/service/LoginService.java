package com.storix.spring_vote_22nd.domains.member.service;

import com.storix.spring_vote_22nd.api.LoginRequest;
import com.storix.spring_vote_22nd.api.LoginResponse;
import com.storix.spring_vote_22nd.domains.member.domain.MemberEntity;
import com.storix.spring_vote_22nd.domains.member.domain.RefreshToken;
import com.storix.spring_vote_22nd.domains.member.repository.MemberRepository;
import com.storix.spring_vote_22nd.domains.member.repository.RefreshTokenRepository;
import com.storix.spring_vote_22nd.global.security.TokenProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public LoginResponse login(LoginRequest req) {
        // 아이디 확인
        MemberEntity m = memberRepository.findByLoginId(req.loginId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자"));

        // 비밀번호 확인
        if (!passwordEncoder.matches(req.password(), m.getPassword())) {
            throw new IllegalArgumentException("비밀번호 불일치");
        }

        UserDetails user = new User(
                m.getLoginId(),
                m.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );

        Authentication auth = new UsernamePasswordAuthenticationToken(
                user, null, user.getAuthorities()
        );

        // 액세스 토큰 발급
        String accessToken = tokenProvider.createAccessToken(m.getId(), auth);

        // 리프레시 토큰 발급
        String refreshToken = tokenProvider.createRefreshToken(m.getId());

        // 리프레시 토큰을 발급받은 적이 있을 경우, DB에서 해당 토큰 삭제
        refreshTokenRepository.deleteByMemberId(m.getId());

        // 리프레시 토큰 DB 저장
        Date expiry = tokenProvider.getRefreshTokenExpiry(refreshToken);
        RefreshToken refreshTokenEntity = RefreshToken.builder()
                .memberId(m.getId())
                .token(refreshToken)
                .expiryDateTime(
                        LocalDateTime.ofInstant(
                                expiry.toInstant(),
                                ZoneId.systemDefault()
                        )
                )
                .build();

        refreshTokenRepository.save(refreshTokenEntity);

        return new LoginResponse(accessToken, refreshToken);
    }

    @Transactional
    public void logout(String refreshToken) {
        // 토큰 검증
        if (!tokenProvider.validateRefreshToken(refreshToken)) {
            throw new IllegalArgumentException("유효하지 않은 리프레시 토큰");
        }

        // DB에서 해당 토큰 삭제
        refreshTokenRepository.deleteByToken(refreshToken);
    }
}

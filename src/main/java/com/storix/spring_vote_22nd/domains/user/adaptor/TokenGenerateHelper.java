package com.storix.spring_vote_22nd.domains.user.adaptor;

import com.storix.spring_vote_22nd.api.auth.dto.LoginWithTokenResponse;
import com.storix.spring_vote_22nd.domains.user.domain.RefreshToken;
import com.storix.spring_vote_22nd.global.security.TokenProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.storix.spring_vote_22nd.global.apiPayload.STORIX_voteStatic.MILLI_TO_SECOND;

@Service
@RequiredArgsConstructor
public class TokenGenerateHelper {

    private final TokenProvider tokenProvider;
    private final RefreshTokenAdaptor refreshTokenAdaptor;

    @Transactional
    public LoginWithTokenResponse generateLoginWithToken(AuthUserDetails userDetails) {

        String userId = userDetails.getUserId();
        String role = userDetails.getRole();

        String accessToken = tokenProvider.createAccessToken(userId, role);
        String refreshToken = tokenProvider.createRefreshToken(userId);

        // redis 저장
        long ttlSeconds = tokenProvider.getRefreshTokenValidityMs() * MILLI_TO_SECOND;
        RefreshToken newRefreshToken = RefreshToken.builder()
                .id(userId)
                .refreshToken(refreshToken)
                .ttl(ttlSeconds)
                .build();
        refreshTokenAdaptor.save(newRefreshToken);

        return LoginWithTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}

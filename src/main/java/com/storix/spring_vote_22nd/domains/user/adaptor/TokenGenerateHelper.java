package com.storix.spring_vote_22nd.domains.user.adaptor;

import com.storix.spring_vote_22nd.domains.user.domain.Role;
import com.storix.spring_vote_22nd.domains.user.dto.LoginWithTokenResponse;
import com.storix.spring_vote_22nd.domains.user.domain.RefreshToken;
import com.storix.spring_vote_22nd.global.apiPayload.exception.InvalidTokenException;
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
    private final UserAdaptor userAdaptor;

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

        return new LoginWithTokenResponse(accessToken, refreshToken);
    }

    @Transactional
    public String reissueAccessTokenWithRefreshToken(String refreshToken) {

        if (!tokenProvider.isRefreshToken(refreshToken)) {
            throw InvalidTokenException.EXCEPTION;
        }

        Long userId = tokenProvider.parseRefreshToken(refreshToken);
        Role role = userAdaptor.findUserRoleByUserId(userId);

        return tokenProvider.createAccessToken(String.valueOf(userId), String.valueOf(role));
    }
}

package com.storix.spring_vote_22nd.domains.user.adaptor;

import com.storix.spring_vote_22nd.domains.user.domain.RefreshToken;
import com.storix.spring_vote_22nd.domains.user.repository.RefreshTokenRepository;
import com.storix.spring_vote_22nd.global.apiPayload.exception.InvalidTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenAdaptor {

    private final RefreshTokenRepository refreshTokenRepository;

    public Long findUserIdByRefreshToken(String refreshToken) {
        Optional<RefreshToken> refreshTokenInfo = refreshTokenRepository.findByRefreshToken(refreshToken);
        if (!refreshTokenInfo.isPresent()) {
            throw InvalidTokenException.EXCEPTION;
        }

        return Long.valueOf(refreshTokenInfo.get().getId());
    }

    public RefreshToken save(RefreshToken refreshToken) {
        return refreshTokenRepository.save(refreshToken);
    }

    public void deleteByUserId(Long userId) {
        refreshTokenRepository.deleteById(userId.toString());
    }
}

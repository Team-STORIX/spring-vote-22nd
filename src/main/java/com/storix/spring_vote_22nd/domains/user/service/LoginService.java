package com.storix.spring_vote_22nd.domains.user.service;

import com.storix.spring_vote_22nd.domains.user.adaptor.AuthUserDetails;
import com.storix.spring_vote_22nd.domains.user.adaptor.RefreshTokenAdaptor;
import com.storix.spring_vote_22nd.domains.user.adaptor.UserAdaptor;
import com.storix.spring_vote_22nd.domains.user.domain.RefreshToken;
import com.storix.spring_vote_22nd.domains.user.dto.LoginInfo;
import com.storix.spring_vote_22nd.global.apiPayload.exception.LoginException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final UserAdaptor userAdaptor;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenAdaptor refreshTokenAdaptor;

    @Override
    public AuthUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAdaptor.findUserIdAndRoleByLoginId(username);
    }

    public void validateUserLogin(String loginId, String password) {
        LoginInfo artistUserLoginInfo = userAdaptor.findUserLoginInfoByLoginI(loginId);

        if (!passwordEncoder.matches(password, artistUserLoginInfo.password())) {
            throw LoginException.EXCEPTION;
        }
    }

    public void logoutByRefreshToken(String refreshToken) {
        Long userId = refreshTokenAdaptor.findUserIdByRefreshToken(refreshToken);
        refreshTokenAdaptor.deleteByUserId(userId);
    }
}


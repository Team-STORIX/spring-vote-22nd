package com.storix.spring_vote_22nd.domains.user.adaptor;

import com.storix.spring_vote_22nd.domains.user.domain.User;
import com.storix.spring_vote_22nd.domains.user.dto.LoginInfo;
import com.storix.spring_vote_22nd.domains.user.repository.UserRepository;
import com.storix.spring_vote_22nd.domains.user.dto.CreateUserCommand;
import com.storix.spring_vote_22nd.global.apiPayload.code.ErrorCode;
import com.storix.spring_vote_22nd.global.apiPayload.exception.LoginException;
import com.storix.spring_vote_22nd.global.apiPayload.exception.ErrorResponse;
import com.storix.spring_vote_22nd.global.apiPayload.exception.UnknownUserException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserAdaptor {

    private final UserRepository userRepository;

    public Long findUserIdByLoginId(String loginId){
        Optional<User> user = userRepository.findUserByLoginId(loginId);
        if(user.isPresent()){
            return user.get().getId();
        }
        throw UnknownUserException.EXCEPTION;
    }

    public LoginInfo findUserLoginInfoByLoginI(String loginId){
        Optional<User> user = userRepository.findUserByLoginId(loginId);
        if(!user.isPresent()) {
            throw LoginException.EXCEPTION;
        }

        LoginInfo loginInfo = new LoginInfo(user.get().getLoginId(), user.get().getPassword());
        return loginInfo;
    }

    public AuthUserDetails findUserIdAndRoleByLoginId(String loginId){
        Optional<User> user = userRepository.findUserByLoginId(loginId);
        if(user.isPresent()){
            return new AuthUserDetails(String.valueOf(user.get().getId()), String.valueOf(user.get().getRole()));
        }
        throw UnknownUserException.EXCEPTION;
    }

    public ErrorResponse isLoginIdDuplicate(String loginId) {
        Optional<User> user = userRepository.findUserByLoginId(loginId);
        if (user.isPresent()) {
            return new ErrorResponse(ErrorCode.BAD_REQUEST);
        }
        return null;
    }

    public User saveUser(CreateUserCommand cmd) {
        User user = userRepository.save(cmd.toEntity());
        return userRepository.save(user);
    }
}
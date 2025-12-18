package com.storix.spring_vote_22nd.domains.user.adaptor;

import com.storix.spring_vote_22nd.domains.user.domain.Role;
import com.storix.spring_vote_22nd.domains.user.domain.User;
import com.storix.spring_vote_22nd.domains.user.dto.LoginInfo;
import com.storix.spring_vote_22nd.domains.user.repository.UserRepository;
import com.storix.spring_vote_22nd.domains.user.dto.CreateUserCommand;
import com.storix.spring_vote_22nd.global.apiPayload.exception.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserAdaptor {

    private final UserRepository userRepository;

    public Role findUserRoleByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get().getRole();
        }
        throw UnknownUserException.EXCEPTION;
    }

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

    public void validateLoginId(String loginId) {
        Optional<User> user = userRepository.findUserByLoginId(loginId);
        if (user.isPresent()) {
            throw DuplicateLoginIdException.EXCEPTION;
        }
    }

    public void validateEmail(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isPresent()) {
            throw DuplicateEmailException.EXCEPTION;
        }
    }

    public User saveUser(CreateUserCommand cmd) {
        User user = userRepository.save(cmd.toEntity());
        return userRepository.save(user);
    }

    // User 엔티티 전체 반환
    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> UnknownUserException.EXCEPTION);
    }
}
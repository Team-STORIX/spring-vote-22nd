package com.storix.spring_vote_22nd.domains.user.adaptor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@AllArgsConstructor
public class AuthUserDetails implements UserDetails {

    private String userId;

    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + role)
        );
    }

    @Override
    public String getPassword() { return null; }

    @Override
    public String getUsername() { return userId; }

}


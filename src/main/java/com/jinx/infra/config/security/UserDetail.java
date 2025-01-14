package com.jinx.infra.config.security;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * The default userDetails {@link User} not suitable business because hasn't id and other fields.
 *
 * @author Jinx
 */
@Getter
@Builder
public class UserDetail implements UserDetails {

    private String id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> Authorities;
    private boolean enabled;
}

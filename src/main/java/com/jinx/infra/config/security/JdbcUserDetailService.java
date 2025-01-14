package com.jinx.infra.config.security;

import com.jinx.domain.primitive.Mobile;
import com.jinx.domain.primitive.Status;
import com.jinx.domain.repository.AccountRepository;
import com.jinx.domain.repository.PermissionRepository;
import com.jinx.domain.repository.UserRepository;
import com.jinx.domain.user.Account;
import com.jinx.domain.user.Permission;
import com.jinx.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Jinx
 */
@Component
@RequiredArgsConstructor
public class JdbcUserDetailService implements UserDetailsService {

    private final UserRepository userRepo;
    private final AccountRepository accountRepo;
    private final PermissionRepository permissionRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountRepo.findByMobile(new Mobile(username));
        if (account.isEmpty()) {
            throw new UsernameNotFoundException("JdbcUserDetailService not found [%s]".formatted(username));
        }

        Optional<User> user = userRepo.findByAccount(account.get());
        Assert.isTrue(user.isPresent(), "Account is existed but the user not found!");

        return UserDetail.builder()
                .id(user.get().getId())
                .username(account.get().getMobile().value())
                .password(account.get().getPassword().value())
                .Authorities(getAuthorities(user.get()))
                .enabled(user.get().getStatus() == Status.ENABLE)
                .build();
    }


    private Collection<GrantedAuthority> getAuthorities(User user) {
        return AuthorityUtils.createAuthorityList(
                permissionRepo.findByUser(user)
                        .stream()
                        .map(Permission::getSignage)
                        .collect(Collectors.toSet())
        );
    }
}

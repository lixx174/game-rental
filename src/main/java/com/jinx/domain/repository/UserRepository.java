package com.jinx.domain.repository;

import com.jinx.domain.user.Account;
import com.jinx.domain.user.User;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * Domain repo.
 *
 * @author Jinx
 */
public interface UserRepository {

    void save(@NonNull User user);

    Optional<User> findByAccount(Account account);
}

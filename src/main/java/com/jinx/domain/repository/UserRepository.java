package com.jinx.domain.repository;

import com.jinx.domain.user.User;
import org.springframework.lang.NonNull;

/**
 * Domain repo.
 *
 * @author Jinx
 */
public interface UserRepository {

    void save(@NonNull User user);
}

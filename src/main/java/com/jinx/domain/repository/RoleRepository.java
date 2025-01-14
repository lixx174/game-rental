package com.jinx.domain.repository;

import com.jinx.domain.user.Role;
import com.jinx.domain.user.User;
import jakarta.annotation.Nonnull;

import java.util.Collection;

/**
 * Domain repo.
 *
 * @author Jinx
 */
public interface RoleRepository {

    Collection<Role> findByUser(@Nonnull User user);
}

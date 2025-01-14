package com.jinx.domain.repository;

import com.jinx.domain.user.Permission;
import com.jinx.domain.user.User;
import jakarta.annotation.Nonnull;

import java.util.Collection;

/**
 * Domain repo.
 *
 * @author Jinx
 */
public interface PermissionRepository {

    Collection<Permission> findByUser(@Nonnull User user);

    Collection<Permission> findAll();
}

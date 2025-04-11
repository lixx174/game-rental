package com.jinx.application;

import com.jinx.infra.config.security.UserDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Authorization context, offer authentication info.
 *
 * @author Jinx
 */
public class UserContext {

    public static String getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetail userDetail) {
                return userDetail.getId();
            }
        }

        throw new AccessDeniedException("Access Denied");
    }
}

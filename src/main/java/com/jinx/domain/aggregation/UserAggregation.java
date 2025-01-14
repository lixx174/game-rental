package com.jinx.domain.aggregation;

import com.jinx.domain.user.Account;
import com.jinx.domain.user.Role;
import com.jinx.domain.user.User;

import java.util.Set;

/**
 * TODO Aggregate all association of user? it's look like too bloated.
 * <p>
 * <p>
 * TODO How to aggregate these domain?
 *
 * @author Jinx
 */
public class UserAggregation {

    private User user;
    private Account account;
    private Set<Role> roles;
}

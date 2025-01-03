package com.jinx.domain.repository;

import com.jinx.domain.primitive.Mobile;
import com.jinx.domain.user.Account;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * Domain repo.
 *
 * @author Jinx
 */
public interface AccountRepository {

    Optional<Account> findByMobile(@NonNull Mobile mobile);

    void save(@NonNull Account account);
}

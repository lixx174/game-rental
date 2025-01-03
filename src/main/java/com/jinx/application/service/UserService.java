package com.jinx.application.service;

import com.jinx.application.command.UserCreateCommand;
import com.jinx.application.generator.IdentityGenerator;
import com.jinx.application.generator.Schema;
import com.jinx.domain.primitive.Mobile;
import com.jinx.domain.primitive.Password;
import com.jinx.domain.primitive.Status;
import com.jinx.domain.repository.AccountRepository;
import com.jinx.domain.repository.UserRepository;
import com.jinx.domain.user.Account;
import com.jinx.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author Jinx
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final AccountRepository accountRepo;
    private final IdentityGenerator generator;

    public void save(UserCreateCommand command) {
        Mobile mobile = new Mobile(command.getMobile());
        Assert.isTrue(
                accountRepo.findByMobile(mobile).isPresent(),
                "mobile [%s] already existed!".formatted(mobile)
        );

        Account account = new Account(
                generator.generate(Schema.ACCOUNT),
                mobile,
                new Password("Aa" + mobile.getLastSixDigits()),
                Status.ENABLE
        );
        User user = new User(
                generator.generate(Schema.USER),
                command.getName(),
                command.getBirthday(),
                Status.ENABLE
        );

        accountRepo.save(account);
        userRepo.save(user);
    }
}
